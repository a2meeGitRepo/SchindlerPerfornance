(function () {
	'use strict';

	angular
		.module('myApp.suppliertype')
		.factory('suppliertypeHttpService', suppliertypeHttpService);

	suppliertypeHttpService.$inject = ['$http', '$q', '_', 'localStorageService', 'ApiEndpoint'];

	/* @ngInject */
	function suppliertypeHttpService($http, $q, _, localStorageService, ApiEndpoint) {
		
		var user = localStorageService.get(ApiEndpoint.userKey);
		var suppliertypeUrl = ApiEndpoint.url+"suppliertype";   // User Url
		
		// Variables
		var users = {};

		var service = {
			addSuppliertype : addSuppliertype,
			getSuppliertypes : getSuppliertypes,
			deleteSuppliertype : deleteSuppliertype,
		};

		return service;
	
		
		function getSuppliertypes(){
			return $http.get(suppliertypeUrl+'/list');
		}
		
		function deleteSuppliertype(suppliertype){
			return $http.get(suppliertypeUrl+'/delete/'+suppliertype.suppliertype_id);
		}
		
		function addSuppliertype(suppliertype){
			//console.log(JSON.stringify(user))
			return $http.post(suppliertypeUrl+'/create', suppliertype);
		}
		
	}
})();
