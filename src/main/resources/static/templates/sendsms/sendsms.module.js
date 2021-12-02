(function() {
	'use strict';

	angular.module('myApp.sendsms', [ 'datatables' ])
	.config(function($stateProvider) {
				$stateProvider
				.state('main.sendsms', {
					url : "/sendsms",
					views : {
						"sub" : {
							templateUrl : "templates/sendsms/sendsms.html",
							controller : "SendsmsController as vm"
						}
					}
				})
			});

})();