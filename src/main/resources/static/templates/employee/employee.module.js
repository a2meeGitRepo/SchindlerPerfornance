(function() {
	'use strict';

	angular
	.module('myApp.employee', [ 'datatables' ])
	.config(function($stateProvider) {
				$stateProvider
				.state('main.employee', {
					url : "/employee",
					views : {
						"sub" : {
							templateUrl : "templates/employee/employee.html",
							controller : "EmployeeController as vm"
						}
					}
				})
			});

})();