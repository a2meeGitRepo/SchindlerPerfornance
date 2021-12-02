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
			getUsers : getUsers,
		};

		return service;
	
		
		function getUsers(){
			return $http.get(userUrl+'/list1');
		}
		
		
		
		function addUser(user){
			debugger;
			return $http.post(userUrl+'/create', user);
		}
		/*function addUser(user){
			console.log(JSON.stringify(user))
			return $http.post(userUrl+'/create', user);
		}*/
		
	}
})();
