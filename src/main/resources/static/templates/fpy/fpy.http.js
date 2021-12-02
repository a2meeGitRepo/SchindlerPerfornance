(function() {
	'use strict';

	angular.module('myApp.fpy').factory('fpyHttpService', fpyHttpService);

	fpyHttpService.$inject = [ '$http', '$q', '_', 'localStorageService',
			'ApiEndpoint' ];

	function fpyHttpService($http, $q, _, localStorageService, ApiEndpoint) {

		var user = localStorageService.get(ApiEndpoint.userKey);
		var fpyUrl = ApiEndpoint.url + "fpy";

		var users = {};

		var service = {
			addFpy : addFpy,
			getFpys : getFpys,
			deleteFpy : deleteFpy,
			uploadFpy : uploadFpy,
			searchFpy1 : searchFpy1,
			getAvg : getAvg,
			getAvg4 : getAvg4,
			getFpyMonthwise : getFpyMonthwise,
			/*--------------------unique serch data---------------------*/
			getUniques : getUniques
			/*--------------------unique serch data---------------------*/
		};

		return service;

		function getFpyMonthwise() {
			return $http.get(fpyUrl + '/monthlyreport');
		}

		function getAvg(){
			return $http.get(fpyUrl+'/avg');
		}

		function getAvg4(){
			return $http.get(fpyUrl+'/avg4');
		}
		function getFpys() {
			return $http.get(fpyUrl + '/list');
		}

		function deleteFpy(fpy) {
			return $http.get(fpyUrl + '/delete/' + fpy.fpy_id);
		}

		function addFpy(fpy) {
			return $http.post(fpyUrl + '/create', fpy);
		}

		function uploadFpy(file){
			debugger;
			return $http.post(fpyUrl+'/uploadFile', file);
		}
		


		function searchFpy1(fpy){
			return $http.post(fpyUrl+'/search1',fpy);
		}
		/*--------------------unique serch data---------------------*/
		function getUniques(){
			return $http.get(fpyUrl+'/uniq');
		}
		/*--------------------unique serch data---------------------*/
	}
})();
