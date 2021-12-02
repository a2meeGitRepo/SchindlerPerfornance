(function () {
	'use strict';

	angular
		.module('myApp.performance')
		.factory('performanceHttpService', performanceHttpService);

	performanceHttpService.$inject = ['$http', '$q', '_', 'localStorageService', 'ApiEndpoint'];

	/* @ngInject */
	function performanceHttpService($http, $q, _, localStorageService, ApiEndpoint) {
		
		var user = localStorageService.get(ApiEndpoint.userKey);
		var performanceUrl = ApiEndpoint.url+"performance";   // User Url
		
		// Variables
		var users = {};

		var service = {
			addPerformance : addPerformance,
			getPerformances : getPerformances,
			deletePerformance : deletePerformance,
			sendPerformance : sendPerformance,
			sendPerformance2 : sendPerformance2,
			addPerformance1 : addPerformance1,
			getPerformances1 : getPerformances1,
			getPerformances2 : getPerformances2,
			searchPerformance : searchPerformance,
		};

		return service;
	
		
		function getPerformances(){
			return $http.get(performanceUrl+'/list');
		}

		
		function searchPerformance(per){
		
			return $http.get(performanceUrl+'/finalList?year='+per.performance_year);
		}

		
		function getPerformances1(){
			return $http.get(performanceUrl+'/highmonth');
		}
		
		function lowPerLastMonth(backMonth,backYear){
			return $http.get(performanceUrl+'/lowper/'+backMonth+'/'+backYear);
		}
		
		
		function getPerformances2(){
			return $http.get(performanceUrl+'/highOcc');
		}
		
		
		function deletePerformance(performance){
			return $http.get(performanceUrl+'/delete/'+performance.performance_id);
		}
		
		function addPerformance(performance){
			//console.log(JSON.stringify(user))
			return $http.post(performanceUrl+'/create', performance);
		}
		
		function addPerformance1(performance){
			//console.log(JSON.stringify(user))
			return $http.post(performanceUrl+'/create1', performance);
		}
		
		function sendPerformance2(performance){
			//console.log(JSON.stringify(user))
			return $http.post(performanceUrl+'/sendsms', performance);
		}
		

		function sendPerformance(performance){
			//console.log(JSON.stringify(user))
			return $http.post(performanceUrl+'/sendmail', performance);
		}
		
	}
})();
