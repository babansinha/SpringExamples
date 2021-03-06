angular.module('auth', []).factory(
		'auth',

		function($rootScope, $http, $location) {

			enter = function() {
				if ($location.path() != auth.loginPath) {
					auth.path = $location.path();
					if (!auth.authenticated) {
						$location.path(auth.loginPath);
					}
				}					
			}

			var auth = {
				user : {},
				authenticated : false,

				loginPath : 'login',
				logoutPath : 'logout',
				homePath : '/',
				path : $location.path(),

				authenticate : function(credentials, callback) {

					var headers = credentials && credentials.username ? {
						authorization : "Basic "
								+ btoa(credentials.username + ":"
										+ credentials.password)
					} : {};

					$http.get('user', {
						headers : headers
					}).success(function(data) {
						console.log("data : ", data);
						if (data.name) {
							auth.user.name = data.principal.username;
							auth.authenticated = true;
						} else {
							auth.authenticated = false;
						}
						
						$rootScope.auth = auth;
						callback && callback(auth.authenticated);
						$location.path(auth.path==auth.loginPath ? auth.homePath : auth.path);
					}).error(function() {
						auth.authenticated = false;
						callback && callback(false);
						$rootScope.auth = auth;
					});

				},

				clear : function() {
					console.log("clear. . .", auth.logoutPath);
					$location.path(auth.loginPath);
					auth.authenticated = false;
					$http.post(auth.logoutPath, {}).success(function() {
						console.log("Logout succeeded");
					}).error(function(data) {
						console.log("Logout failed");
					});
					
					$rootScope.auth = auth;
				},

				init : function(homePath, loginPath, logoutPath) {

					auth.homePath = homePath;
					auth.loginPath = loginPath;
					auth.logoutPath = logoutPath;
					$rootScope.auth = auth;

					auth.authenticate({}, function(authenticated) {
						if (authenticated) {
							$location.path(auth.path);
						}
					})

					// Guard route changes and switch to login page if unauthenticated
					$rootScope.$on('$routeChangeStart', function() {
						console.log("$routeChangeStart. . .");
						//enter();
					});
					
					/*$rootScope.$on('Unauthorized', function() {
						console.log("Unauthorized. . .");
						//$location.path(auth.path);
						//enter();
					});*/

				}

			};

			return auth;

		});
