(function() {
	'use strict';

	angular.module('myApp.lowPerformance').factory('lowHttpService',
			lowHttpService);

	lowHttpService.$inject = [ '$http', '$q', '_', 'localStorageService',
			'ApiEndpoint' ];

	/* @ngInject */
	function lowHttpService($http, $q, _, localStorageService, ApiEndpoint) {

		var saleUrl = ApiEndpoint.url + "lowPerf"; // User Url

		// Variables
		var users = {};

		var service = {
			lowPefo : lowPefo,
		};

		return service;

		function lowPefo(perfor) {
			return $http.post(saleUrl + '/lowOtdFpyPpm/?criteria='+ perfor.criteria + '&year=' + perfor.year+ '&type=' + perfor.type);
		}
	}
})();
