(function() {
	'use strict';

	angular.module('myApp.login_form').factory('loginHttpService',
			loginHttpService);

	loginHttpService.$inject = [ '$http', '$q', '_', 'localStorageService',
			'ApiEndpoint' ];

	function loginHttpService($http, $q, _, localStorageService, ApiEndpoint) {

		var user = localStorageService.get(ApiEndpoint.userKey);
		var userUrl = ApiEndpoint.url + "user";

		var users = {};

		var service = {
			doLogin : doLogin,
			send : send
		};

		return service;

		function doLogin(login) {
			console.log(JSON.stringify(login))
			return $http.post(userUrl + '/login', login);
		}
		

		function send(forget) {
			console.log(JSON.stringify(forget))
			return $http.post(userUrl + '/sendPass', forget);
		}

	}
})();
