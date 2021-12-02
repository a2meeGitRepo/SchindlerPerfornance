(function() {
	'use strict';

	angular.module('myApp.supplierstatus').factory('supplierstatusHttpService', supplierstatusHttpService);

	supplierstatusHttpService.$inject = [ '$http', '$q', '_', 'localStorageService',
			'ApiEndpoint' ];

	function supplierstatusHttpService($http, $q, _, localStorageService, ApiEndpoint) {

		var user = localStorageService.get(ApiEndpoint.userKey);
		var supplierstatusUrl = ApiEndpoint.url + "supplierstatus";

		var users = {};

		var service = {
			addSupplierstatus : addSupplierstatus,
			getSupplierstatuss : getSupplierstatuss,
			deleteSupplierstatus : deleteSupplierstatus,
			uploadSupplierstatus : uploadSupplierstatus,
			searchSupplierstatus1 : searchSupplierstatus1
		};

		return service;

		function getSupplierstatuss() {
			return $http.get(supplierstatusUrl + '/list');
		}

		function deleteSupplierstatus(supplierstatus) {
			return $http.get(supplierstatusUrl + '/delete/' + supplierstatus.supplierstatus_id);
		}

		function addSupplierstatus(supplierstatus) {
			return $http.post(supplierstatusUrl + '/create', supplierstatus);
		}

		function uploadSupplierstatus(file){
			debugger;
			return $http.post(supplierstatusUrl+'/uploadFile', file);
		}
		
		

		function searchSupplierstatus1(supplierstatus){
			return $http.post(supplierstatusUrl+'/search1',supplierstatus);
		}
	}
})();
