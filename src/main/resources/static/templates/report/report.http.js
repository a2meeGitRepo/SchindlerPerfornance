(function() {
	'use strict';

	angular.module('myApp.escalation').factory('escalationHttpService',
			escalationHttpService);

	escalationHttpService.$inject = [ '$http', '$q', '_',
			'localStorageService', 'ApiEndpoint' ];

	/* @ngInject */
	function escalationHttpService($http, $q, _, localStorageService,
			ApiEndpoint) {

		var escalationUrl = ApiEndpoint.url + "escalation"; // User Url

		// Variables
		var users = {};

		var service = {
			addEscalation : addEscalation,
			getEscalations : getEscalations,
		};

		return service;

		function getEscalations() {
			return $http.get(escalationUrl + '/list1');
		}

		function addEscalation(escalation) {
			return $http.post(escalationUrl + '/create', escalation);
		}

	}
})();
