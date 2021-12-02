(function() {
	'use strict';

	angular
		.module('myApp.escalation')
		.factory('escalationService', escalationService);

	escalationService.$inject = ['escalationHttpService', '$q', 'toastr'];
	
	/* @ngInject */
	function escalationService(escalationHttpService, $q, toastr) {
		var service = {			
			getEscalations : getEscalations,
			addEscalation : addEscalation,
		};
		return service;

		// ***************************************************************

		function getEscalations() {
			var deferred = $q.defer();
			escalationHttpService.getEscalations().then(function(response){
				deferred.resolve(response.data);
			}, function(err){
				alert(JSON.stringify(err.statusText));
			});
			return deferred.promise;
		}
		
		function addEscalation(escalation){
			var deferred = $q.defer();
			escalationHttpService.addEscalation(escalation).then(function(response){
				toastr.success('Escalation Added....', 'Succesful !!');
				deferred.resolve(response.data);
			}, function(err){
				toastr.error(err.status+' : '+err.statusText, 'Error !!');
			});
			return deferred.promise;
		}
		
	}

})();