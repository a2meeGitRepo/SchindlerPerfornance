(function() {
	'use strict';

	angular.module('myApp.ppm').controller('PpmController', PpmController)
			.controller('PpmModalCtrl', PpmModalCtrl).controller(
					'PpmModalAddEditCtrl', PpmModalAddEditCtrl);

	PpmController.$inject = [ '$state', 'ppmService', '$uibModal', '$log',
			'$scope', '$rootScope', 'toastr', 'ApiEndpoint', 'fileUpload',
			'$window', 'supplierService', 'suppliertypeService',
			'performanceService','localStorageService' ];
	PpmModalCtrl.$inject = [ '$uibModalInstance', 'items', '$scope', '$rootScope', 'ppmService' ];
	PpmModalAddEditCtrl.$inject = [ '$uibModalInstance', 'ppm', '$scope',
			'ppmService', 'supplierService', 'suppliertypeService' ];

	/* @ngInject */
	function PpmController($state, ppmService, $uibModal, $log, $scope,
			$rootScope, toastr, ApiEndpoint, fileUpload, $window,
			supplierService, suppliertypeService, performanceService,localStorageService) {
		var ppmUrl = ApiEndpoint.url + "ppm";
		var userDetail = localStorageService.get(ApiEndpoint.userKey);
		var vm = angular.extend(this, {
			user:userDetail,
			ppms : [],
			suppliers : [],
			supptypes : [],
			ppm2s : [],
			/*--------------------unique serch data---------------------*/
			supuniq : [],
			/*--------------------unique serch data---------------------*/
			view : view,
			view2 : view2,
			add : add,
			addPpm2 	: addPpm2,
			delet : delet,
			search : search,
			uploadXlxs : uploadXlxs,
			performanceObject : [],
		});

		(function activate() {
			$rootScope.count3 = 0;
			$rootScope.counts3 = 0;

			$scope.showLoader = false;
			$scope.isDisabled = true;
			$scope.isDisabled2 = true;
			
			$scope.myDynamicClass = [];
			$scope.febClass = [];
			$scope.marClass = [];
			$scope.aprClass = [];
			$scope.mayClass = [];
			$scope.juneClass = [];
			$scope.julyClass = [];
			$scope.augClass = [];
			$scope.sepClass = [];
			$scope.octClass = [];
			$scope.novClass = [];
			$scope.decClass = [];
			$scope.ytdClass = [];

			$scope.enable = function() {
				$scope.isDisabled = false;
				
				if ($scope.ppm2.ppm2_occ != null
						&& $scope.ppm2.ppm2_kw != null) {
					$scope.isDisabled2 = false;
				}
				else{
					
					$scope.isDisabled2 = true;
				}
			}

			loadPerformances();
			loadPpms();
			loadPpm2s();
			loadSuppliers();
			loadSuppliertypes();
			/*--------------------unique serch data---------------------*/
			loadUnique();
			/*--------------------unique serch data---------------------*/
		})();

		// ******************************************************


		$scope.$on('fileUploadExcel', function(event, data) {
			toastr.success('File Upload....', 'Succesful !!');
			//alert("data imported successfully");
			loadPpms();
			view2();
			angular.element("input[type='file']").val(null);
	    });
		
		function loadPpm2s() {
			ppmService.getPpm2s().then(function(data) {
				vm.ppm2s = data;
				console.log(JSON.stringify(vm.ppm2s));
			});
		}

		/*--------------------unique serch data---------------------*/
		function loadUnique() {
			ppmService.getUniques().then(function(data) {
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

		function loadPerformances() {
			performanceService.getPerformances().then(function(data) {
				vm.performanceObject = data;
			});
		}

		function loadSuppliers() {
			supplierService.getSuppliers().then(function(data) {
				vm.suppliers = data;
			});
		}

		function loadSuppliertypes() {
			suppliertypeService.getSuppliertypes().then(function(data) {
				vm.supptypes = data;
			});
		}

		function search(ppm) {
			
			
			if(ppm == undefined){
				toastr.error('Select atleast one feild', 'Error !!');
				return;
			}
			ppmService.searchPpm1(ppm).then(function(data) {
				vm.ppms = data;
			});
		}

		function uploadXlxs() {
			$scope.showLoader = true;
			var file = $scope.myFile;
			console.log('File is: ')
			console.dir(file);
			var uploadUrl = ppmUrl + "/uploadFile";
			fileUpload.uploadFileToUrl(file, uploadUrl);
//			$window.location.reload();
			angular.element("input[type='file']").val(null);
		}

		function loadPpms() {
			ppmService.getPpms().then(function(data) 
			{
				vm.ppms = data;
				for (var a = 0; a < vm.ppms.length; a++) {
					for (var b = 0; b < vm.performanceObject.length; b++) {
	
						var ppmPerf = parseFloat(vm.performanceObject[b].performance_ppm);
						var suppCodePerf = vm.performanceObject[b].performance_suppliercode;
						var suppTypePerf = vm.performanceObject[b].performance_suppliertype;
						var suppYearPerf = vm.performanceObject[b].performance_year;
	
						if (vm.ppms[a].ppm_suppliercode == suppCodePerf && vm.ppms[a].ppm_supplierType == suppTypePerf && vm.ppms[a].ppm_year == suppYearPerf) {
	
							var janPpm	= parseFloat(vm.ppms[a].ppm_jan);
							var febPpm	= parseFloat(vm.ppms[a].ppm_feb);
							var marPpm	= parseFloat(vm.ppms[a].ppm_mar);
							var aprPpm	= parseFloat(vm.ppms[a].ppm_apr);
							var mayPpm	= parseFloat(vm.ppms[a].ppm_may);
							var junePpm	= parseFloat(vm.ppms[a].ppm_june);
							var julyPpm	= parseFloat(vm.ppms[a].ppm_july);
							var augPpm	= parseFloat(vm.ppms[a].ppm_aug);
							var sepPpm	= parseFloat(vm.ppms[a].ppm_sep);
							var octPpm	= parseFloat(vm.ppms[a].ppm_oct);
							var novPpm	= parseFloat(vm.ppms[a].ppm_nov);
							var decPpm	= parseFloat(vm.ppms[a].ppm_dec);
							var ytdPpm	= parseFloat(vm.ppms[a].ppm_ytd);
	
							if (janPpm	> ppmPerf) {$scope.myDynamicClass[a] = 'redD';}
							if (febPpm	> ppmPerf) {$scope.febClass[a]	= 'redD';}
							if (marPpm	> ppmPerf) {$scope.marClass[a]	= 'redD';}
							if (aprPpm	> ppmPerf) {$scope.aprClass[a]	= 'redD';}
							if (mayPpm	> ppmPerf) {$scope.mayClass[a]	= 'redD';}
							if (junePpm	> ppmPerf) {$scope.juneClass[a]	= 'redD';}
							if (julyPpm	> ppmPerf) {$scope.julyClass[a]	= 'redD';}
							if (augPpm	> ppmPerf) {$scope.augClass[a]	= 'redD';}
							if (sepPpm	> ppmPerf) {$scope.sepClass[a]	= 'redD';}
							if (octPpm	> ppmPerf) {$scope.octClass[a]	= 'redD';}
							if (novPpm	> ppmPerf) {$scope.novClass[a]	= 'redD';}
							if (decPpm	> ppmPerf) {$scope.decClass[a]	= 'redD';}
							
							if (ytdPpm > ppmPerf) {
								$scope.ytdClass[a] = 'redD';
								$rootScope.counts3 = $rootScope.counts3 + 1;
								sessionStorage.setItem("low3",$rootScope.counts3);
							} else {
								$rootScope.count3 = $rootScope.count3 + 1;
								sessionStorage.setItem("count3",$rootScope.count3);
							}
							break;
						}
					}
				}
			});
		}
		
		/*function delet(ppm) {
			ppmService.deletePpm(ppm).then(function() {
				loadPpms();
			});
		}*/
		function delet(ppm){
			var usr = ppm ? ppm : {};
			var modalInstance = $uibModal.open({
				animation : true,
				ariaLabelledBy : 'modal-title',
				ariaDescribedBy : 'modal-body',
				templateUrl : 'templates/ppm/ppmDelete.html',
				controller : 'PpmModalCtrl',
				controllerAs : 'vm',
				size : 'lg',
				resolve : {
					items : function() {
						return usr;
					}
				}
			});
		}
		$scope.$on('ppmDeleted', function(event, data) {
			loadPpms();
	    });

		function addPpm2(ppm2) {
			
			
			ppmService.addPpm2(ppm2).then(function() {
				
			});
		}

		function view(ppm) {
			var modalInstance = $uibModal.open({
				animation : true,
				ariaLabelledBy : 'modal-title',
				ariaDescribedBy : 'modal-body',
				templateUrl : 'templates/ppm/ppmModelView.html',
				controller : 'PpmModalCtrl',
				controllerAs : 'vm',
				size : 'md',
				resolve : {
					items : function() {
						return ppm;
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
				controller : 'PpmModalCtrl',
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
		
		function add(ppm) {
			var emp = ppm ? ppm : {};
			var modalInstance = $uibModal.open({
				animation : true,
				ariaLabelledBy : 'modal-title',
				ariaDescribedBy : 'modal-body',
				templateUrl : 'templates/ppm/ppmModelAddEdit.html',
				controller : 'PpmModalAddEditCtrl',
				controllerAs : 'vm',
				size : 'lg',
				resolve : {
					ppm : function() {
						return emp;
					}
				}
			});

			modalInstance.result.then(function() {
				loadPpms();
			}, function() {
				$log.info('Modal dismissed at: ' + new Date());
			});
		}

	}

	function PpmModalCtrl($uibModalInstance, items, $scope, $rootScope, ppmService) {
		var vm = angular.extend(this, {
			items : items,
			ok : ok,
			cancel : cancel
		});

		(function activate() {

		})();

		// ******************************************************

		function ok() {
		ppmService.deletePpm(items).then(function(){
				$rootScope.$broadcast('ppmDeleted', {
	                data: 'something'
	            });
				$uibModalInstance.close();
		});
		}

		function cancel() {
			$uibModalInstance.dismiss('cancel');
		}
	}

	function PpmModalAddEditCtrl($uibModalInstance, ppm, $scope, ppmService,
			supplierService, suppliertypeService) {
		var vm = angular.extend(this, {
			ok : ok,
			cancel : cancel,
			suppliers : [],
			suppliertypes : [],
		});

		(function activate() {
			$scope.ppm = ppm;

			$scope.setDeletes = function() {

				$scope.ppm.deletes = 1;
				$scope.s = vm.suppliers;

				var sc = $scope.ppm.ppm_suppliercode;
				var sn = $scope.ppm.ppm_suppliername;

				for (var i = 0; i < $scope.s.length; i++) {

					if (sc == $scope.s[i].supplier_code) {
						$scope.ppm.ppm_suppliername = $scope.s[i].supplier_name;
					}
					else if (sn == $scope.s[i].supplier_name) {
						$scope.ppm.ppm_suppliercode = $scope.s[i].supplier_code;
					}
				}
			}

			loadSuppliers();
			loadSuppliertypes();
		})();

		// ******************************************************

		function loadSuppliertypes() {
			suppliertypeService.getSuppliertypes().then(function(data) {
				vm.suppliertypes = data;
			});
		}

		function loadSuppliers() {
			supplierService.getSuppliers().then(function(data) {
				vm.suppliers = data;
			});
		}

		function ok(ppm) {
			console.log("OK PPM "+JSON.stringify(ppm))
			ppmService.addPpm(ppm).then(function() {
				$uibModalInstance.close(ppm);
			});
		}

		function cancel() {
			$uibModalInstance.dismiss('cancel');
		}
	}
})();
