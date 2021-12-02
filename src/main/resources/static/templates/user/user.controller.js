(function() {
	'use strict';

	angular.module('myApp.user').controller('UserController', UserController)
			.controller('UserModalCtrl', UserModalCtrl).controller(
					'UserModalAddEditCtrl', UserModalAddEditCtrl);

	UserController.$inject = [ '$state', 'userService', '$uibModal', '$log',
			'$scope', 'toastr','$rootScope' ];
	UserModalCtrl.$inject = [ '$uibModalInstance', 'userService', '$scope', 'items', '$rootScope'];
	UserModalAddEditCtrl.$inject = [ '$uibModalInstance',
			'localStorageService', 'ApiEndpoint', 'user', '$scope',
			'userService', '$filter', 'toastr' ];

	/* @ngInject */
	function UserController($state, userService, $uibModal, $log, $scope,
			toastr,$rootScope) {
		var vm = angular.extend(this, {
			users : [],
			view : view,
			add : add,
			add2 : add2,
			delet : delet
		});

		(function activate() {
			loadUsers();

			$scope.isDisabled = true;
			$scope.enable = function() {

				debugger;

				$scope.isDisabled = false;

			}
		})();

		// ******************************************************

		function loadUsers() {
			$rootScope.loader=true;
			userService.getUsers().then(function(data) {
				vm.users = data;
				$rootScope.loader=false;

				console.log(JSON.stringify(vm.users));
			});
		}

		/*function delet(user) {
			userService.deleteUser(user).then(function() {
				loadUsers();
			});
		}*/
		
		function delet(user){
			var usr = user ? user : {};
			var modalInstance = $uibModal.open({
				animation : true,
				ariaLabelledBy : 'modal-title',
				ariaDescribedBy : 'modal-body',
				templateUrl : 'templates/user/userDelete.html',
				controller : 'UserModalCtrl',
				controllerAs : 'vm',
				size : 'lg',
				resolve : {
					items : function() {
						return usr;
					}
				}
			});
		}

		/**
		 * @author	ABS
		 * @date	July 07, 2018
		 * @purpose	to load data after changes
		 */
		$scope.$on('userDeleted', function(event, data) {
			loadUsers();
	    });


		function view(user) {
			var modalInstance = $uibModal.open({
				animation : true,
				ariaLabelledBy : 'modal-title',
				ariaDescribedBy : 'modal-body',
				templateUrl : 'templates/user/userModelView.html',
				controller : 'UserModalCtrl',
				controllerAs : 'vm',
				size : 'md',
				resolve : {
					items : function() {
						return user;
					}
				}
			});

			modalInstance.result.then(function() {

			}, function() {
				$log.info('Modal dismissed at: ' + new Date());
			});
		}

		
		function add2(user) {
			var usr = user ? user : {};
			var modalInstance = $uibModal.open({
				animation : true,
				ariaLabelledBy : 'modal-title',
				ariaDescribedBy : 'modal-body',
				templateUrl : 'templates/user/userModelAddEdit2.html',
				controller : 'UserModalAddEditCtrl',
				controllerAs : 'vm',
				size : 'lg',
				resolve : {
					user : function() {
						return usr;
					}
				}
			});

			modalInstance.result.then(function() {
				loadUsers();
			}, function() {
				$log.info('Modal dismissed at: ' + new Date());
			});
		}
		
		
		function add(user) {
			var usr = user ? user : {};
			var modalInstance = $uibModal.open({
				animation : true,
				ariaLabelledBy : 'modal-title',
				ariaDescribedBy : 'modal-body',
				templateUrl : 'templates/user/userModelAddEdit.html',
				controller : 'UserModalAddEditCtrl',
				controllerAs : 'vm',
				size : 'lg',
				resolve : {
					user : function() {
						return usr;
					}
				}
			});

			modalInstance.result.then(function() {
				loadUsers();
			}, function() {
				$log.info('Modal dismissed at: ' + new Date());
			});
		}

	}

	function UserModalCtrl($uibModalInstance, userService, $scope, items, $rootScope) {
		var vm = angular.extend(this, {
			items : items,
			ok : ok,
			cancel : cancel
		});

		(function activate() {

		})();

		// ******************************************************

		function ok() {
			userService.deleteUser(items).then(function(){
				$rootScope.$broadcast('userDeleted', {
	                data: 'something'
	            });
				$uibModalInstance.close();
			});
			//$uibModalInstance.close();
		}

		function cancel() {
			$uibModalInstance.dismiss('cancel');
		}
	}

	function UserModalAddEditCtrl($uibModalInstance, localStorageService,
			ApiEndpoint, user, $scope, userService, $filter, toastr) {
		
		var userDetail = localStorageService.get(ApiEndpoint.userKey);
		
		var vm = angular.extend(this, {
			ok : ok,
			cancel : cancel,
			roles : [],
//			userTypes : [],
			users : [],

			user : userDetail
		});

		(function activate() {
			$scope.user = user;
			loadRoles();
//			loadUserTypes();
			loadUsers();
			/*
			 * $scope.setTime = function(){
			 * 
			 * $scope.CurrentDate = new Date();
			 * 
			 * $scope.cd = $filter('date')($scope.CurrentDate, "hh:mm:ss a");
			 * 
			 * $scope.user.added_time = $scope.cd; }
			 */

			$scope.setUser = function() {

				debugger;
				$scope.u = vm.user;

				$scope.user.added_by = $scope.u.mobile_no;

			}

		})();

		// ******************************************************

		function loadUsers() {
			userService.getUsers().then(function(data) {
				vm.users = data;

				console.log(JSON.stringify(vm.users));
			});
		}

		function loadRoles() {
			userService.getRoles().then(function(data) {
				vm.roles = data;
			});
		}

//		function loadUserTypes() {
//			userService.getUserTypes().then(function(data) {
//				vm.userTypes = data;
//			});
//		}

		function ok(user) {

			if ($scope.user.password.toUpperCase() === 'PASSWORD') {
				toastr.error('password can not be \'password\'');
				document.getElementsByClassName('d')[0].focus();
				return;
			}
			if ($scope.user.password.length < 6
					|| $scope.user.password.length > 12) {
				toastr
						.error('password length can not be less than 6 or more than 12');
				document.getElementsByClassName('d')[0].focus();
				return;
			}
			
			userService.addUser(user).then(function() {
				$uibModalInstance.close(user);
				sendMail(user);
				sendSms(user);
			});
		}

		function sendMail(user) {
			userService.sendMail(user).then(function() {

			});
		}

		function sendSms(user) {
			userService.sendSms(user).then(function() {

			});
		}

		function cancel() {
			$uibModalInstance.dismiss('cancel');
		}
	}
})();
