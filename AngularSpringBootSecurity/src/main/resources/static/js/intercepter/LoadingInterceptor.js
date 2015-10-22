angular.module('LoadingInterceptor', [])
.factory('LoadingInterceptor', ['$q', '$rootScope', '$log', '$location', 
	 function($q, $rootScope, $log, $location) { 'use strict';
	 	var xhrCreations = 0;
	    var xhrResolutions = 0;

	    function isLoading() {
	        return xhrResolutions < xhrCreations;
	    }
	 
	    function updateStatus() {
	        $rootScope.loading = isLoading();
	    }
	    
	    function handleError(status) {
	    	$log.error("status : ", status);
	    	switch (status) {
	        //server error
	        case 500:
	        	$log.info("500 : ", status);
	        	//ErrorController.redirectToErrorPage(status);
	        	//$location.url = "/" + status;
	            break;
	        //If unauthorized
	        case 401:
	        	$log.info("401 : ", status);
	        	$location.path("/401");
	        	//$rootScope.$emit('Unauthorized');
	        	//$location.path("/login");
	        	//$location.url = "/login";
	            break;      
	       }
	    }
	 
	    return {
	        request: function (config) {
	            xhrCreations++;
	            updateStatus();
	            return config;
	        },
	        requestError: function (rejection) {
	            xhrResolutions++;
	            updateStatus();
	            $log.error('Request error:', rejection);
	            return $q.reject(rejection);
	        },
	        response: function (response) {
	        	//$log.info("response : ", response);
	            xhrResolutions++;
	            updateStatus();
	            return response;
	        },
	        responseError: function (rejection) {
	            xhrResolutions++;
	            updateStatus();
	            $log.error('Response error:', rejection);
	            handleError(rejection.status);
	            return $q.reject(rejection);
	        }
	    };
	}]);
