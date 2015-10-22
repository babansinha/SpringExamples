//Define an angular module for our app
var errorRoute = angular.module('ErrorRoute', [ 'ngRoute', 'auth' ]);
errorRoute.config(
	function($routeProvider, $httpProvider) {//, $locationProvider) {
		//$locationProvider.html5Mode(true);

		$routeProvider.when('/401', {
			templateUrl : 'error/401.html',
			controller : 'ErrorController'
		}).when('/404', {
			templateUrl : 'error/404.html',
			controller : 'ErrorController'
		}).when('/422', {
			templateUrl : 'error/422.html',
			controller : 'ErrorController'
		}).when('/500', {
			templateUrl : 'error/500.html',
			controller : 'ErrorController'
		}).otherwise({
			redirectTo : '/home'
		});

		//$httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';

	}).run(function() {});