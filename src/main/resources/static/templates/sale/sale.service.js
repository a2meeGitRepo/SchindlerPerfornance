(function() {
	'use strict';

	angular
		.module('myApp.sale')
		.factory('saleService', saleService);

	saleService.$inject = ['saleHttpService', '$q', 'toastr'];
	
	/* @ngInject */
	function saleService(saleHttpService, $q, toastr) {
		var service = {			
			getSales : getSales,
			getSales1 : getSales1,
			getSales2 : getSales2,
			getSale : getSale,
			getSaleByVerified: getSaleByVerified,
			getSaleByNonVerified : getSaleByNonVerified,
			deleteSale : deleteSale,
			addTransfer : addTransfer,
			searchSale : searchSale,
			searchSale1 : searchSale1,
			searchhSale : searchhSale,
			addSale : addSale,
			downloadFile : downloadFile,
			uploadSale : uploadSale,
			sendMail : sendMail,
			sendSms : sendSms,
			delete1Sale : delete1Sale
		};
		return service;

		// ***************************************************************

		
		function sendMail(sale){
			var deferred = $q.defer();
			saleHttpService.sendMail(sale).then(function(response){
				toastr.success('Mail Sent....!', 'Succesful !!');
				deferred.resolve(response.data);
			}, function(err){
				toastr.error(err.status+' : '+err.statusText, 'Error !!');
			});
			return deferred.promise;
		}
		
		function sendSms(sale){
			var deferred = $q.defer();
			saleHttpService.sendSms(sale).then(function(response){
				toastr.success('SMS Sent....!', 'Succesful !!');
				deferred.resolve(response.data);
			}, function(err){
				toastr.error(err.status+' : '+err.statusText, 'Error !!');
			});
			return deferred.promise;
		}
		
		function getSales() {
			var deferred = $q.defer();
			saleHttpService.getSales().then(function(response){
				deferred.resolve(response.data);
			}, function(err){
				alert(JSON.stringify(err.statusText));
			});
			return deferred.promise;
		}
		
		function getSales1() {
			var deferred = $q.defer();
			saleHttpService.getSales1().then(function(response){
				deferred.resolve(response.data);
			}, function(err){
				alert(JSON.stringify(err.statusText));
			});
			return deferred.promise;
		}
		
		
		function getSales2() {
			var deferred = $q.defer();
			saleHttpService.getSales2().then(function(response){
				deferred.resolve(response.data);
			}, function(err){
				alert(JSON.stringify(err.statusText));
			});
			return deferred.promise;
		}
		

		function getSaleByVerified() {
			var deferred = $q.defer();
			saleHttpService.getSaleByVerified().then(function(response){
				deferred.resolve(response.data);
			}, function(err){
				alert(JSON.stringify(err.statusText));
			});
			return deferred.promise;
		}
		

		function getSaleByNonVerified() {
			var deferred = $q.defer();
			saleHttpService.getSaleByNonVerified().then(function(response){
				deferred.resolve(response.data);
			}, function(err){
				alert(JSON.stringify(err.statusText));
			});
			return deferred.promise;
		}
		
		
		function getSale(location_id) {
			var deferred = $q.defer();
			saleHttpService.getSale(location_id).then(function(response){
				deferred.resolve(response.data);
			}, function(err){
				alert(JSON.stringify(err.statusText));
			});
			return deferred.promise;
		}
		
		function deleteSale(sale){
			var deferred = $q.defer();
			saleHttpService.deleteSale(sale).then(function(response){
				toastr.success('Sale Deleted....', 'Succesful !!');
				deferred.resolve(response.data);
			}, function(err){
				toastr.error(err.status+' : '+err.statusText, 'Error !!');
			});
			return deferred.promise;
		}
		
		
		function delete1Sale(sale){
			var deferred = $q.defer();
			saleHttpService.delete1Sale(sale).then(function(response){
				toastr.success('Sale Deleted....', 'Succesful !!');
				deferred.resolve(response.data);
			}, function(err){
				toastr.error(err.status+' : '+err.statusText, 'Error !!');
			});
			return deferred.promise;
		}
		
		
		function downloadFile(){
			var deferred = $q.defer();
			saleHttpService.downloadFile().then(function(response){
				toastr.success('Excel File Download....', 'Succesful !!');
				deferred.resolve(response.data);
			}, function(err){
				toastr.error(err.status+' : '+err.statusText, 'Location Not Found Error !!');
			});
			return deferred.promise;
		}
		
		function addSale(sale){
			var deferred = $q.defer();
			saleHttpService.addSale(sale).then(function(response){
//				debugger;
				deferred.resolve(response.data);
				toastr.success('Sale Added....', 'Succesful !!');
				
				
			}, function(err){
				toastr.error(err.status+' : '+err.statusText, 'Error !!');
			});
			return deferred.promise;
		}
		
		function addTransfer(transfer){
			var deferred = $q.defer();
			saleHttpService.addTransfer(transfer).then(function(response){
				toastr.success('Sale transfered....', 'Succesful !!');
				deferred.resolve(response.data);
			}, function(err){
				toastr.error(err.status+' : '+err.statusText, 'Error !!');
			});
			return deferred.promise;
		}
		
		function searchSale(sale){
			var deferred = $q.defer();
			saleHttpService.searchSale(sale).then(function(response){
				deferred.resolve(response.data);
			}, function(err){
				alert(JSON.stringify(err.statusText));
			});
			return deferred.promise;
		}
		
		
		function searchSale1(sale){
			var deferred = $q.defer();
			saleHttpService.searchSale1(sale).then(function(response){
				deferred.resolve(response.data);
			}, function(err){
				alert(JSON.stringify(err.statusText));
			});
			return deferred.promise;
		}
		
		
		
		function searchhSale(sale){
			var deferred = $q.defer();
			saleHttpService.searchhSale(sale).then(function(response){
				deferred.resolve(response.data);
			}, function(err){
				alert(JSON.stringify(err.statusText));
			});
			return deferred.promise;
		}
		
		function uploadSale(file){
			var deferred = $q.defer();
			saleHttpService.uploadSale(file).then(function(response){
				toastr.success('File Imported Succesful !!');

				deferred.resolve(response.data);
			}, function(err){
				toastr.error(err.status+' : '+err.statusText, 'Error !!');
			});
			return deferred.promise;
		}
	}

})();	