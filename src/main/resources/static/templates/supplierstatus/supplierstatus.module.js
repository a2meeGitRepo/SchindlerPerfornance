(function() {
	'use strict';

	angular
	.module('myApp.supplierstatus', [ 'datatables' ])
	.config(function($stateProvider) {
				$stateProvider
				.state('main.supplierstatus', {
					url : "/supplierstatus",
					views : {
						"sub" : {
							templateUrl : "templates/supplierstatus/supplierstatus.html",
							controller : "SupplierstatusController as vm"
						}
					}
				})
			});

})();