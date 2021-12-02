(function() {
	'use strict';

	angular.module('myApp.otd').factory('otdHttpService', otdHttpService);

	otdHttpService.$inject = [ '$http', '$q', '_', 'localStorageService',
			'ApiEndpoint' ];

	/* @ngInject */
	function otdHttpService($http, $q, _, localStorageService, ApiEndpoint) {

		var user = localStorageService.get(ApiEndpoint.userKey);
		var otdUrl = ApiEndpoint.url + "otd"; // User Url

		// Variables
		var users = {};

		var service = {
			addOtd : addOtd,
			getOtds : getOtds,
			deleteOtd : deleteOtd,
			uploadOtd : uploadOtd,
			searchOtd1 : searchOtd1,
			getAvg : getAvg,
			getOtdMonthwise : getOtdMonthwise,
			getAvg3 : getAvg3,
		/*--------------------unique serch data---------------------*/
			getUniques : getUniques,
		/*--------------------unique serch data---------------------*/
			lowPerLastMonth : lowPerLastMonth,
			editOtd:editOtd,
		};

		return service;

		function lowPerLastMonth(monthCol,backYear){
			return $http.get(otdUrl+'/lowper/'+monthCol+'/'+backYear);
		}
		
		function getOtds() {
			return $http.get(otdUrl + '/list');
		}

		function getOtdMonthwise() {
			return $http.get(otdUrl + '/monthlyreport');
		}

		function getAvg() {
			return $http.get(otdUrl + '/avg');
		}
		
		function getAvg3() {
			return $http.get(otdUrl + '/avg3');
		}

		function deleteOtd(otd) {
			return $http.get(otdUrl + '/delete/' + otd.otd_id);
		}

		function editOtd(otd) {
			return $http.post(otdUrl + '/edit', otd);
		}
		function addOtd(otd) {
			return $http.post(otdUrl + '/create', otd);
		}
		function uploadOtd(file) {
			return $http.post(otdUrl + '/uploadFile', file);
		}

		function searchOtd1(otd) {
			return $http.post(otdUrl + '/search1', otd);
		}

		/*--------------------unique serch data---------------------*/
		function getUniques() {
			return $http.get(otdUrl + '/uniq');
		}
		/*--------------------unique serch data---------------------*/

	}
})();
