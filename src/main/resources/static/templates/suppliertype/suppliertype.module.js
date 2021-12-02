(function() {
	'use strict';

	angular
	.module('myApp.suppliertype', [ 'datatables' ])
	.config(function($stateProvider) {
				$stateProvider
				.state('main.suppliertype', {
					url : "/suppliertype",
					views : {
						"sub" : {
							templateUrl : "templates/suppliertype/suppliertype.html",
							controller : "SuppliertypeController as vm"
						}
					}
				})
			});

})();