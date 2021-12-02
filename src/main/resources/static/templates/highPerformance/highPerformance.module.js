(function() {
	'use strict';

	angular.module('myApp.highPerformance', [ 'datatables' ])
	.config(function($stateProvider) {
				$stateProvider
				.state('main.highPerformance', {
					url : "/highPerformance",
					views : {
						"sub" : {
							templateUrl : "templates/highPerformance/highPerformance.html",
							controller : "HighPerformanceController as vm"
						}
					}
				})
			});

})();