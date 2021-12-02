(function() {
	'use strict';

	angular
		.module('myApp.supplierstatus')
		.factory('supplierstatusService', supplierstatusService);

	supplierstatusService.$inject = ['supplierstatusHttpService', '$q', 'toastr'];
	
	/* @ngInject */
	function supplierstatusService(supplierstatusHttpService, $q, toastr) {
		var service = {			
			getSupplierstatuss : getSupplierstatuss,
			deleteSupplierstatus : deleteSupplierstatus,
			addSupplierstatus : addSupplierstatus,
			uploadSupplierstatus  : uploadSupplierstatus,
			searchSupplierstatus1 : searchSupplierstatus1
		};
		return service;

		// ***************************************************************

		function getSupplierstatuss() {
			var deferred = $q.defer();
			supplierstatusHttpService.getSupplierstatuss().then(function(response){
				deferred.resolve(response.data);
			}, function(err){
				alert(JSON.stringify(err.statusText));
			});
			return deferred.promise;
		}
		
		function deleteSupplierstatus(supplierstatus){
			var deferred = $q.defer();
			supplierstatusHttpService.deleteSupplierstatus(supplierstatus).then(function(response){
				toastr.success('Supplierstatus Deleted....', 'Succesful !!');
				deferred.resolve(response.data);
			}, function(err){
				toastr.error(err.status+' : '+err.statusText, 'Error !!');
			});
			return deferred.promise;
		}
		
		function addSupplierstatus(supplierstatus){
			var deferred = $q.defer();
			supplierstatusHttpService.addSupplierstatus(supplierstatus).then(function(response){
				toastr.success('Supplierstatus Added....', 'Succesful !!');
				deferred.resolve(response.data);
			}, function(err){
				toastr.error(err.status+' : '+err.statusText, 'Error !!');
			});
			return deferred.promise;
		}

		function uploadSupplierstatus(file){
			debugger;
			var deferred = $q.defer();
			aiHttpService.uploadSupplierstatus(file).then(function(response){
				toastr.success('File Imported Succesful !!');

				deferred.resolve(response.data);
			}, function(err){
				toastr.error(err.status+' : '+err.statusText, 'Error !!');
			});
			return deferred.promise;
		}
		
		function searchSupplierstatus1(supplierstatus){
			var deferred = $q.defer();
			supplierstatusHttpService.searchSupplierstatus1(supplierstatus).then(function(response){
				deferred.resolve(response.data);
			}, function(err){
				alert(JSON.stringify(err.statusText));
			});
			return deferred.promise;
		}
	}

})();