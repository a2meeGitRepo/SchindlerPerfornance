(function() {
	'use strict';

	angular
	.module('myApp.otd', [ 'datatables' ])
	.config(function($stateProvider) {
				$stateProvider
				.state('main.otd', {
					url : "/otd",
					views : {
						"sub" : {
							templateUrl : "templates/otd/otd.html",
							controller : "OtdController as vm"
						}
					}
				})
				
				$stateProvider
				.state('main.otd2', {
					url : "/otd2",
					views : {
						"sub" : {
							templateUrl : "templates/otd/otd2.html",
							controller : "OtdController as vm"
						}
					}
				})
			});

})();