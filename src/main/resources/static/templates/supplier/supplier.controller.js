(function() {
	'use strict';

	angular.module('myApp.supplier').controller('SupplierController',
			SupplierController).controller('SupplierModalCtrl',
			SupplierModalCtrl).controller('SupplierModalAddEditCtrl',
			SupplierModalAddEditCtrl);

	SupplierController.$inject = [ '$state', 'supplierService', '$uibModal',
			'$log', '$scope', 'toastr', 'ApiEndpoint', 'fileUpload', '$window',
			'suppliertypeService' ];
	SupplierModalCtrl.$inject = [ '$uibModalInstance', 'items', '$scope',
			'$rootScope', 'supplierService' ];
	SupplierModalAddEditCtrl.$inject = [ '$uibModalInstance', 'supplier',
			'$scope', 'supplierService', 'suppliertypeService', 'toastr' ];

	/* @ngInject */
	function SupplierController($state, supplierService, $uibModal, $log,
			$scope, toastr, ApiEndpoint, fileUpload, $window,
			suppliertypeService) {

		var supplierUrl = ApiEndpoint.url + "supplier";
		var vm = angular.extend(this, {
			suppliers : [],
			/*--------------------unique serch data---------------------*/
			supuniq : [],
			/*--------------------unique serch data---------------------*/
			supptypes : [],
			view : view,
			view2 : view2,
			add : add,
			search : search,
			delet : delet,
			uploadXlxs : uploadXlxs,
		});

		(function activate() {

			$scope.showLoader = false;
			$scope.isDisabled = true;

			$scope.enable = function() {

				$scope.isDisabled = false;

			}

			loadSuppliers();
			loadSuppliertypes();

			/*--------------------unique serch data---------------------*/
			loadUnique();
			/*--------------------unique serch data---------------------*/
		})();

		// ******************************************************

		$scope.$on('fileUploadExcel', function(event, data) {
			toastr.success('File Upload....', 'Succesful !!');
			// alert("data imported successfully");
			loadSuppliers();
			view2();
			angular.element("input[type='file']").val(null);
		});

		/*--------------------unique serch data---------------------*/
		function loadUnique() {
			supplierService.getUniques().then(function(data) {
				vm.supuniq = data;

				$scope.sup = [];

				for (var i = 0; i < vm.supuniq.length; i++) {
					$scope.sup.push({
						name : $scope.vm.supuniq[i]
					});
				}

			});
		}
		/*--------------------unique serch data---------------------*/
		function search(supplier) {
			console.log(JSON.stringify(supplier))
			
			if(supplier == undefined){
				toastr.error('Select atleast one feild', 'Error !!');
				return;
			}
			
			supplierService.searchSupplier1(supplier).then(function(data) {
				vm.suppliers = data;
			});
		}

		function loadSuppliers() {
			supplierService.getSuppliers().then(function(data) {
				vm.suppliers = data;
				console.log(JSON.stringify(vm.suppliers));
			});
		}

		function uploadXlxs() {

			$scope.showLoader = true;

			var file = $scope.myFile;
			console.log('File is: ')
			console.dir(file);
			var uploadUrl = supplierUrl + "/uploadFile";
			fileUpload.uploadFileToUrl(file, uploadUrl);
			// $window.location.reload();
			angular.element("input[type='file']").val(null);
			/* loadRooms(); */

		}

		function loadSuppliertypes() {
			suppliertypeService.getSuppliertypes().then(function(data) {
				vm.supptypes = data;

			});
		}

		/*
		 * function delet(supplier) {
		 * supplierService.deleteSupplier(supplier).then(function() {
		 * loadSuppliers(); }); }
		 */

		function delet(supplier) {
			var usr = supplier ? supplier : {};
			var modalInstance = $uibModal.open({
				animation : true,
				ariaLabelledBy : 'modal-title',
				ariaDescribedBy : 'modal-body',
				templateUrl : 'templates/supplier/supplierDelete.html',
				controller : 'SupplierModalCtrl',
				controllerAs : 'vm',
				size : 'lg',
				resolve : {
					items : function() {
						return usr;
					}
				}
			});
		}
		$scope.$on('supplierDeleted', function(event, data) {
			loadSuppliers();
		});

		function view(supplier) {
			var modalInstance = $uibModal.open({
				animation : true,
				ariaLabelledBy : 'modal-title',
				ariaDescribedBy : 'modal-body',
				templateUrl : 'templates/supplier/supplierModelView.html',
				controller : 'SupplierModalCtrl',
				controllerAs : 'vm',
				size : 'md',
				resolve : {
					items : function() {
						return supplier;
					}
				}
			});

			modalInstance.result.then(function() {
				loadSuppliers();
			}, function() {
				loadSuppliers();
				$log.info('Modal dismissed at: ' + new Date());
			});
		}

		function view2() {
			$scope.showLoader = false;
			var modalInstance = $uibModal.open({
				animation : true,
				ariaLabelledBy : 'modal-title',
				ariaDescribedBy : 'modal-body',
				templateUrl : 'templates/sale/saleModelView.html',
				controller : 'SupplierModalCtrl',
				controllerAs : 'vm',
				size : 'md',
				resolve : {
					items : function() {
						return;
					}
				}
			});

			modalInstance.result.then(function() {

				loadSuppliers();
			}, function() {
				loadSuppliers();
				$log.info('Modal dismissed at: ' + new Date());
			});
		}

		function add(supplier) {
			var emp = supplier ? supplier : {};
			
			if(!supplier)
				emp.mode = 'add';
			else
				emp.mode = 'edit';
			// alert(JSON.stringify(usr));
			var modalInstance = $uibModal.open({
				animation : true,
				ariaLabelledBy : 'modal-title',
				ariaDescribedBy : 'modal-body',
				templateUrl : 'templates/supplier/supplierModelAddEdit.html',
				controller : 'SupplierModalAddEditCtrl',
				controllerAs : 'vm',
				size : 'lg',
				resolve : {
					supplier : function() {
						return emp;
					}
				}
			});

			modalInstance.result.then(function() {
				loadSuppliers();
			}, function() {
				loadSuppliers();
				$log.info('Modal dismissed at: ' + new Date());
			});
		}

	}

	function SupplierModalCtrl($uibModalInstance, items, $scope, $rootScope,
			supplierService) {
		var vm = angular.extend(this, {
			items : items,
			ok : ok,
			cancel : cancel
		});

		(function activate() {

		})();

		// ******************************************************

		function ok() {
			supplierService.deleteSupplier(items).then(function() {
				$rootScope.$broadcast('supplierDeleted', {
					data : 'something'
				});
				$uibModalInstance.close();
			});
		}

		function cancel() {
			$uibModalInstance.dismiss('cancel');
		}
	}

	function SupplierModalAddEditCtrl($uibModalInstance, supplier, $scope,
			supplierService, suppliertypeService, toastr) {
		var vm = angular.extend(this, {
			ok : ok,
			cancel : cancel,
			suppliertypes : [],
			suppliers : [],
		});

		(function activate() {
			$scope.supplier = supplier;

			loadSuppliers();

			$scope.setDeletes = function() {

				$scope.supplier.deletes = 1;

			}

			loadSuppliertypes();
		})();

		// ******************************************************

		function loadSuppliers() {
			supplierService.getSuppliers().then(function(data) {
				vm.suppliers = data;
				console.log(JSON.stringify(vm.suppliers));
			});
		}

		function loadSuppliertypes() {
			suppliertypeService.getSuppliertypes().then(function(data) {
				vm.suppliertypes = data;
				console.log(JSON.stringify(vm.suppliertypes));
			});
		}

		function ok(supplier) {

			$scope.s = vm.suppliers;
			
			delete supplier.mode;
			
			$scope.code = supplier.supplier_code;
			$scope.type = supplier.supplier_type;

			if (!supplier.supplier_id) {

				for (var i = 0; i < $scope.s.length; i++) {

					if ($scope.code == $scope.s[i].supplier_code
							&& $scope.type == $scope.s[i].supplier_type) {

						toastr.error('supplier already exist');

						return;
					}
				}

			}

			supplierService.addSupplier(supplier).then(function() {
				$uibModalInstance.close(supplier);
			});
		}

		function cancel() {
			$uibModalInstance.dismiss('cancel');
		}
	}
})();
