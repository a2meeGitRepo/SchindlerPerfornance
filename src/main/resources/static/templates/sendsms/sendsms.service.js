(function() {
	'use strict';

	angular
		.module('myApp.sendsms')
		.factory('sendsmsService', sendsmsService);

	sendsmsService.$inject = ['sendsmsHttpService', '$q', 'toastr'];
	
	/* @ngInject */
	function sendsmsService(sendsmsHttpService, $q, toastr) {
		var service = {			
			getSendsmss : getSendsmss,
			getSendsmss1 : getSendsmss1,
			getSendsmss2 : getSendsmss2,
			getSendsms : getSendsms,
			getSendsmsByVerified: getSendsmsByVerified,
			getSendsmsByNonVerified : getSendsmsByNonVerified,
			deleteSendsms : deleteSendsms,
			addTransfer : addTransfer,
			searchSendsms : searchSendsms,
			searchSendsms1 : searchSendsms1,
			searchhSendsms : searchhSendsms,
			addSendsms : addSendsms,
			downloadFile : downloadFile,
			uploadSendsms : uploadSendsms,
			sendMail : sendMail,
			sendSms : sendSms,
			delete1Sendsms : delete1Sendsms,
			sendSms : sendSms
		};
		return service;

		// ***************************************************************

		
		function sendMail(sendsms){
			var deferred = $q.defer();
			sendsmsHttpService.sendMail(sendsms).then(function(response){
				toastr.success('Mail Sent....!', 'Succesful !!');
				deferred.resolve(response.data);
			}, function(err){
				toastr.error(err.status+' : '+err.statusText, 'Error !!');
			});
			return deferred.promise;
		}
		
		function sendSms(sms){
			var deferred = $q.defer();
			sendsmsHttpService.sendSms(sms).then(function(response){
				toastr.success('SMS Sent....!', 'Succesful !!');
				deferred.resolve(response.data);
			}, function(err){
				toastr.error(err.status+' : '+err.statusText, 'Error !!');
			});
			return deferred.promise;
		}
		
		function getSendsmss() {
			var deferred = $q.defer();
			sendsmsHttpService.getSendsmss().then(function(response){
				deferred.resolve(response.data);
			}, function(err){
				alert(JSON.stringify(err.statusText));
			});
			return deferred.promise;
		}
		
		function getSendsmss1() {
			var deferred = $q.defer();
			sendsmsHttpService.getSendsmss1().then(function(response){
				deferred.resolve(response.data);
			}, function(err){
				alert(JSON.stringify(err.statusText));
			});
			return deferred.promise;
		}
		
		
		function getSendsmss2() {
			var deferred = $q.defer();
			sendsmsHttpService.getSendsmss2().then(function(response){
				deferred.resolve(response.data);
			}, function(err){
				alert(JSON.stringify(err.statusText));
			});
			return deferred.promise;
		}
		

		function getSendsmsByVerified() {
			var deferred = $q.defer();
			sendsmsHttpService.getSendsmsByVerified().then(function(response){
				deferred.resolve(response.data);
			}, function(err){
				alert(JSON.stringify(err.statusText));
			});
			return deferred.promise;
		}
		

		function getSendsmsByNonVerified() {
			var deferred = $q.defer();
			sendsmsHttpService.getSendsmsByNonVerified().then(function(response){
				deferred.resolve(response.data);
			}, function(err){
				alert(JSON.stringify(err.statusText));
			});
			return deferred.promise;
		}
		
		
		function getSendsms(location_id) {
			var deferred = $q.defer();
			sendsmsHttpService.getSendsms(location_id).then(function(response){
				deferred.resolve(response.data);
			}, function(err){
				alert(JSON.stringify(err.statusText));
			});
			return deferred.promise;
		}
		
		function deleteSendsms(sendsms){
			var deferred = $q.defer();
			sendsmsHttpService.deleteSendsms(sendsms).then(function(response){
				toastr.success('Sendsms Deleted....', 'Succesful !!');
				deferred.resolve(response.data);
			}, function(err){
				toastr.error(err.status+' : '+err.statusText, 'Error !!');
			});
			return deferred.promise;
		}
		
		
		function delete1Sendsms(sendsms){
			var deferred = $q.defer();
			sendsmsHttpService.delete1Sendsms(sendsms).then(function(response){
				toastr.success('Sendsms Deleted....', 'Succesful !!');
				deferred.resolve(response.data);
			}, function(err){
				toastr.error(err.status+' : '+err.statusText, 'Error !!');
			});
			return deferred.promise;
		}
		
		
		function downloadFile(){
			var deferred = $q.defer();
			sendsmsHttpService.downloadFile().then(function(response){
				toastr.success('Excel File Download....', 'Succesful !!');
				deferred.resolve(response.data);
			}, function(err){
				toastr.error(err.status+' : '+err.statusText, 'Location Not Found Error !!');
			});
			return deferred.promise;
		}
		
		function addSendsms(sendsms){
			var deferred = $q.defer();
			sendsmsHttpService.addSendsms(sendsms).then(function(response){
//				debugger;
				deferred.resolve(response.data);
				toastr.success('Sendsms Added....', 'Succesful !!');
				
				
			}, function(err){
				toastr.error(err.status+' : '+err.statusText, 'Error !!');
			});
			return deferred.promise;
		}
		
		function addTransfer(transfer){
			var deferred = $q.defer();
			sendsmsHttpService.addTransfer(transfer).then(function(response){
				toastr.success('Sendsms transfered....', 'Succesful !!');
				deferred.resolve(response.data);
			}, function(err){
				toastr.error(err.status+' : '+err.statusText, 'Error !!');
			});
			return deferred.promise;
		}
		
		function searchSendsms(sendsms){
			var deferred = $q.defer();
			sendsmsHttpService.searchSendsms(sendsms).then(function(response){
				deferred.resolve(response.data);
			}, function(err){
				alert(JSON.stringify(err.statusText));
			});
			return deferred.promise;
		}
		
		
		function searchSendsms1(sendsms){
			var deferred = $q.defer();
			sendsmsHttpService.searchSendsms1(sendsms).then(function(response){
				deferred.resolve(response.data);
			}, function(err){
				alert(JSON.stringify(err.statusText));
			});
			return deferred.promise;
		}
		
		
		
		function searchhSendsms(sendsms){
			var deferred = $q.defer();
			sendsmsHttpService.searchhSendsms(sendsms).then(function(response){
				deferred.resolve(response.data);
			}, function(err){
				alert(JSON.stringify(err.statusText));
			});
			return deferred.promise;
		}
		
		function uploadSendsms(file){
			var deferred = $q.defer();
			sendsmsHttpService.uploadSendsms(file).then(function(response){
				toastr.success('File Imported Succesful !!');

				deferred.resolve(response.data);
			}, function(err){
				toastr.error(err.status+' : '+err.statusText, 'Error !!');
			});
			return deferred.promise;
		}
	}

})();	