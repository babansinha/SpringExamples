//Define an angular module for our app
var communityController = angular.module('CommunityController', []);

communityController.controller('CommunityController', function($scope, $rootScope) {
	$scope.message = 'This is Add new order screen';
	var user = {};
	user.name = "Jyoti";
	$scope.user = user;
	
	 $rootScope.initialized = true;
	 
	 $scope.showSignInPage = function($scope, $location) {
		 $location = "/community";
	 };
});