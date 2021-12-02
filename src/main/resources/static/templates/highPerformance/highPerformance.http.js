(function() {
	'use strict';

	angular.module('myApp.highPerformance').factory('highHttpService',
			highHttpService);

	highHttpService.$inject = [ '$http', '$q', '_', 'localStorageService',
			'ApiEndpoint' ];

	/* @ngInject */
	function highHttpService($http, $q, _, localStorageService, ApiEndpoint) {

		var saleUrl = ApiEndpoint.url + "highPerf"; // User Url

		// Variables
		var users = {};

		var service = {
			highPefo : highPefo,
		};

		return service;

		function highPefo(perfor) {
			return $http.post(saleUrl + '/highOtdFpyPpm/?criteria='+ perfor.criteria + '&year=' + perfor.year+ '&type=' + perfor.type);
		}
	}
})();
