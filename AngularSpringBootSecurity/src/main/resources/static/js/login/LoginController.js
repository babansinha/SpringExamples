//Define an angular module for our app
var loginController = angular.module('LoginController',
		[ 'auth', 'userService' ]);

loginController.controller('LoginController', function($scope, $rootScope, $location,
		auth, userService) {
	$rootScope.initialized = true;
	$scope.credentials = {};

	$scope.authenticated = function() {
		return auth.authenticated;
	}

	$scope.login = function() {
		auth.authenticate($scope.credentials, function(authenticated) {
			if (authenticated) {
				console.log("Login succeeded");
				$scope.error = false;
				//$location.path("/home");
			} else {
				console.log("Login failed")
				$scope.error = true;
			}
		})
	};

	$scope.logout = auth.clear;

	$scope.showSignInPage = function($scope, $location) {
		$location.url = "/login";
	}

});