(function() {
	'use strict';

	angular.module('myApp.fpy').factory('fpyService', fpyService);

	fpyService.$inject = [ 'fpyHttpService', '$q', 'toastr' ];

	
	function fpyService(fpyHttpService, $q, toastr) {
		var service = {
			getFpys : getFpys,
			deleteFpy : deleteFpy,
			addFpy : addFpy,
			uploadFpy  : uploadFpy,
			searchFpy1 : searchFpy1,
			getAvg : getAvg,
			getAvg4 : getAvg4,
			getFpyMonthwise : getFpyMonthwise,
			/*--------------------unique serch data---------------------*/
			getUniques : getUniques
			/*--------------------unique serch data---------------------*/
		};
		return service;

		// ***************************************************************

		function getFpyMonthwise() {
			var deferred = $q.defer();
			fpyHttpService.getFpyMonthwise().then(function(response){
				deferred.resolve(response.data);
			}, function(err){
				alert(JSON.stringify(err.statusText));
			});
			return deferred.promise;
		}
		function getAvg() {
			var deferred = $q.defer();
			fpyHttpService.getAvg().then(function(response){
				deferred.resolve(response.data);
			}, function(err){
				alert(JSON.stringify(err.statusText));
			});
			return deferred.promise;
		}

		function getAvg4() {
			var deferred = $q.defer();
			fpyHttpService.getAvg4().then(function(response){
				deferred.resolve(response.data);
			}, function(err){
				alert(JSON.stringify(err.statusText));
			});
			return deferred.promise;
		}
		function getFpys() {
			var deferred = $q.defer();
			fpyHttpService.getFpys().then(function(response) {
				deferred.resolve(response.data);
			}, function(err) {
				alert(JSON.stringify(err.statusText));
			});
			return deferred.promise;
		}

		function deleteFpy(fpy) {
			var deferred = $q.defer();
			fpyHttpService.deleteFpy(fpy).then(function(response) {
				toastr.success('Fpy Deleted....', 'Succesful !!');
				deferred.resolve(response.data);
			}, function(err) {
				toastr.error(err.status + ' : ' + err.statusText, 'Error !!');
			});
			return deferred.promise;
		}

		function addFpy(fpy) {
			var deferred = $q.defer();
			fpyHttpService.addFpy(fpy).then(function(response) {
				toastr.success('Fpy Added....', 'Succesful !!');
				deferred.resolve(response.data);
			}, function(err) {
				toastr.error(err.status + ' : ' + err.statusText, 'Error !!');
			});
			return deferred.promise;
		}
		
		function uploadFpy(file){
			debugger;
			var deferred = $q.defer();
			aiHttpService.uploadFpy(file).then(function(response){
				toastr.success('File Imported Succesful !!');

				deferred.resolve(response.data);
			}, function(err){
				toastr.error(err.status+' : '+err.statusText, 'Error !!');
			});
			return deferred.promise;
		}
		

		function searchFpy1(fpy){
			var deferred = $q.defer();
			fpyHttpService.searchFpy1(fpy).then(function(response){
				deferred.resolve(response.data);
			}, function(err){
				alert(JSON.stringify(err.statusText));
			});
			return deferred.promise;
		}
		
		

		/*--------------------unique serch data---------------------*/
		function getUniques() {
			var deferred = $q.defer();
			fpyHttpService.getUniques().then(function(response){
				deferred.resolve(response.data);
			}, function(err){
				alert(JSON.stringify(err.statusText));
			});
			return deferred.promise;
		}
		/*--------------------unique serch data---------------------*/
	}

})();