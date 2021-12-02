(function() {
	'use strict';

	angular.module('myApp.ppm').factory('ppmHttpService', ppmHttpService);

	ppmHttpService.$inject = [ '$http', '$q', '_', 'localStorageService',
			'ApiEndpoint' ];

	function ppmHttpService($http, $q, _, localStorageService, ApiEndpoint) {

		var user = localStorageService.get(ApiEndpoint.userKey);
		var ppmUrl = ApiEndpoint.url + "ppm";

		var users = {};

		var service = {
			addPpm : addPpm,
			addPpm2 : addPpm2,
			getPpms : getPpms,
			getPpm2s : getPpm2s,
			deletePpm : deletePpm,
			uploadPpm : uploadPpm,
			searchPpm1 : searchPpm1,
			getAvg : getAvg,
			getPpmMonthwise : getPpmMonthwise,
			/*--------------------unique serch data---------------------*/
			getUniques : getUniques
		/*--------------------unique serch data---------------------*/
		};

		return service;

		function getPpms() {
			return $http.get(ppmUrl + '/list');
		}

		function getPpm2s() {
			return $http.get(ppmUrl + '/list2');
		}

		function getPpmMonthwise() {
			return $http.get(ppmUrl + '/monthlyreport');
		}

		function getAvg() {
			return $http.get(ppmUrl + '/avg');
		}

		function deletePpm(ppm) {
			return $http.get(ppmUrl + '/delete/' + ppm.ppm_id);
		}

		function addPpm(ppm) {
			return $http.post(ppmUrl + '/create3', ppm);
		}
		

		function addPpm2(ppm2) {
			debugger;
			return $http.post(ppmUrl + '/create2', ppm2);
		}


		function uploadPpm(file) {
			return $http.post(ppmUrl + '/uploadFile', file);
		}

		function searchPpm1(ppm) {
			return $http.post(ppmUrl + '/search1', ppm);
		}

		/*--------------------unique serch data---------------------*/
		function getUniques() {
			return $http.get(ppmUrl + '/uniq');
		}
		/*--------------------unique serch data---------------------*/

	}
})();
