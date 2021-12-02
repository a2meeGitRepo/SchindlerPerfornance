(function() {
	'use strict';

	angular
		.module('myApp.registration_form')
		.factory('registrationService', registrationService);

	registrationService.$inject = ['dataService'];
	
	/* @ngInject */
	function registrationService(dataService) {
		var service = {			
			addUser : addUser		
		};
		return service;

		// ***************************************************************

		function addUser(user){
			dataService.addUser(user);
		}
		
	}

})();