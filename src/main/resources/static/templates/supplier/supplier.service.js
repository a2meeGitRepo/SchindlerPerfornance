(function() {
	'use strict';

	angular
		.module('myApp.supplier')
		.factory('supplierService', supplierService);

	supplierService.$inject = ['supplierHttpService', '$q', 'toastr'];
	
	/* @ngInject */
	function supplierService(supplierHttpService, $q, toastr) {
		var service = {			
			getSuppliers : getSuppliers,
			deleteSupplier : deleteSupplier,
			addSupplier : addSupplier,
			searchSupplier1 : searchSupplier1,
			uploadSupplier  : uploadSupplier,
			/*--------------------unique serch data---------------------*/
			getUniques : getUniques
			/*--------------------unique serch data---------------------*/
		};
		return service;

		// ***************************************************************

		function getSuppliers() {
			var deferred = $q.defer();
			supplierHttpService.getSuppliers().then(function(response){
				deferred.resolve(response.data);
			}, function(err){
				alert(JSON.stringify(err.statusText));
			});
			return deferred.promise;
		}
		
		/*--------------------unique serch data---------------------*/
		function getUniques() {
			var deferred = $q.defer();
			supplierHttpService.getUniques().then(function(response){
				deferred.resolve(response.data);
			}, function(err){
				alert(JSON.stringify(err.statusText));
			});
			return deferred.promise;
		}
		/*--------------------unique serch data---------------------*/
		
		function deleteSupplier(supplier){
			var deferred = $q.defer();
			supplierHttpService.deleteSupplier(supplier).then(function(response){
				toastr.success('Supplier Deleted....', 'Succesful !!');
				deferred.resolve(response.data);
			}, function(err){
				toastr.error(err.status+' : '+err.statusText, 'Error !!');
			});
			return deferred.promise;
		}
		
		function addSupplier(supplier){
			var deferred = $q.defer();
			supplierHttpService.addSupplier(supplier).then(function(response){
				toastr.success('Supplier Added....', 'Succesful !!');
				deferred.resolve(response.data);
			}, function(err){
				toastr.error(err.status+' : '+err.statusText, 'Error !!');
			});
			return deferred.promise;
		}

		function searchSupplier1(supplier){
			var deferred = $q.defer();
			supplierHttpService.searchSupplier1(supplier).then(function(response){
				deferred.resolve(response.data);
			}, function(err){
				alert(JSON.stringify(err.statusText));
			});
			return deferred.promise;
		}
		

		function uploadSupplier(file){
			debugger;
			var deferred = $q.defer();
			aiHttpService.uploadSupplier(file).then(function(response){
				toastr.success('File Imported Succesful !!');

				deferred.resolve(response.data);
			}, function(err){
				toastr.error(err.status+' : '+err.statusText, 'Error !!');
			});
			return deferred.promise;
		}
	}

})();