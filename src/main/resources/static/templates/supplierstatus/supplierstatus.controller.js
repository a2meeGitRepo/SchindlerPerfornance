(function() {
	'use strict';

	angular.module('myApp.supplierstatus').controller(
			'SupplierstatusController', SupplierstatusController).controller(
			'SupplierstatusModalCtrl', SupplierstatusModalCtrl).controller(
			'SupplierstatusModalAddEditCtrl', SupplierstatusModalAddEditCtrl).filter('unique', function () {

			    return function (items, filterOn) {

			        if (filterOn === false) {
			            return items;
			        }

			        if ((filterOn || angular.isUndefined(filterOn)) && angular.isArray(items)) {
			            var hashCheck = {}, newItems = [];

			            var extractValueToCompare = function (item) {
			                if (angular.isObject(item) && angular.isString(filterOn)) {
			                    return item[filterOn];
			                } else {
			                    return item;
			                }
			            };

			            angular.forEach(items, function (item) {
			                var valueToCheck, isDuplicate = false;

			                for (var i = 0; i < newItems.length; i++) {
			                    if (angular.equals(extractValueToCompare(newItems[i]), extractValueToCompare(item))) {
			                        isDuplicate = true;
			                        break;
			                    }
			                }
			                if (!isDuplicate) {
			                    newItems.push(item);
			                }

			            });
			            items = newItems;
			        }
			        return items;
			    };
			});;

	SupplierstatusController.$inject = [ '$state', 'supplierstatusService',
			'$uibModal', '$log', '$scope', 'toastr', 'ApiEndpoint',
			'fileUpload', '$window', 'supplierService', 'suppliertypeService',
			'fpyService', 'otdService', 'ppmService' ];
	SupplierstatusModalCtrl.$inject = [ '$uibModalInstance', 'items', '$scope', '$rootScope', 'supplierstatusService' ];
	SupplierstatusModalAddEditCtrl.$inject = [ '$uibModalInstance',
			'supplierstatus', '$scope', 'supplierstatusService', 'supplierService', 'suppliertypeService' ];

	/* @ngInject */
	function SupplierstatusController($state, supplierstatusService, $uibModal,
			$log, $scope, toastr, ApiEndpoint, fileUpload, $window,
			supplierService, suppliertypeService, fpyService, otdService, ppmService ) {
		var supplierstatusUrl = ApiEndpoint.url + "supplierstatus"; // Supplierstatus Url
		var vm = angular.extend(this, {
			supplierstatuss : [],
			suppliers : [],
			supptypes : [],
			fpys : [],
			otds : [],
			ppms : [],
			view : view,
			view2 : view2,
			add : add,
			delet : delet,
			search : search,
			uploadXlxs : uploadXlxs,
		});

		(function activate() {
			
			$scope.showLoader = false;
			$scope.isDisabled = true;


			/*---------------year logic----------------*/
			$scope.year = new Date().getFullYear();
		    $scope.years = [];
		    for(var i = -3; i < 3; i++) {
		    		$scope.years.push({id: $scope.year + i});
		    }
		    
		    $scope.yearsStr = JSON.stringify($scope.years, null, 4);
		    /*---------------year logic----------------*/


			$scope.enable = function() {

				$scope.isDisabled = false;

			}
			loadSupplierstatuss();
			loadSuppliers();
			loadSuppliertypes();
			loadFpys();
			loadOtds();
			loadPpms();
		})();

		// ******************************************************

		$scope.$on('fileUploadExcel', function(event, data) {
			toastr.success('File Upload....', 'Succesful !!');
			//alert("data imported successfully");
			loadSupplierstatuss();
			view2();
			angular.element("input[type='file']").val(null);
	    });
		
		
		function search(supplierstatus) {
			console.log(JSON.stringify(supplierstatus))
			if(supplierstatus == undefined){
				toastr.error('Select atleast one feild', 'Error !!');
				return;
			}
			
			supplierstatusService.searchSupplierstatus1(supplierstatus).then(
					function(data) {
						vm.supplierstatuss = data;
					});
		}

		function loadPpms() {
			ppmService.getPpms().then(function(data) {
				vm.ppms = data;
				console.log(JSON.stringify(vm.ppms));
			});
		}

		function loadOtds() {
			otdService.getOtds().then(function(data) {
				vm.otds = data;
				console.log(JSON.stringify(vm.otds));
			});
		}

		function loadFpys() {
			fpyService.getFpys().then(function(data) {
				vm.fpys = data;
				console.log(JSON.stringify(vm.fpys));
			});
		}

		function loadSuppliers() {
			supplierService.getSuppliers().then(function(data) {
				vm.suppliers = data;
				console.log(JSON.stringify(vm.suppliers));
			});
		}

		function loadSuppliertypes() {
			suppliertypeService.getSuppliertypes().then(function(data) {
				vm.supptypes = data;

			});
		}

		function uploadXlxs() {
			$scope.showLoader = true;
			var file = $scope.myFile;
			console.log('File is: ')
			console.dir(file);
			var uploadUrl = supplierstatusUrl + "/uploadFile";
			fileUpload.uploadFileToUrl(file, uploadUrl);
//			$window.location.reload();
			angular.element("input[type='file']").val(null);
			/* loadRooms(); */

		}

		function loadSupplierstatuss() {
			supplierstatusService.getSupplierstatuss().then(function(data) {
				vm.supplierstatuss = data;
				console.log(JSON.stringify(vm.supplierstatuss));
			});
		}

		/*function delet(supplierstatus) {
			supplierstatusService.deleteSupplierstatus(supplierstatus).then(
					function() {
						loadSupplierstatuss();
					});
		}*/
		
		function delet(supplierstatus){
			var usr = supplierstatus ? supplierstatus : {};
			var modalInstance = $uibModal.open({
				animation : true,
				ariaLabelledBy : 'modal-title',
				ariaDescribedBy : 'modal-body',
				templateUrl : 'templates/supplierstatus/supplierstatusDelete.html',
				controller : 'SupplierstatusModalCtrl',
				controllerAs : 'vm',
				size : 'lg',
				resolve : {
					items : function() {
						return usr;
					}
				}
			});
		}
		$scope.$on('supplierstatusDeleted', function(event, data) {
			loadSupplierstatuss();
	    });
		
		

		function view(supplierstatus) {
			var modalInstance = $uibModal
					.open({
						animation : true,
						ariaLabelledBy : 'modal-title',
						ariaDescribedBy : 'modal-body',
						templateUrl : 'templates/supplierstatus/supplierstatusModelView.html',
						controller : 'SupplierstatusModalCtrl',
						controllerAs : 'vm',
						size : 'md',
						resolve : {
							items : function() {
								return supplierstatus;
							}
						}
					});

			modalInstance.result.then(function() {

			}, function() {
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
				controller : 'SupplierstatusModalCtrl',
				controllerAs : 'vm',
				size : 'md',
				resolve : {
					items : function() {
						return;
					}
				}
			});

			modalInstance.result.then(function() {

			}, function() {
				$log.info('Modal dismissed at: ' + new Date());
			});
		}

		function add(supplierstatus) {
			var emp = supplierstatus ? supplierstatus : {};
			// alert(JSON.stringify(usr));
			var modalInstance = $uibModal
					.open({
						animation : true,
						ariaLabelledBy : 'modal-title',
						ariaDescribedBy : 'modal-body',
						templateUrl : 'templates/supplierstatus/supplierstatusModelAddEdit.html',
						controller : 'SupplierstatusModalAddEditCtrl',
						controllerAs : 'vm',
						size : 'lg',
						resolve : {
							supplierstatus : function() {
								return emp;
							}
						}
					});

			modalInstance.result.then(function() {
				loadSupplierstatuss();
			}, function() {
				$log.info('Modal dismissed at: ' + new Date());
			});
		}

	}

	function SupplierstatusModalCtrl($uibModalInstance, items, $scope, $rootScope, supplierstatusService) {
		var vm = angular.extend(this, {
			items : items,
			ok : ok,
			cancel : cancel
		});

		(function activate() {

		})();

		// ******************************************************

		function ok() {
			supplierstatusService.deleteSupplierstatus(items).then(function(){
				$rootScope.$broadcast('supplierstatusDeleted', {
	                data: 'something'
	            });
				$uibModalInstance.close();
			});
		}

		function cancel() {
			$uibModalInstance.dismiss('cancel');
		}
	}

	function SupplierstatusModalAddEditCtrl($uibModalInstance, supplierstatus,
			$scope, supplierstatusService, supplierService, suppliertypeService) {
		var vm = angular.extend(this, {
			ok : ok,
			cancel : cancel,
			suppliers : [],
			suppliertypes : [],
		});

		(function activate() {
			$scope.supplierstatus = supplierstatus;

			$scope.setDeletes = function() {

				$scope.supplierstatus.deletes = 1;

				debugger;

				$scope.s = vm.suppliers;

				var sc = $scope.supplierstatus.supplierstatus_suppliercode;
				var sn = $scope.supplierstatus.supplierstatus_suppliername;

				for (var i = 0; i < $scope.s.length; i++) {

					if (sc == $scope.s[i].supplier_code) {

						$scope.supplierstatus.supplierstatus_suppliername = $scope.s[i].supplier_name;

					} else if (sn == $scope.s[i].supplier_name) {

						$scope.supplierstatus.supplierstatus_suppliercode = $scope.s[i].supplier_code;

					}
				}

			}

			loadSuppliers();
			loadSuppliertypes();
		})();

		// ******************************************************

		function ok(supplierstatus) {
			supplierstatusService.addSupplierstatus(supplierstatus).then(
					function() {
						$uibModalInstance.close(supplierstatus);
					});
		}

		function loadSuppliertypes() {
			suppliertypeService.getSuppliertypes().then(function(data) {
				vm.suppliertypes = data;
				console.log(JSON.stringify(vm.suppliertypes));
			});
		}


		function loadSuppliers() {
			supplierService.getSuppliers().then(function(data) {
				vm.suppliers = data;
				console.log(JSON.stringify(vm.suppliers));
			});
		}
		
		
		function cancel() {
			$uibModalInstance.dismiss('cancel');
		}
	}
})();
