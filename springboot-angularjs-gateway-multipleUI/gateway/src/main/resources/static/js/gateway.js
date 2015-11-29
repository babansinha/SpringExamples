angular.module('gateway', []).config(function($httpProvider) {

	$httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';
	
	// Tough luck: the default cookie-to-header mechanism is not working for cross-origin requests!
	$httpProvider.defaults.xsrfCookieName = 'XSRF-TOKEN'; // The name of the cookie sent by the server
	$httpProvider.defaults.xsrfHeaderName = 'X-XSRF-TOKEN'; // The default header name picked up 

}).controller('navigation',

function($scope, $http) {

	var authenticate = function(credentials, callback) {

		var headers = credentials ? {
			authorization : "Basic "
					+ btoa(credentials.username + ":"
							+ credentials.password)
		} : {};

		$scope.user = ''
		$http.get('user', {
			headers : headers
		}).success(function(data) {
			if (data.name) {
				$scope.authenticated = true;
				$scope.user = data.name
			} else {
				$scope.authenticated = false;
			}
			callback && callback(true);
		}).error(function() {
			$scope.authenticated = false;
			callback && callback(false);
		});

	}

	authenticate();

	$scope.credentials = {};
	$scope.login = function() {
		authenticate($scope.credentials, function(authenticated) {
			$scope.authenticated = authenticated;
			$scope.error = !authenticated;
		})
	};

	$scope.logout = function() {
		$http.post('logout', {}).success(function() {
			$scope.authenticated = false;
		}).error(function(data) {
			console.log("Logout failed")
			$scope.authenticated = false;
		});
	}

});
