angular.module("demoApp", ['LoginController', 'LoginRoute', 'CommunityController', 'homeController', 'CommunityRoute', 'footerDirective', 'LoadingInterceptor', 'ErrorController', 'ErrorRoute'])
.config(['$httpProvider', function($httpProvider) {
    $httpProvider.interceptors.push('LoadingInterceptor');
	$httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';
}]);