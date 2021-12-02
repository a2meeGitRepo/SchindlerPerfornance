(function() {
	'use strict';

	angular.module('myApp.employee').controller('EmployeeController', EmployeeController)
			.controller('EmployeeModalCtrl', EmployeeModalCtrl).controller(
					'EmployeeModalAddEditCtrl', EmployeeModalAddEditCtrl);

	EmployeeController.$inject = [ '$state', 'employeeService', '$uibModal', '$log',
			'$scope', 'toastr' ];
	EmployeeModalCtrl.$inject = [ '$uibModalInstance', 'items', '$scope' ];
	EmployeeModalAddEditCtrl.$inject = [ '$uibModalInstance', 'employee', '$scope', 'employeeService', 'departmentService' ];

	/* @ngInject */
	function EmployeeController($state, employeeService, $uibModal, $log, $scope, toastr) {
		var vm = angular.extend(this, {
			employees : [],
			view : view,
			add : add,
			delet : delet
		});

		(function activate() {
			loadEmployees();
		})();

		// ******************************************************

		function loadEmployees() {
			employeeService.getEmployees().then(function(data) {
				vm.employees = data;
				console.log(JSON.stringify(vm.employees));
			});
		}
		
		function delet(employee){
			employeeService.deleteEmployee(employee).then(function(){
				loadEmployees();
			});
		}

		function view(employee) {
			var modalInstance = $uibModal.open({
				animation : true,
				ariaLabelledBy : 'modal-title',
				ariaDescribedBy : 'modal-body',
				templateUrl : 'templates/employee/employeeModelView.html',
				controller : 'EmployeeModalCtrl',
				controllerAs : 'vm',
				size : 'md',
				resolve : {
					items : function() {
						return employee;
					}
				}
			});

			modalInstance.result.then(function() {
				
			}, function() {
				$log.info('Modal dismissed at: ' + new Date());
			});
		}

		function add(employee) {
			var emp = employee ? employee : {};
			// alert(JSON.stringify(usr));
			var modalInstance = $uibModal.open({
				animation : true,
				ariaLabelledBy : 'modal-title',
				ariaDescribedBy : 'modal-body',
				templateUrl : 'templates/employee/employeeModelAddEdit.html',
				controller : 'EmployeeModalAddEditCtrl',
				controllerAs : 'vm',
				size : 'lg',
				resolve : {
					employee : function() {
						return emp;
					}
				}
			});

			modalInstance.result.then(function() {
				loadEmployees();
			}, function() {
				$log.info('Modal dismissed at: ' + new Date());
			});
		}

	}

	function EmployeeModalCtrl($uibModalInstance, items, $scope) {
		var vm = angular.extend(this, {
			items : items,
			ok : ok,
			cancel : cancel
		});

		(function activate() {

		})();

		// ******************************************************

		function ok() {
			$uibModalInstance.close();
		}

		function cancel() {
			$uibModalInstance.dismiss('cancel');
		}
	}

	function EmployeeModalAddEditCtrl($uibModalInstance, employee, $scope, employeeService, departmentService) {
		var vm = angular.extend(this, {
			ok : ok,
			cancel : cancel,
			departments : [],
		});

		(function activate() {
			$scope.employee = employee;
			
			$scope.setDeletes = function(){
				
				$scope.employee.deletes = 1;
				
			}
			
			
			loadDepartments();
		})();

		// ******************************************************
		function loadDepartments() {
			departmentService.getDepartments().then(function(data) {
				vm.departments = data;
			});
		}
		function ok(employee) {
			employeeService.addEmployee(employee).then(function(){
				$uibModalInstance.close(employee);
			});
		}

		function cancel() {
			$uibModalInstance.dismiss('cancel');
		}
	}
})();
