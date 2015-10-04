
/*angular.module('hello', [])
  .controller('home', function($scope, $http) {
  $http.get('resource').success(function(data) {
    $scope.greeting = data;
  })
});*/

angular.module('hello', [ 'ngRoute' ]).config(function($routeProvider, $httpProvider) {

	$routeProvider.when('/', {
		templateUrl : 'home.html',
		controller : 'home'
	}).when('/login', {
		templateUrl : 'login.html',
		controller : 'navigation'
	}).otherwise('/');

	$httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';

}).controller(
		'navigation',

		function($rootScope, $scope, $http, $location, $route) {

			$scope.tab = function(route) {
				return $route.current && route === $route.current.controller;
			};

			var authenticate = function(credentials, callback) {

				var headers = credentials ? {
					authorization : "Basic "
							+ btoa(credentials.username + ":"
									+ credentials.password)
				} : {};

				$http.get('user', {
					headers : headers
				}).success(function(data) {
					if (data.name) {
						$rootScope.authenticated = true;
					} else {
						$rootScope.authenticated = false;
					}
					callback && callback($rootScope.authenticated);
				}).error(function() {
					$rootScope.authenticated = false;
					callback && callback(false);
				});

			}

			authenticate();

			$scope.credentials = {};
			$scope.login = function() {
				console.log("login. . .");
				authenticate($scope.credentials, function(authenticated) {
					console.log("authenticated. . .", authenticated);
					if (authenticated) {
						console.log("Login succeeded")
						$location.path("/");
						$scope.error = false;
						$rootScope.authenticated = true;
					} else {
						console.log("Login failed")
						$location.path("/login");
						$scope.error = true;
						$rootScope.authenticated = false;
					}
				})
			};

			$scope.logout = function() {
				console.log("logout. . .");
				$http.post('logout', {}).success(function() {
					$rootScope.authenticated = false;
					$location.path("/");
				}).error(function(data) {
					console.log("Logout failed")
					$rootScope.authenticated = false;
				});
			}

		}).controller('home', function($scope, $http) {
			$http.get('./resource').success(function(data) {
				$scope.greeting = data;
			})
		});