(function() {
	'use strict';

	angular
		.module('myApp.employee')
		.factory('employeeService', employeeService);

	employeeService.$inject = ['employeeHttpService', '$q', 'toastr'];
	
	/* @ngInject */
	function employeeService(employeeHttpService, $q, toastr) {
		var service = {			
			getEmployees : getEmployees,
			deleteEmployee : deleteEmployee,
			addEmployee : addEmployee,
		};
		return service;

		// ***************************************************************

		function getEmployees() {
			var deferred = $q.defer();
			employeeHttpService.getEmployees().then(function(response){
				deferred.resolve(response.data);
			}, function(err){
				alert(JSON.stringify(err.statusText));
			});
			return deferred.promise;
		}
		
		function deleteEmployee(employee){
			var deferred = $q.defer();
			employeeHttpService.deleteEmployee(employee).then(function(response){
				toastr.success('Employee Deleted....', 'Succesful !!');
				deferred.resolve(response.data);
			}, function(err){
				toastr.error(err.status+' : '+err.statusText, 'Error !!');
			});
			return deferred.promise;
		}
		
		function addEmployee(employee){
			var deferred = $q.defer();
			employeeHttpService.addEmployee(employee).then(function(response){
				toastr.success('Employee Added....', 'Succesful !!');
				deferred.resolve(response.data);
			}, function(err){
				toastr.error(err.status+' : '+err.statusText, 'Error !!');
			});
			return deferred.promise;
		}

		
	}

})();