(function() {
	'use strict';

	angular.module('myApp.password').controller('PasswordController',
			PasswordController);

	PasswordController.$inject = [ '$state', 'userService', '$uibModal',
			'$log', '$scope', 'fileUpload', 'toastr', 'ApiEndpoint', '$filter',
			'localStorageService', '$window' ];

	/* @ngInject */
	function PasswordController($state, userService, $uibModal, $log, $scope,
			fileUpload, toastr, ApiEndpoint, $filter, localStorageService,
			$window) {

		var userDetail = localStorageService.get(ApiEndpoint.userKey);

		var vm = angular.extend(this, {
			users : [],
			search : search,
			doLogout : doLogout,
			ok : ok,
			u : userDetail
		});

		(function activate() {

			loadUsers();

			$scope.user = {};
			$scope.role = {};

			$scope.user.role = {};
//			$scope.user.userType = {};

			debugger;

			$scope.userDetail = vm.u;

			$scope.user.user_id = $scope.userDetail.user_id;
			$scope.user.user_code = $scope.userDetail.user_code;
			$scope.user.mobile_no = $scope.userDetail.mobile_no;
			$scope.user.fname = $scope.userDetail.fname;
			$scope.user.email = $scope.userDetail.email;
			$scope.cpassword = $scope.userDetail.password;
			$scope.user.added_by = $scope.userDetail.added_by;

			$scope.user.role.role_id = $scope.userDetail.role.role_id;

//			$scope.user.userType.user_type_id = $scope.userDetail.userType.user_type_id;

			$scope.refresh1 = function() {
				toastr.success('PASSWORD CHANGE....', 'Succesful !!');
				$window.location.reload();
			}

			$scope.refresh = function() {
//				var retVal = confirm("Do you want to refresh the page");
//				if (retVal == true) {
//					$window.location.reload();
//				} else {
//					return false;
//				}
				$scope.user.password = "";
			}

		})();

		// ******************************************************

		function doLogout() {
			$state.go('login_form');
			localStorageService.remove(ApiEndpoint.userKey);
		}

		
		function ok(user) {

			if ($scope.user.password.toUpperCase() === 'PASSWORD') {
				toastr.error('password can not be \'password\'');
				document.getElementsByClassName('d')[0].focus();
				return;
			}
			if ($scope.user.password == $scope.cpassword) {
				toastr.error('password can not be \'current password\'');
				document.getElementsByClassName('d')[0].focus();
				return;
			}
			if ($scope.user.password.length < 6 || $scope.user.password.length > 12) {
				toastr.error('password length can not be less than 6 or more than 12');
				document.getElementsByClassName('d')[0].focus();
				return;
			}

			userService.addUser(user).then(function() {
//				$uibModalInstance.close(user);
				doLogout();
			});
		}

		
		function loadUsers() {
			userService.getUsers().then(function(data) {
				vm.users = data;
				console.log(JSON.stringify(vm.users));
			});
		}

		function search(user) {

			userService.searchUser(user).then(function(data) {
				vm.users = data;
				console.log(JSON.stringify(vm.users))
			});
		}

	}
})();
