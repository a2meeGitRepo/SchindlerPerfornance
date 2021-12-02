(function () {
	'use strict';

	angular
		.module('myApp.supplier')
		.factory('supplierHttpService', supplierHttpService);

	supplierHttpService.$inject = ['$http', '$q', '_', 'localStorageService', 'ApiEndpoint'];

	/* @ngInject */
	function supplierHttpService($http, $q, _, localStorageService, ApiEndpoint) {
		
		var user = localStorageService.get(ApiEndpoint.userKey);
		var supplierUrl = ApiEndpoint.url+"supplier";   // User Url
		
		// Variables
		var users = {};

		var service = {
			addSupplier : addSupplier,
			getSuppliers : getSuppliers,
			deleteSupplier : deleteSupplier,
			searchSupplier1 : searchSupplier1,
			uploadSupplier : uploadSupplier,
			/*--------------------unique serch data---------------------*/
			getUniques : getUniques
			/*--------------------unique serch data---------------------*/
		};

		return service;
	
		
		function getSuppliers(){
			return $http.get(supplierUrl+'/list');
		}
		
		/*--------------------unique serch data---------------------*/
		function getUniques(){
			return $http.get(supplierUrl+'/uniq');
		}
		/*--------------------unique serch data---------------------*/
		
		function deleteSupplier(supplier){
			return $http.get(supplierUrl+'/delete/'+supplier.supplier_id);
		}
		
		function addSupplier(supplier){
			//console.log(JSON.stringify(user))
			return $http.post(supplierUrl+'/create', supplier);
		}

		function searchSupplier1(supplier){
			return $http.post(supplierUrl+'/search1',supplier);
		}

		function uploadSupplier(file){
			debugger;
			return $http.post(supplierUrl+'/uploadFile', file);
		}
		
	}
})();
