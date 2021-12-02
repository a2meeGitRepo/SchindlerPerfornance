(function() {
	'use strict';

	angular
		.module('myApp.performance')
		.factory('performanceService', performanceService);

	performanceService.$inject = ['performanceHttpService', '$q', 'toastr'];
	
	/* @ngInject */
	function performanceService(performanceHttpService, $q, toastr) {
		var service = {			
			getPerformances : getPerformances,
			deletePerformance : deletePerformance,
			addPerformance : addPerformance,
			sendPerformance : sendPerformance, 
			sendPerformance2 : sendPerformance2,
			addPerformance1 : addPerformance1,
			getPerformances1 : getPerformances1,
			getPerformances2 : getPerformances2,
			searchPerformance : searchPerformance,
		};
		return service;

		// ***************************************************************
		
		
		function getPerformances() {
			var deferred = $q.defer();
			performanceHttpService.getPerformances().then(function(response){
				deferred.resolve(response.data);
			}, function(err){
				alert(JSON.stringify(err.statusText));
			});
			return deferred.promise;
		}
		
		
		function searchPerformance(per) {
			var deferred = $q.defer();
			performanceHttpService.searchPerformance(per).then(function(response){
				deferred.resolve(response.data);
			}, function(err){
				alert(JSON.stringify(err.statusText));
			});
			return deferred.promise;
		}
		

		function getPerformances1() {
			var deferred = $q.defer();
			performanceHttpService.getPerformances1().then(function(response){
				deferred.resolve(response.data);
			}, function(err){
				alert(JSON.stringify(err.statusText));
			});
			return deferred.promise;
		}

		function getPerformances2() {
			var deferred = $q.defer();
			performanceHttpService.getPerformances2().then(function(response){
				deferred.resolve(response.data);
			}, function(err){
				alert(JSON.stringify(err.statusText));
			});
			return deferred.promise;
		}
		
		
		function deletePerformance(performance){
			var deferred = $q.defer();
			performanceHttpService.deletePerformance(performance).then(function(response){
				toastr.success('Performance Deleted....', 'Succesful !!');
				deferred.resolve(response.data);
			}, function(err){
				toastr.error(err.status+' : '+err.statusText, 'Error !!');
			});
			return deferred.promise;
		}
		
		function addPerformance(performance){
			var deferred = $q.defer();
			performanceHttpService.addPerformance(performance).then(function(response){
				toastr.success('Performance Added....', 'Succesful !!');
				deferred.resolve(response.data);
			}, function(err){
				toastr.error(err.status+' : '+err.statusText, 'Error !!');
			});
			return deferred.promise;
		}

		
		function addPerformance1(performance){
			var deferred = $q.defer();
			performanceHttpService.addPerformance1(performance).then(function(response){
				toastr.success('Performance Added....', 'Succesful !!');
				deferred.resolve(response.data);
			}, function(err){
				toastr.error(err.status+' : '+err.statusText, 'Error !!');
			});
			return deferred.promise;
		}


		function sendPerformance(performance){
			var deferred = $q.defer();
			performanceHttpService.sendPerformance(performance).then(function(response){
				toastr.success('Performance Added....', 'Succesful !!');
				deferred.resolve(response.data);
			}, function(err){
				toastr.error(err.status+' : '+err.statusText, 'Error !!');
			});
			return deferred.promise;
		}

		
		function sendPerformance2(performance){
			var deferred = $q.defer();
			performanceHttpService.sendPerformance2(performance).then(function(response){
				toastr.success('Performance Added....', 'Succesful !!');
				deferred.resolve(response.data);
			}, function(err){
				toastr.error(err.status+' : '+err.statusText, 'Error !!');
			});
			return deferred.promise;
		}

		
	}

})();