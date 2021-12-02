(function() {
	'use strict';

	angular
	.module('myApp.supplier', [ 'datatables' ])
	.config(function($stateProvider) {
				$stateProvider
				.state('main.supplier', {
					url : "/supplier",
					views : {
						"sub" : {
							templateUrl : "templates/supplier/supplier.html",
							controller : "SupplierController as vm"
						}
					}
				})
			});

})();