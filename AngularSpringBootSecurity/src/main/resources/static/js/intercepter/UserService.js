angular.module('userService', []).factory(
		'userService',
		function($rootScope, $http, $location) {

			var userData = {
				user : {},
				authenticated : false,

				loginPath : 'login',
				logoutPath : 'logout',
				homePath : '/',
				path : $location.path(),

				resource : function(callback) {

					$http.get('resource').success(function(data) {
						console.log("data : ", data);
						//userService = data;
						/*if (data.name) {
							userService.user.name = data.principal.username;
							userService.authenticated = true;
						} else {
							userService.authenticated = false;
						}
						
						$rootScope.userService = userService;*/
						$rootScope.userData = data;
						callback && callback(data);
					}).error(function() {
						userData.authenticated = false;
						callback && callback(false);
					});

				}

			};

			return userData;

		});
