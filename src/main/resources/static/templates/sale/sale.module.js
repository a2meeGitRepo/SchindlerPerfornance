(function() {
	'use strict';

	angular.module('myApp.sale', [ 'datatables' ])
	.config(function($stateProvider) {
				$stateProvider
				.state('main.sale', {
					url : "/sale",
					views : {
						"sub" : {
							templateUrl : "templates/sale/sale.html",
							controller : "SaleController as vm"
						}
					}
				})
			});

})();