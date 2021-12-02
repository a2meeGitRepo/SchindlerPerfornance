(function() {
	'use strict';

	angular
		.module('myApp.registration_form')
		.controller('RegistrationController', RegistrationController);

		RegistrationController.$inject = ['$rootScope','$scope','$stateParams','$state', 'registrationService'];

	/* @ngInject */
	function RegistrationController($rootScope, $stateParams, $scope, $state, registrationService) {

		var vm = angular.extend(this, {
			submit : submit
		});

		(function activate() {
				
		})();

		// ******************************************************

		function submit(user){
			console.log(JSON.stringify(user));
			registrationService.addUser(user);
		}
	}
})();
