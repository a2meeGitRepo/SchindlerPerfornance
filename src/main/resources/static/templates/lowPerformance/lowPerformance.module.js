(function() {
	'use strict';

	angular.module('myApp.lowPerformance', [ 'datatables' ])
	.config(function($stateProvider) {
				$stateProvider
				.state('main.lowPerformance', {
					url : "/lowPerformance",
					views : {
						"sub" : {
							templateUrl : "templates/lowPerformance/lowPerformance.html",
							controller : "LowPerformanceController as vm"
						}
					}
				})
			});

})();