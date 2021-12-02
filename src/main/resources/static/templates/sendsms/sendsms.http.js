(function () {
	'use strict';

	angular
		.module('myApp.sendsms')
		.factory('sendsmsHttpService', sendsmsHttpService);

	sendsmsHttpService.$inject = ['$http', '$q', '_', 'localStorageService', 'ApiEndpoint'];

	/* @ngInject */
	function sendsmsHttpService($http, $q, _, localStorageService, ApiEndpoint) {
		
		var sendsmsUrl = ApiEndpoint.url+"sms";   // User Url
		
		// Variables
		var users = {};

		var service = {
			addSendsms : addSendsms,
			addTransfer : addTransfer,
			getSendsmss : getSendsmss,
			getSendsmss2 : getSendsmss2,
			getSendsmss1 : getSendsmss1,
			getSendsmsByVerified:getSendsmsByVerified,
			getSendsmsByNonVerified:getSendsmsByNonVerified,
			getSendsms : getSendsms,
			searchSendsms : searchSendsms,
			searchSendsms1 : searchSendsms1,
			searchhSendsms : searchhSendsms,
			deleteSendsms : deleteSendsms,
			downloadFile : downloadFile,
			uploadSendsms : uploadSendsms,
			sendMail : sendMail,
			sendSms : sendSms,
			delete1Sendsms : delete1Sendsms
			
		};

		return service;
	
		function sendMail(sendsms){
			
			debugger;
			//console.log(JSON.stringify(user))
			return $http.post(sendsmsUrl+'/sendmail', sendsms);
		}
		
		function sendSms(sms){
			//console.log(JSON.stringify(user))
			
			debugger;
			
			return $http.post(sendsmsUrl+'/send', sms);
		}
		
		function getSendsmss(){
			return $http.get(sendsmsUrl+'/list');
		}
		
		function getSendsmss2(){
			return $http.get(sendsmsUrl+'/list2');
		}
		
		function getSendsmss1(){
			return $http.get(sendsmsUrl+'/list1');
		}
		
		
		
		function getSendsmsByVerified(){
			return $http.get(sendsmsUrl+'/exportbyverified');
		}
		
		
		
		function getSendsmsByNonVerified(){
			return $http.get(sendsmsUrl+'/exportbynonverified');
		}
		
		
		function searchSendsms(sendsms){
			return $http.post(sendsmsUrl+'/search',sendsms);
		}
		
		

		function searchSendsms1(sendsms){
			return $http.post(sendsmsUrl+'/search1',sendsms);
		}
		
		
		function searchhSendsms(sendsms){
			return $http.post(sendsmsUrl+'/getlist',sendsms);
		}
		
		function getSendsms(location_id){
			return $http.get(sendsmsUrl+'/getlist/'+location_id);
		}
		
		function deleteSendsms(sendsms){
			return $http.get(sendsmsUrl+'/delete/'+sendsms.sendsms_id);
		}
				
		
		function delete1Sendsms(sendsms){
			return $http.get(sendsmsUrl+'/permdelete/'+sendsms.sendsms_id);
		}
		
		function downloadFile(){
			return $http.get(sendsmsUrl+'/export');
		}
		
		function addSendsms(sendsms){
			console.log(JSON.stringify(sendsms))
			return $http.post(sendsmsUrl+'/create', sendsms);
		}
		
		function addTransfer(transfer){
			console.log(JSON.stringify(transfer))
			return $http.post(sendsmsUrl+'/transfer', sendsms);
		}
		
		
		function uploadSendsms(file){
			return $http.post(sendsmsUrl+'/uploadFile', file);
		}
	}
})();
