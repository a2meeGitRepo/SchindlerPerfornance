(function() {
	'use strict';

	angular
	.module('myApp.escalation', [ 'datatables' ])
	.config(function($stateProvider) {
				$stateProvider
				.state('main.escalation', {
					url : "/escalation",
					views : {
						"sub" : {
							templateUrl : "templates/escalation/escalation.html",
							controller : "EscalationController as vm"
						}
					}
				})
			});

})();