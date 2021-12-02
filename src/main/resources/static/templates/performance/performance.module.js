(function() {
	'use strict';

	angular
	.module('myApp.performance', [ 'datatables' ])
	.config(function($stateProvider) {
				$stateProvider
				.state('main.performance', {
					url : "/performance",
					views : {
						"sub" : {
							templateUrl : "templates/performance/performance.html",
							controller : "PerformanceController as vm"
						}
					}
				})
				$stateProvider
				.state('main.performance2', {
					url : "/performance2",
					views : {
						"sub" : {
							templateUrl : "templates/performance/performance2.html",
							controller : "PerformanceController as vm"
						}
					}
				})
			});

})();