(function () {
	'use strict';

	angular
		.module('myApp.employee')
		.factory('employeeHttpService', employeeHttpService);

	employeeHttpService.$inject = ['$http', '$q', '_', 'localStorageService', 'ApiEndpoint'];

	/* @ngInject */
	function employeeHttpService($http, $q, _, localStorageService, ApiEndpoint) {
		
		var user = localStorageService.get(ApiEndpoint.userKey);
		var employeeUrl = ApiEndpoint.url+"employee";   // User Url
		
		// Variables
		var users = {};

		var service = {
			addEmployee : addEmployee,
			getEmployees : getEmployees,
			deleteEmployee : deleteEmployee,
		};

		return service;
	
		
		function getEmployees(){
			return $http.get(employeeUrl+'/list');
		}
		
		function deleteEmployee(employee){
			return $http.get(employeeUrl+'/delete/'+employee.employee_id);
		}
		
		function addEmployee(employee){
			//console.log(JSON.stringify(user))
			return $http.post(employeeUrl+'/create', employee);
		}
		
	}
})();
