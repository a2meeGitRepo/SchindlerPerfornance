(function() {
	'use strict';

	angular
		.module('myApp.otd')
		.factory('otdService', otdService);

	otdService.$inject = ['otdHttpService', '$q', 'toastr'];
	
	/* @ngInject */
	function otdService(otdHttpService, $q, toastr) {
		var service = {			
			getOtds : getOtds,
			getAvg : getAvg,
			getAvg3 : getAvg3,
			deleteOtd : deleteOtd,
			addOtd : addOtd,
			uploadOtd  : uploadOtd,
			searchOtd1 : searchOtd1,
			getOtdMonthwise : getOtdMonthwise,
			/*--------------------unique serch data---------------------*/
			getUniques : getUniques,
			/*--------------------unique serch data---------------------*/
			lowPerLastMonth : lowPerLastMonth,
			editOtd:editOtd,
		};
		return service;

		// ***************************************************************

		function lowPerLastMonth(monthCol,backYear) {
			var deferred = $q.defer();
			otdHttpService.lowPerLastMonth(monthCol,backYear).then(function(response){
				deferred.resolve(response.data);
			}, function(err){
				alert(JSON.stringify(err.statusText));
			});
			return deferred.promise;
		}
		
		function getAvg() {
			var deferred = $q.defer();
			otdHttpService.getAvg().then(function(response){
				deferred.resolve(response.data);
			}, function(err){
				alert(JSON.stringify(err.statusText));
			});
			return deferred.promise;
		}
		
		function getOtdMonthwise() {
			var deferred = $q.defer();
			otdHttpService.getOtdMonthwise().then(function(response){
				deferred.resolve(response.data);
			}, function(err){
				alert(JSON.stringify(err.statusText));
			});
			return deferred.promise;
		}
		
		
		
		
		
		function getAvg3() {
			var deferred = $q.defer();
			otdHttpService.getAvg3().then(function(response){
				deferred.resolve(response.data);
			}, function(err){
				alert(JSON.stringify(err.statusText));
			});
			return deferred.promise;
		}
		
		function getOtds() {
			var deferred = $q.defer();
			otdHttpService.getOtds().then(function(response){
				deferred.resolve(response.data);
			}, function(err){
				alert(JSON.stringify(err.statusText));
			});
			return deferred.promise;
		}
		
		
		function deleteOtd(otd){
			var deferred = $q.defer();
			otdHttpService.deleteOtd(otd).then(function(response){
				toastr.success('Otd Deleted....', 'Succesful !!');
				deferred.resolve(response.data);
			}, function(err){
				toastr.error(err.status+' : '+err.statusText, 'Error !!');
			});
			return deferred.promise;
		}
		
		function editOtd(otd){
			var deferred = $q.defer();
			otdHttpService.editOtd(otd).then(function(response){
				toastr.success('Otd Edited....', 'Succesful !!');
				deferred.resolve(response.data);
			}, function(err){
				toastr.error(err.status+' : '+err.statusText, 'Error !!');
			});
			return deferred.promise;
		}

		function addOtd(otd){
			var deferred = $q.defer();
			otdHttpService.addOtd(otd).then(function(response){
				toastr.success('Otd Added....', 'Succesful !!');
				deferred.resolve(response.data);
			}, function(err){
				toastr.error(err.status+' : '+err.statusText, 'Error !!');
			});
			return deferred.promise;
		}

		function uploadOtd(file){
			debugger;
			var deferred = $q.defer();
			aiHttpService.uploadOtd(file).then(function(response){
				toastr.success('File Imported Succesful !!');

				deferred.resolve(response.data);
			}, function(err){
				toastr.error(err.status+' : '+err.statusText, 'Error !!');
			});
			return deferred.promise;
		}
		

		function searchOtd1(otd){
			var deferred = $q.defer();
			otdHttpService.searchOtd1(otd).then(function(response){
				deferred.resolve(response.data);
			}, function(err){
				alert(JSON.stringify(err.statusText));
			});
			return deferred.promise;
		}
		
		

		/*--------------------unique serch data---------------------*/
		function getUniques() {
			var deferred = $q.defer();
			otdHttpService.getUniques().then(function(response){
				deferred.resolve(response.data);
			}, function(err){
				alert(JSON.stringify(err.statusText));
			});
			return deferred.promise;
		}
		/*--------------------unique serch data---------------------*/
		
	}

})();