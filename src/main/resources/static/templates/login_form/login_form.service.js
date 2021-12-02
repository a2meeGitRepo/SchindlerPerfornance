(function() {
	'use strict';

	angular.module('myApp.login_form').factory('loginService', loginService);

	loginService.$inject = [ 'loginHttpService', '$q', 'toastr' ];

	function loginService(loginHttpService, $q, toastr) {
		var service = {
			doLogin : doLogin,
			send : send
		};
		return service;

		// ***************************************************************

		
		function send(forget) {
			var deferred = $q.defer();
			loginHttpService.send(forget).then(function(response) {
				
			
				
				if (response.data.message == "Email Id not exist.. plz enter correct mail id") {

					toastr.error(response.data.message);

				}
				
				else{
					
					toastr.success('new password send to your email id');
					
				}

				
				
				deferred.resolve(response.data);
			}, function(err) {
				toastr.error(err.status + ' : ' + err.statusText, 'Error !!');
			});
			return deferred.promise;
		}
		
		
		
		function doLogin(login) {
			var deferred = $q.defer();
			loginHttpService.doLogin(login).then(function(response) {
				deferred.resolve(response.data);
			}, function(err) {
				toastr.error(err.status + ' : ' + err.statusText, 'Error !!');
			});
			return deferred.promise;
		}
	}

})();