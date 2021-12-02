(function() {
	'use strict';

	angular
		.module('myApp.user')
		.factory('userService', userService);

	userService.$inject = ['userHttpService', '$q', 'toastr'];
	
	/* @ngInject */
	function userService(userHttpService, $q, toastr) {
		var service = {			
			getUsers : getUsers,
			addUser : addUser,
		};
		return service;

		// ***************************************************************

		function getUsers() {
			var deferred = $q.defer();
			userHttpService.getUsers().then(function(response){
				deferred.resolve(response.data);
			}, function(err){
				alert(JSON.stringify(err.statusText));
			});
			return deferred.promise;
		}
		
		/*function addUser(user){
			var deferred = $q.defer();
			userHttpService.addUser(user).then(function(response){
				toastr.success('User Added....', 'Succesful !!');
				deferred.resolve(response.data);
			}, function(err){
				toastr.error(err.status+' : '+err.statusText, 'Error !!');
			});
			return deferred.promise;
		}*/
		
		function addUser(user){
			var deferred = $q.defer();
			userHttpService.addUser(user).then(function(response){
				//toastr.success('User Added....', 'Succesful !!');
				debugger
				deferred.resolve(response.data);
				
				if (response.data.message == "Employee Already Exists") {

					toastr.error(response.data.message);

				}

				else if (response.data.message == "Employee added Successfully") {

					toastr.success(response.data.message);
				}

				else {
				}
				
			}, function(err){
				toastr.error('sorry user cant registered at this time', 'Error !!');
			});
			return deferred.promise;
		}
		
	}

})();