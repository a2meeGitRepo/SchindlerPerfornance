(function() {
	'use strict';

	angular.module('myApp.login_form').controller('loginController',
			loginController);

	loginController.$inject = [ '$rootScope', '$scope', '$stateParams',
			'$state', 'localStorageService', 'toastr', 'ApiEndpoint',
			'loginService', 'userService'];

	/* @ngInject */
	function loginController($rootScope, $stateParams, $scope, $state,
			localStorageService, toastr, ApiEndpoint, loginService, userService) {

		var vm = angular.extend(this, {
			doLogin : doLogin,
			sendMail : sendMail,
			users : []

		});

		(function activate() {

			$scope.forget = {};
			
			loadUsers();
		})();

		// ******************************************************
	
		function loadUsers() {
			userService.getUsers().then(function(data) {
				vm.users = data;
			});
		}
		
		function sendMail(forget) {
			
			debugger;
			
			$scope.email = forget.email;
			
			$scope.u = vm.users;
			
			
			for(var i=0; i< $scope.u.length; i++){
				
				if($scope.email ==$scope.u[i].email){
					
					$scope.forget.email = $scope.u[i].email;
					$scope.forget.fname = $scope.u[i].fname;
					
					$scope.forget.mobile_no = $scope.u[i].mobile_no;
					
					var random_no = Math.floor(Math.random() * 10001);
					
					$scope.forget.password = random_no;
					$scope.u[i].password=random_no
					userService.addUser($scope.u[i])
					break;
				
				}
				
				else{
					
					$scope.forget.email = "no email found";
					
				}
				
			}
			
			
		
			
			loginService.send($scope.forget).then(function() {
			
			});
		}
		
		function doLogin(login) {
			console.log(JSON.stringify(login));
			loginService.doLogin(login).then(function(data) {
				console.log(JSON.stringify(data[0]));
				if (data.length != 0) {
					
					if(data[0].role.role_id == 1)
					$state.go('main.home');
					
					if(data[0].role.role_id == 2)
					$state.go('main.home2');
					
					if(data[0].role.role_id == 3)
					$state.go('main.home3');
					
					if(data[0].role.role_id == 4)
					$state.go('main.home4');
					
					
					
					toastr.success('Login....', 'Succesful !!');
					localStorageService.set(ApiEndpoint.userKey, data[0]);
				} else {
					toastr.error('Username and Password Doesnt match...!!');
				}
			});
		}
	}
})();
