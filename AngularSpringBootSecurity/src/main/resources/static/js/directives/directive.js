var footerDirective = angular.module("footerDirective",[]);


footerDirective.directive('footerDirective', function() {
	return {
		restrict: 'E',
		replace: true,
		require: '?cdMouseDrag',
		scope: true,
		controller: function($scope, $window, $timeout){
			//$scope.dw = ubconfig.get('bottomMenu');
			$scope.aboutUs = 0;
			$scope.container_style_param = function() {
				var style = {
						'width': ($window.innerWidth - 100) + 'px',
				};
				return style;
			};

			$scope.item_style = function(idx) {
				var style = {
						'padding-right':'10px',
						'padding-left' : '10px',
				};
				style['float'] = 'left';

				return style;
			};

			$scope.item_click = function(item) {
				if(item.label == 'About') {
					$scope.aboutUs = 1;
				}
			};

			$scope.$on('aboutus', function(ev,val) {
				$scope.aboutUs = val;
			});
		},
		templateUrl: 'footer.html'
	};
});



