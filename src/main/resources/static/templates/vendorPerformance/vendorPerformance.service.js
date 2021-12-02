(function() {
	'use strict';

	angular.module('myApp.vendorPerformance').factory('vpService', vpService);

	vpService.$inject = ['vpHttpService', '$q', 'toastr' ];

	/* @ngInject */
	function vpService(vpHttpService, $q, toastr) {
		var service = {
			getSales : getSales,
			getMaxPpm:getMaxPpm,
			addSale : addSale,
			downloadFile : downloadFile,
			vendorPefo : vendorPefo,
			sendMail : sendMail
		};
		return service;

		// ***************************************************************

		
		function sendMail(otd){	
			var deferred = $q.defer();
			vpHttpService.sendMail(otd).then(function(response){
				
				console.log("response object"+JSON.stringify(response))
			
				toastr.success(response.data.message);
				deferred.resolve(response.data);
			}, function(err){
				toastr.error(err.status+' : '+err.statusText, 'Error !!');
			});
			return deferred.promise;
		}



		function vendorPefo(otd) {
			var deferred = $q.defer();
			vpHttpService.vendorPefo(otd).then(function(response) {
				deferred.resolve(response.data);
			}, function(err) {
				alert(JSON.stringify(err.statusText));
			});
			return deferred.promise;
		}

		function getSales() {
			var deferred = $q.defer();
			vpHttpService.getSales().then(function(response) {
				deferred.resolve(response.data);
			}, function(err) {
				alert(JSON.stringify(err.statusText));
			});
			return deferred.promise;
		}

		function getMaxPpm(supplierCode,supplierType,year) {
			var deferred = $q.defer();
			vpHttpService.getMaxPpm(supplierCode,supplierType,year).then(function(response) {
				deferred.resolve(response.data);
			}, function(err) {
				alert(JSON.stringify(err.statusText));
			});
			return deferred.promise;
		}
		function downloadFile() {
			var deferred = $q.defer();
			vpHttpService.downloadFile().then(
					function(response) {
						toastr.success('Excel File Download....',
								'Succesful !!');
						deferred.resolve(response.data);
					},
					function(err) {
						toastr.error(err.status + ' : ' + err.statusText,
								'Location Not Found Error !!');
					});
			return deferred.promise;
		}

		function addSale(sale) {
			var deferred = $q.defer();
			vpHttpService.addSale(sale).then(function(response) {
				toastr.success('Sale Added....', 'Succesful !!');
				deferred.resolve(response.data);
			}, function(err) {
				toastr.error(err.status + ' : ' + err.statusText, 'Error !!');
			});
			return deferred.promise;
		}

	}

})();