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
			addPerformance : addPerformance,
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
		
	}

})();