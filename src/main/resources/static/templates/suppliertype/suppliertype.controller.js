(function() {
	'use strict';

	angular.module('myApp.suppliertype').controller('SuppliertypeController',
			SuppliertypeController).controller('SuppliertypeModalCtrl',
			SuppliertypeModalCtrl).controller('SuppliertypeModalAddEditCtrl',
			SuppliertypeModalAddEditCtrl);

	SuppliertypeController.$inject = [ '$state', 'suppliertypeService',
			'$uibModal', '$log', '$scope', 'toastr' ];
	SuppliertypeModalCtrl.$inject = [ '$uibModalInstance', 'items', '$scope', '$rootScope', 'suppliertypeService' ];
	SuppliertypeModalAddEditCtrl.$inject = [ '$uibModalInstance', 'suppliertype',
			'$scope', 'suppliertypeService' ];

	/* @ngInject */
	function SuppliertypeController($state, suppliertypeService, $uibModal, $log,
			$scope, toastr) {
		var vm = angular.extend(this, {
			suppliertypes : [],
			view : view,
			add : add,
			delet : delet
		});

		(function activate() {
			loadSuppliertypes();
		})();

		// ******************************************************

		function loadSuppliertypes() {
			suppliertypeService.getSuppliertypes().then(function(data) {
				vm.suppliertypes = data;
				console.log(JSON.stringify(vm.suppliertypes));
			});
		}

		/*function delet(suppliertype) {
			suppliertypeService.deleteSuppliertype(suppliertype).then(function() {
				loadSuppliertypes();
			});
		}*/
		function delet(suppliertype){
			var usr = suppliertype ? suppliertype : {};
			var modalInstance = $uibModal.open({
				animation : true,
				ariaLabelledBy : 'modal-title',
				ariaDescribedBy : 'modal-body',
				templateUrl : 'templates/suppliertype/suppliertypeDelete.html',
				controller : 'SuppliertypeModalCtrl',
				controllerAs : 'vm',
				size : 'lg',
				resolve : {
					items : function() {
						return usr;
					}
				}
			});
		}
		$scope.$on('suppliertypeDeleted', function(event, data) {
			loadSuppliertypes();
	    });

		function view(suppliertype) {
			var modalInstance = $uibModal
					.open({
						animation : true,
						ariaLabelledBy : 'modal-title',
						ariaDescribedBy : 'modal-body',
						templateUrl : 'templates/suppliertype/suppliertypeModelView.html',
						controller : 'SuppliertypeModalCtrl',
						controllerAs : 'vm',
						size : 'md',
						resolve : {
							items : function() {
								return suppliertype;
							}
						}
					});

			modalInstance.result.then(function() {

			}, function() {
				$log.info('Modal dismissed at: ' + new Date());
			});
		}

		function add(suppliertype) {
			var emp = suppliertype ? suppliertype : {};
			// alert(JSON.stringify(usr));
			var modalInstance = $uibModal
					.open({
						animation : true,
						ariaLabelledBy : 'modal-title',
						ariaDescribedBy : 'modal-body',
						templateUrl : 'templates/suppliertype/suppliertypeModelAddEdit.html',
						controller : 'SuppliertypeModalAddEditCtrl',
						controllerAs : 'vm',
						size : 'lg',
						resolve : {
							suppliertype : function() {
								return emp;
							}
						}
					});

			modalInstance.result.then(function() {
				loadSuppliertypes();
			}, function() {
				$log.info('Modal dismissed at: ' + new Date());
			});
		}

	}

	function SuppliertypeModalCtrl($uibModalInstance, items, $scope, $rootScope, suppliertypeService) {
		var vm = angular.extend(this, {
			items : items,
			ok : ok,
			cancel : cancel
		});

		(function activate() {

		})();

		// ******************************************************

		function ok() {
			suppliertypeService.deleteSuppliertype(items).then(function(){
				$rootScope.$broadcast('suppliertypeDeleted', {
	                data: 'something'
	            });
				$uibModalInstance.close();
			});
		}

		function cancel() {
			$uibModalInstance.dismiss('cancel');
		}
	}

	function SuppliertypeModalAddEditCtrl($uibModalInstance, suppliertype,
			$scope, suppliertypeService) {
		var vm = angular.extend(this, {
			ok : ok,
			cancel : cancel,
		});

		(function activate() {
			$scope.suppliertype = suppliertype;

			$scope.setDeletes = function() {

				$scope.suppliertype.deletes = 1;

			}

		})();

		// ******************************************************

		function ok(suppliertype) {
			suppliertypeService.addSuppliertype(suppliertype).then(function() {
				$uibModalInstance.close(suppliertype);
			});
		}

		function cancel() {
			$uibModalInstance.dismiss('cancel');
		}
	}
})();
