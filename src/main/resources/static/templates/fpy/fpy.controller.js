(function() {
	'use strict';

	angular.module('myApp.fpy').controller('FpyController', FpyController)
			.controller('FpyModalCtrl', FpyModalCtrl).controller(
					'FpyModalAddEditCtrl', FpyModalAddEditCtrl);

	FpyController.$inject = [ '$state', 'fpyService', '$uibModal', '$log', '$scope', '$rootScope', 'toastr', 'ApiEndpoint', 'fileUpload', '$window', 'supplierService', 'suppliertypeService', 'performanceService', 'localStorageService' ];
	FpyModalCtrl.$inject = [ '$uibModalInstance', 'items', '$scope', '$rootScope', 'fpyService' ];
	FpyModalAddEditCtrl.$inject = [ '$uibModalInstance', 'fpy', '$scope', 'fpyService', 'suppliertypeService', 'supplierService' ];

	/* @ngInject */
	function FpyController($state, fpyService, $uibModal, $log, $scope, $rootScope, toastr, ApiEndpoint, fileUpload, $window, supplierService, suppliertypeService, performanceService, localStorageService) {
		var fpyUrl = ApiEndpoint.url + "fpy";
		var userDetail = localStorageService.get(ApiEndpoint.userKey);
		var vm = angular.extend(this, {
			user:userDetail,
			fpys : [],
			suppliers : [],
			supptypes : [],
			performanceObject : [],
			/*--------------------unique serch data---------------------*/
			supuniq : [],
			/*--------------------unique serch data---------------------*/
			uploadXlxs : uploadXlxs,
			view : view,
			view2 : view2,
			add : add,
			search : search,
			delet : delet,
		});

		(function activate() {

			$rootScope.count2 = 0;
			$rootScope.counts2 = 0;
			
			$scope.showLoader = false;
			$scope.isDisabled = true;
			
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

			/*---------------year logic----------------*/
			$scope.year = new Date().getFullYear();
			$scope.years = [];
			for (var i = -3; i < 3; i++) {
				$scope.years.push({
					id : $scope.year + i
				});
			}
			$scope.yearsStr = JSON.stringify($scope.years, null, 4);
			/*---------------year logic----------------*/

			$scope.enable = function() {
				$scope.isDisabled = false;
			}

			loadPerformances();
			loadFpys();
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
			loadFpys();
			view2();
			angular.element("input[type='file']").val(null);
	    });
		
		/*--------------------unique serch data---------------------*/
		function loadUnique() {
			fpyService.getUniques().then(function(data) {
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
		
		function search(fpy) {
			
			if(fpy == undefined){
				toastr.error('Select atleast one feild', 'Error !!');
				return;
			}
			
			fpyService.searchFpy1(fpy).then(function(data) {
				vm.fpys = data;
			});
		}

		function uploadXlxs() {
			$scope.showLoader = true;
			var file = $scope.myFile;
			console.log('File is: ')
			console.dir(file);
			var uploadUrl = fpyUrl + "/uploadFile";
			fileUpload.uploadFileToUrl(file, uploadUrl);
//			$window.location.reload();
			angular.element("input[type='file']").val(null);
		}

		function loadFpys() 
		{

			fpyService.getFpys().then(function(data) 
			{
				vm.fpys = data;
				
				for (var a = 0; a < vm.fpys.length; a++) {

					for (var b = 0; b < vm.performanceObject.length; b++) {

						var fpyPerf = parseFloat(vm.performanceObject[b].performance_fpy);
						var suppCodePerf = vm.performanceObject[b].performance_suppliercode;
						var suppTypePerf = vm.performanceObject[b].performance_suppliertype;
						var suppYearPerf = vm.performanceObject[b].performance_year;

						if (vm.fpys[a].fpy_suppliercode == suppCodePerf && vm.fpys[a].fpy_suppliertype == suppTypePerf && vm.fpys[a].fpy_year == suppYearPerf) {
							
							var janFpy	=	parseFloat(vm.fpys[a].fpy_jan);
							var febFpy	=	parseFloat(vm.fpys[a].fpy_feb);
							var marFpy	=	parseFloat(vm.fpys[a].fpy_mar);
							var aprFpy	=	parseFloat(vm.fpys[a].fpy_apr);
							var mayFpy	=	parseFloat(vm.fpys[a].fpy_may);
							var juneFpy =	parseFloat(vm.fpys[a].fpy_june);
							var julyFpy =	parseFloat(vm.fpys[a].fpy_july);
							var augFpy	=	parseFloat(vm.fpys[a].fpy_aug);
							var sepFpy	=	parseFloat(vm.fpys[a].fpy_sep);
							var octFpy	=	parseFloat(vm.fpys[a].fpy_oct);
							var novFpy	=	parseFloat(vm.fpys[a].fpy_nov);
							var decFpy	=	parseFloat(vm.fpys[a].fpy_dec);
							var ytdFpy	=	parseFloat(vm.fpys[a].fpy_ytd);

							if (janFpy	< fpyPerf)	{$scope.myDynamicClass[a] = 'redD';}
							if (febFpy	< fpyPerf)	{$scope.febClass[a]  = 'redD';}
							if (marFpy	< fpyPerf)	{$scope.marClass[a]  = 'redD';}
							if (aprFpy	< fpyPerf)	{$scope.aprClass[a]  = 'redD';}
							if (mayFpy	< fpyPerf)	{$scope.mayClass[a]  = 'redD';}
							if (juneFpy	< fpyPerf)	{$scope.juneClass[a] = 'redD';}
							if (julyFpy	< fpyPerf)	{$scope.julyClass[a] = 'redD';}
							if (augFpy	< fpyPerf)	{$scope.augClass[a]	 = 'redD';}
							if (sepFpy	< fpyPerf)	{$scope.sepClass[a]  = 'redD';}
							if (octFpy	< fpyPerf)	{$scope.octClass[a]  = 'redD';}
							if (novFpy	< fpyPerf)	{$scope.novClass[a]	 = 'redD';}
							if (decFpy	< fpyPerf)	{$scope.decClass[a]  = 'redD';}
							
							if (ytdFpy < fpyPerf) {
								$scope.ytdClass[a] = 'redD';
								$rootScope.counts2 = $rootScope.counts2 + 1;
								sessionStorage.setItem("low1",$rootScope.counts2);
							} else {

								$rootScope.count2 = $rootScope.count2 + 1;
								sessionStorage.setItem("countss",$rootScope.count2);
							}
							break;
						}
					}
				}
			});
		}

		/*function delet(fpy) {
			fpyService.deleteFpy(fpy).then(function() {
				loadFpys();
			});
		}*/
		
			function delet(fpy){
			var usr = fpy ? fpy : {};
			var modalInstance = $uibModal.open({
				animation : true,
				ariaLabelledBy : 'modal-title',
				ariaDescribedBy : 'modal-body',
				templateUrl : 'templates/fpy/fpyDelete.html',
				controller : 'FpyModalCtrl',
				controllerAs : 'vm',
				size : 'lg',
				resolve : {
					items : function() {
						return usr;
					}
				}
			});
		}
		$scope.$on('fpyDeleted', function(event, data) {
			loadFpys();
	    });


		function view(fpy) {
			var modalInstance = $uibModal.open({
				animation : true,
				ariaLabelledBy : 'modal-title',
				ariaDescribedBy : 'modal-body',
				templateUrl : 'templates/fpy/fpyModelView.html',
				controller : 'FpyModalCtrl',
				controllerAs : 'vm',
				size : 'md',
				resolve : {
					items : function() {
						return fpy;
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
				controller : 'FpyModalCtrl',
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

		function add(fpy) {
			var emp = fpy ? fpy : {};
			var modalInstance = $uibModal.open({
				animation : true,
				ariaLabelledBy : 'modal-title',
				ariaDescribedBy : 'modal-body',
				templateUrl : 'templates/fpy/fpyModelAddEdit.html',
				controller : 'FpyModalAddEditCtrl',
				controllerAs : 'vm',
				size : 'lg',
				resolve : {
					fpy : function() {
						return emp;
					}
				}
			});

			modalInstance.result.then(function() {
				loadFpys();
			}, function() {
				$log.info('Modal dismissed at: ' + new Date());
			});
		}

	}

	function FpyModalCtrl($uibModalInstance, items, $scope, $rootScope, fpyService) {
		var vm = angular.extend(this, {
			items : items,
			ok : ok,
			cancel : cancel
		});

		(function activate() {

		})();

		// ******************************************************

		function ok() {
			fpyService.deleteFpy(items).then(function(){
				$rootScope.$broadcast('fpyDeleted', {
	                data: 'something'
	            });
				$uibModalInstance.close();
			});
		}

		function cancel() {
			$uibModalInstance.dismiss('cancel');
		}
	}

	function FpyModalAddEditCtrl($uibModalInstance, fpy, $scope, fpyService,
			suppliertypeService, supplierService) {
		var vm = angular.extend(this, {
			ok : ok,
			cancel : cancel,
			supptypes : [],
			suppliers : [],
		});

		(function activate() 
		{
			$scope.fpy = fpy;
			$scope.setDeletes = function() 
			{
				$scope.fpy.deletes = 1;
				$scope.s = vm.suppliers;

				var sc = $scope.fpy.fpy_suppliercode;
				var sn = $scope.fpy.fpy_suppliername;

				for (var i = 0; i < $scope.s.length; i++) 
				{
					if (sc == $scope.s[i].supplier_code) 
					{
						$scope.fpy.fpy_suppliername = $scope.s[i].supplier_name;
					} 
					else if (sn == $scope.s[i].supplier_name) 
					{
						$scope.fpy.fpy_suppliercode = $scope.s[i].supplier_code;
					}
				}
			}
			loadSuppliertypes();
			loadSuppliers();
		})();

		// ******************************************************

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
		function ok(fpy) {
			console.log("FPY value"+JSON.stringify(fpy));
			fpyService.addFpy(fpy).then(function() {
				$uibModalInstance.close(fpy);
			});
		}

		function cancel() {
			$uibModalInstance.dismiss('cancel');
		}
	}
})();
