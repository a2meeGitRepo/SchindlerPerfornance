(function() {
	'use strict';

	angular
		.module('myApp.suppliertype')
		.factory('suppliertypeService', suppliertypeService);

	suppliertypeService.$inject = ['suppliertypeHttpService', '$q', 'toastr'];
	
	/* @ngInject */
	function suppliertypeService(suppliertypeHttpService, $q, toastr) {
		var service = {			
			getSuppliertypes : getSuppliertypes,
			deleteSuppliertype : deleteSuppliertype,
			addSuppliertype : addSuppliertype,
		};
		return service;

		// ***************************************************************

		function getSuppliertypes() {
			var deferred = $q.defer();
			suppliertypeHttpService.getSuppliertypes().then(function(response){
				deferred.resolve(response.data);
			}, function(err){
				alert(JSON.stringify(err.statusText));
			});
			return deferred.promise;
		}
		
		function deleteSuppliertype(suppliertype){
			var deferred = $q.defer();
			suppliertypeHttpService.deleteSuppliertype(suppliertype).then(function(response){
				toastr.success('Suppliertype Deleted....', 'Succesful !!');
				deferred.resolve(response.data);
			}, function(err){
				toastr.error(err.status+' : '+err.statusText, 'Error !!');
			});
			return deferred.promise;
		}
		
		function addSuppliertype(suppliertype){
			var deferred = $q.defer();
			suppliertypeHttpService.addSuppliertype(suppliertype).then(function(response){
				toastr.success('Suppliertype Added....', 'Succesful !!');
				deferred.resolve(response.data);
			}, function(err){
				toastr.error(err.status+' : '+err.statusText, 'Error !!');
			});
			return deferred.promise;
		}

		
	}

})();