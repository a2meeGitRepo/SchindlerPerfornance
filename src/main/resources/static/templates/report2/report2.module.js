(function() {
	'use strict';

	angular.module('myApp.report2', [ 'datatables' ])
	.config(function($stateProvider) {
				$stateProvider
				.state('main.report2', {
					url : "/report2",
					views : {
						"sub" : {
							templateUrl : "templates/report2/report2.html",
							controller : "Report2Controller as vm"
						}
					}
				})
			});

})();