(function() {
	'use strict';

	angular.module('myApp.vendorPerformance', [ 'datatables' ])
	.config(function($stateProvider) {
				$stateProvider
				.state('main.vendorPerformance', {
					url : "/vendorPerformance",
					views : {
						"sub" : {
							templateUrl : "templates/vendorPerformance/vendorPerformance.html",
							controller : "VendorPerformanceController as vm"
						}
					}
				})
			});

})();