//Define an angular module for our app
var communityRoute = angular.module('CommunityRoute', ['ngRoute']);
communityRoute.config(['$routeProvider',
         function($routeProvider) {
        	  $routeProvider.
        	  when('/community', {
        		  templateUrl : 'community/community.html',
        		  controller : 'CommunityController'
        	  }).
        	  when('/accessibility', {
        		  templateUrl : 'accessibility/accessibility.html',
        		  controller : 'CommunityController'
        	  }).
        	  when('/services', {
        		  templateUrl : 'services/services.html',
        		  controller : 'CommunityController'
        	  }).
        	  when('/articles', {
        		  templateUrl : 'article/article.html',
        		  controller : 'CommunityController'
        	  }).
        	  otherwise({
        		  redirectTo : '/home'
        	  });
        }]);
