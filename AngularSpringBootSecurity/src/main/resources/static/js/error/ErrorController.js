//Define an angular module for our app
var errorController = angular.module('ErrorController',
		[]);

errorController.controller('ErrorController', function($scope, $rootScope, $log, $location) {
	$scope.redirectToErrorPage = function(errorCode) {
		$log.info("errorCode : ", errorCode);
		$location.url = "/" + errorCode;
	};

});