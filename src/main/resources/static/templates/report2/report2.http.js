(function() {
	'use strict';

	angular.module('myApp.performance').factory('performanceHttpService',
			performanceHttpService);

	performanceHttpService.$inject = [ '$http', '$q', '_',
			'localStorageService', 'ApiEndpoint' ];

	/* @ngInject */
	function performanceHttpService($http, $q, _, localStorageService,
			ApiEndpoint) {

		var performanceUrl = ApiEndpoint.url + "performance"; // User Url

		// Variables
		var users = {};

		var service = {
			addPerformance : addPerformance,
			getPerformances : getPerformances,
		};

		return service;

		function getPerformances() {
			return $http.get(performanceUrl + '/list1');
		}

		function addPerformance(performance) {
			return $http.post(performanceUrl + '/create', performance);
		}

	}
})();
