(function () {
	'use strict';

	angular
		.module('myApp.user')
		.factory('userHttpService', userHttpService);

	userHttpService.$inject = ['$http', '$q', '_', 'localStorageService', 'ApiEndpoint'];

	/* @ngInject */
	function userHttpService($http, $q, _, localStorageService, ApiEndpoint) {
		
		var user = localStorageService.get(ApiEndpoint.userKey);
		var userUrl = ApiEndpoint.url+"user";   // User Url
		
		// Variables
		var users = {};

		var service = {
			addUser : addUser,
			sendMail : sendMail,
			sendSms : sendSms,
			getUsers : getUsers,
			deleteUser : deleteUser,
			getRoles : getRoles,
			getUserTypes : getUserTypes
		};

		return service;
	
		function getRoles(){
			return $http.get(userUrl+'/role');
		}
		
		function getUserTypes(){
			return $http.get(userUrl+'/user_type');
		}
		
		function getUsers(){
			return $http.get(userUrl+'/list');
		}
		
		function deleteUser(user){
			return $http.get(userUrl+'/delete/'+user.user_id);
		}
		
		function addUser(user){
			//console.log(JSON.stringify(user))
			debugger;
			
			return $http.post(userUrl+'/create', user);
		}
		function sendMail(user){
			//console.log(JSON.stringify(user))
			return $http.post(userUrl+'/sendmail', user);
		}
		
		function sendSms(user){
			//console.log(JSON.stringify(user))
			return $http.post(userUrl+'/sendsms', user);
		}
		
	}
})();
