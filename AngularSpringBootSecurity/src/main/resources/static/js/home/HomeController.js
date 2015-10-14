//Define an angular module for our app
var homeController = angular.module('homeController', [ 'userService' ]);

homeController.controller('homeController', function($scope, $rootScope, auth,
		userService) {
	$rootScope.initialized = true;
	$scope.user = {};

	$scope.resource = function() {
		userService.resource(resourceCallback);
	};

	resourceCallback = function(data) {
		$scope.user = data;
	}

});