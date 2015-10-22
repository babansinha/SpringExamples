//Define an angular module for our app
var loginRoute = angular.module('LoginRoute', [ 'ngRoute', 'auth' ]);
loginRoute.config(
				function($routeProvider, $httpProvider) {//, $locationProvider) {
					//$locationProvider.html5Mode(true);

					$routeProvider.when('/home', {
						templateUrl : 'home.html',
						controller : 'LoginController'
					}).when('/login', {
						templateUrl : 'login/login.html',
						controller : 'LoginController'
					}).when('/loginSuccess', {
						templateUrl : 'login/success.html',
						controller : 'LoginController'
					}).when('/signUp', {
						templateUrl : 'login/registration.html',
						controller : 'LoginController'
					}).when('/contactus', {
						templateUrl : 'contact-us.html',
						controller : 'LoginController'
					}).when('/aboutus', {
						templateUrl : 'about-us.html',
						controller : 'LoginController'
					}).when('/copywrite', {
						templateUrl : 'copy-write-terms.html',
						controller : 'LoginController'
					}).otherwise({
						redirectTo : '/home'
					});

					//$httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';

				}).run(function(auth) {

			// Initialize auth module with the home page and login/logout path
			// respectively
			auth.init('/', 'login', 'logout');

		});