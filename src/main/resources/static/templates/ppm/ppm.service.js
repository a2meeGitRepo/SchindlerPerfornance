(function() {
	'use strict';

	angular
		.module('myApp.ppm')
		.factory('ppmService', ppmService);

	ppmService.$inject = ['ppmHttpService', '$q', 'toastr'];
	
	/* @ngInject */
	function ppmService(ppmHttpService, $q, toastr) {
		var service = {			
			getPpms : getPpms,
			getPpm2s : getPpm2s,
			deletePpm : deletePpm,
			addPpm : addPpm,
			addPpm2 : addPpm2,
			uploadPpm  : uploadPpm,
			searchPpm1 : searchPpm1,
			getAvg : getAvg,
			getPpmMonthwise : getPpmMonthwise,
			/*--------------------unique serch data---------------------*/
			getUniques : getUniques
			/*--------------------unique serch data---------------------*/
		};
		return service;

		// ***************************************************************

		function getAvg() {
			var deferred = $q.defer();
			ppmHttpService.getAvg().then(function(response){
				deferred.resolve(response.data);
			}, function(err){
				alert(JSON.stringify(err.statusText));
			});
			return deferred.promise;
		}
		

		function getPpmMonthwise() {
			var deferred = $q.defer();
			ppmHttpService.getPpmMonthwise().then(function(response){
				deferred.resolve(response.data);
			}, function(err){
				alert(JSON.stringify(err.statusText));
			});
			return deferred.promise;
		}
		
		
		function getPpms() {
			var deferred = $q.defer();
			ppmHttpService.getPpms().then(function(response){
				deferred.resolve(response.data);
			}, function(err){
				alert(JSON.stringify(err.statusText));
			});
			return deferred.promise;
		}

		
		function getPpm2s() {
			var deferred = $q.defer();
			ppmHttpService.getPpm2s().then(function(response){
				deferred.resolve(response.data);
			}, function(err){
				alert(JSON.stringify(err.statusText));
			});
			return deferred.promise;
		}
		
		function deletePpm(ppm){
			var deferred = $q.defer();
			ppmHttpService.deletePpm(ppm).then(function(response){
				toastr.success('Ppm Deleted....', 'Succesful !!');
				deferred.resolve(response.data);
			}, function(err){
				toastr.error(err.status+' : '+err.statusText, 'Error !!');
			});
			return deferred.promise;
		}
		
		function addPpm(ppm){
			var deferred = $q.defer();
			ppmHttpService.addPpm(ppm).then(function(response){
				toastr.success('Ppm Added....', 'Succesful !!');
				deferred.resolve(response.data);
			}, function(err){
				toastr.error(err.status+' : '+err.statusText, 'Error !!');
			});
			return deferred.promise;
		}
		
		function addPpm2(ppm2){
			debugger;
			var deferred = $q.defer();
			ppmHttpService.addPpm2(ppm2).then(function(response){
				toastr.success('Ppm2 Added....', 'Succesful !!');
				deferred.resolve(response.data);
			}, function(err){
				toastr.error(err.status+' : '+err.statusText, 'Error !!');
			});
			return deferred.promise;
		}


		function uploadPpm(file){
			debugger;
			var deferred = $q.defer();
			aiHttpService.uploadPpm(file).then(function(response){
				toastr.success('File Imported Succesful !!');

				deferred.resolve(response.data);
			}, function(err){
				toastr.error(err.status+' : '+err.statusText, 'Error !!');
			});
			return deferred.promise;
		}

		function searchPpm1(ppm){
			var deferred = $q.defer();
			ppmHttpService.searchPpm1(ppm).then(function(response){
				deferred.resolve(response.data);
			}, function(err){
				alert(JSON.stringify(err.statusText));
			});
			return deferred.promise;
		}
		

		/*--------------------unique serch data---------------------*/
		function getUniques() {
			var deferred = $q.defer();
			ppmHttpService.getUniques().then(function(response){
				deferred.resolve(response.data);
			}, function(err){
				alert(JSON.stringify(err.statusText));
			});
			return deferred.promise;
		}
		/*--------------------unique serch data---------------------*/
	}

})();