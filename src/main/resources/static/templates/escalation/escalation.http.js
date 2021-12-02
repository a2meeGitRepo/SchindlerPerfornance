(function() {
	'use strict';

	angular.module('myApp.escalation').factory('escalationHttpService',
			escalationHttpService);

	escalationHttpService.$inject = [ '$http', '$q', '_',
			'localStorageService', 'ApiEndpoint' ];

	/* @ngInject */
	function escalationHttpService($http, $q, _, localStorageService,
			ApiEndpoint) {

		var user = localStorageService.get(ApiEndpoint.userKey);
		var escalationUrl = ApiEndpoint.url + "escalation"; // User Url

		// Variables
		var users = {};

		var service = {
			addEscalation : addEscalation,
			getEscalations : getEscalations,
			deleteEscalation : deleteEscalation,
			searchEscalation : searchEscalation,
			sendMail : sendMail,
			getEscalationMonthwise : getEscalationMonthwise,
			getExisting : getExisting
		};

		return service;

		function getEscalationMonthwise() {
			return $http.get(escalationUrl + '/monthlyreport');
		}

		function sendMail(escalation) {
			return $http.post(escalationUrl + '/sendmail', escalation);
		}

		function searchEscalation(escalation) {
			return $http.post(escalationUrl + '/search1', escalation);
		}

		function getExisting(escalation) {
			return $http.get(escalationUrl + '/getExistingList', escalation);
		}

		function getEscalations() {
			return $http.get(escalationUrl + '/list');
		}

		function deleteEscalation(escalation) {
			return $http.get(escalationUrl + '/delete/'+ escalation.escalation_id);
		}

		function addEscalation(escalation) {
			return $http.post(escalationUrl + '/create', escalation);
		}
	}
})();
