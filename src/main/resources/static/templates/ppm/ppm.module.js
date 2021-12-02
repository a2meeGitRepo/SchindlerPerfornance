(function() {
	'use strict';

	angular
	.module('myApp.ppm', [ 'datatables' ])
	.config(function($stateProvider) {
				$stateProvider
				.state('main.ppm', {
					url : "/ppm",
					views : {
						"sub" : {
							templateUrl : "templates/ppm/ppm.html",
							controller : "PpmController as vm"
						}
					}
				})
				
				$stateProvider
				.state('main.ppm2', {
					url : "/ppm2",
					views : {
						"sub" : {
							templateUrl : "templates/ppm/ppm2.html",
							controller : "PpmController as vm"
						}
					}
				})
			});

})();