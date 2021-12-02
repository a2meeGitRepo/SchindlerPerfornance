(function() {
	'use strict';

	angular.module('myApp.otd').controller('OtdController', OtdController)
			.controller('OtdModalCtrl', OtdModalCtrl).controller(
					'OtdModalAddEditCtrl', OtdModalAddEditCtrl).controller(
							'OtdModalEditCtrl', OtdModalEditCtrl);

	OtdController.$inject = [ '$state', 'otdService', '$uibModal', '$log', '$scope', '$rootScope', 'toastr', 'ApiEndpoint', 'fileUpload', '$window', 'supplierService', 'suppliertypeService', 'performanceService','genericFactory','localStorageService' ];
	OtdModalCtrl.$inject = [ '$uibModalInstance', 'items', '$scope', '$rootScope', 'otdService' ];
	OtdModalAddEditCtrl.$inject = [ '$uibModalInstance', 'otd', '$scope', 'otdService', 'suppliertypeService', 'supplierService' ];
	OtdModalEditCtrl.$inject = [ '$uibModalInstance', 'otd', '$scope', 'otdService', 'suppliertypeService', 'supplierService' ];

	/* @ngInject */
	function OtdController($state, otdService, $uibModal, $log, $scope, $rootScope, toastr, ApiEndpoint, fileUpload, $window, supplierService, suppliertypeService, performanceService,genericFactory,localStorageService) {
		var otdUrl = ApiEndpoint.url + "otd";
		var userDetail = localStorageService.get(ApiEndpoint.userKey);
		console.log("USER  OTD: "+JSON.stringify(userDetail));
		var vm = angular.extend(this, {
			user:userDetail,
			otds : [],
			suppliers : [],
			/*--------------------unique serch data---------------------*/
			supuniq : [],
			/*--------------------unique serch data---------------------*/
			supptypes : [],
			performanceObject : [],
			view : view,
			view2 : view2,
			add : add,
			delet : delet,
			search : search,
			uploadXlxs : uploadXlxs,
			edit:edit
		});

		(function activate() {

			$rootScope.count = 0;
			$rootScope.counts = 0;

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
			$scope.enable = function() {

				$scope.isDisabled = false;

			}

			loadPerformances();
			loadOtds();
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
			loadOtds();
			view2();
			angular.element("input[type='file']").val(null);
	    });
		
		/*--------------------unique serch data---------------------*/
		function loadUnique() {
			otdService.getUniques().then(function(data) {
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

		function search(otd) {
			

			if(otd == undefined){
				toastr.error('Select atleast one feild', 'Error !!');
				return;
			}
			
			otdService.searchOtd1(otd).then(function(data) {
				vm.otds = data;
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

		function uploadXlxs() {
			/*$scope.showLoader = true;
			var file = $scope.myFile;
			console.log('File is: ')
			console.dir(file);
			var uploadUrl = otdUrl + "/uploadFile";
			console.log("URL"+uploadUrl)
			fileUpload.uploadFileToUrl(file, uploadUrl);*/
//			$window.location.reload();
			//angular.element("input[type='file']").val(null);
			
			$scope.showLoader = true;
			var file = $scope.myFile;
			console.log('File is: '+$scope.myFile)
			console.dir(file);
			var uploadUrl = otdUrl + "/uploadFile";
			fileUpload.uploadFileToUrl(file, uploadUrl);
//			$window.location.reload();
			angular.element("input[type='file']").val(null);
		}

		function loadOtds() 
		{
			otdService.getOtds().then(function(data) 
			{
				vm.otds = data;
				console.log(vm.otds);
				
				for (var a = 0; a < vm.otds.length; a++)
				{
					for (var b = 0; b < vm.performanceObject.length; b++) 
					{
						var	otdPerf = parseFloat(vm.performanceObject[b].performance_otd);
						var suppCodePerf = vm.performanceObject[b].performance_suppliercode;
						var suppTypePerf = vm.performanceObject[b].performance_suppliertype;
						var suppYearPerf = vm.performanceObject[b].performance_year;

						if (vm.otds[a].otd_suppliercode == suppCodePerf && vm.otds[a].otd_suppliertype == suppTypePerf && vm.otds[a].otd_year == suppYearPerf) 
						{
							var janOtd	= parseFloat(vm.otds[a].otd_jan);
							var febOtd	= parseFloat(vm.otds[a].otd_feb);
							var marOtd	= parseFloat(vm.otds[a].otd_mar);
							var aprOtd	= parseFloat(vm.otds[a].otd_apr);
							var mayOtd	= parseFloat(vm.otds[a].otd_may);
							var juneOtd = parseFloat(vm.otds[a].otd_june);
							var julyOtd = parseFloat(vm.otds[a].otd_july);
							var augOtd	= parseFloat(vm.otds[a].otd_aug);
							var sepOtd	= parseFloat(vm.otds[a].otd_sep);
							var octOtd	= parseFloat(vm.otds[a].otd_oct);
							var novOtd	= parseFloat(vm.otds[a].otd_nov);
							var decOtd	= parseFloat(vm.otds[a].otd_dec);
							var ytdOtd	= parseFloat(vm.otds[a].otd_ytd);

							if (janOtd	< otdPerf)	{$scope.myDynamicClass[a] = 'redD';} 
							if (febOtd	< otdPerf)	{$scope.febClass[a]  = 'redD';} 
							if (marOtd	< otdPerf)	{$scope.marClass[a]  = 'redD';} 
							if (aprOtd	< otdPerf)	{$scope.aprClass[a]  = 'redD';} 
							if (mayOtd	< otdPerf)	{$scope.mayClass[a]  = 'redD';} 
							if (juneOtd	< otdPerf)	{$scope.juneClass[a] = 'redD';}
							if (julyOtd	< otdPerf)	{$scope.julyClass[a] = 'redD';} 
							if (augOtd	< otdPerf)	{$scope.augClass[a]  = 'redD';}
							if (sepOtd	< otdPerf)	{$scope.sepClass[a]  = 'redD';} 
							if (octOtd	< otdPerf)	{$scope.octClass[a]  = 'redD';} 
							if (novOtd	< otdPerf)	{$scope.novClass[a]  = 'redD';} 
							if (decOtd	< otdPerf)	{$scope.decClass[a]  = 'redD';} 
							
							if (ytdOtd < otdPerf) 
							{
								$scope.ytdClass[a] = 'redD';
								$rootScope.counts = $rootScope.counts + 1;
								sessionStorage.setItem("low2",$rootScope.counts);
							} else {
								$rootScope.count = $rootScope.count + 1;
								sessionStorage.setItem("count",$rootScope.count);
							}
							break;
						} 
					}
				}
			});
		}

		/*function delet(otd) {
			otdService.deleteOtd(otd).then(function() {
				loadOtds();
			});
		}*/
		
		function delet(otd){
			var usr = otd ? otd : {};
			var modalInstance = $uibModal.open({
				animation : true,
				ariaLabelledBy : 'modal-title',
				ariaDescribedBy : 'modal-body',
				templateUrl : 'templates/otd/otdDelete.html',
				controller : 'OtdModalCtrl',
				controllerAs : 'vm',
				size : 'lg',
				resolve : {
					items : function() {
						return usr;
					}
				}
			});
		}
		$scope.$on('otdDeleted', function(event, data) {
			loadOtds();
	    });

		function view(otd) {
			var modalInstance = $uibModal.open({
				animation : true,
				ariaLabelledBy : 'modal-title',
				ariaDescribedBy : 'modal-body',
				templateUrl : 'templates/otd/otdModelView.html',
				controller : 'OtdModalCtrl',
				controllerAs : 'vm',
				size : 'md',
				resolve : {
					items : function() {
						return otd;
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
				controller : 'OtdModalCtrl',
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

		function add(otd) {
			var emp = otd ? otd : {};
			var modalInstance = $uibModal.open({
				animation : true,
				ariaLabelledBy : 'modal-title',
				ariaDescribedBy : 'modal-body',
				templateUrl : 'templates/otd/otdModelAddEdit.html',
				controller : 'OtdModalAddEditCtrl',
				controllerAs : 'vm',
				size : 'lg',
				resolve : {
					otd : function() {
						return emp;
					}
				}
			});

			modalInstance.result.then(function() {
				loadOtds();
			}, function() {
				$log.info('Modal dismissed at: ' + new Date());
			});
		}
		function edit(otd) {
			var emp = otd ? otd : {};
			var modalInstance = $uibModal.open({
				animation : true,
				ariaLabelledBy : 'modal-title',
				ariaDescribedBy : 'modal-body',
				templateUrl : 'templates/otd/otdModelEdit.html',
				controller : 'OtdModalEditCtrl',
				controllerAs : 'vm',
				size : 'lg',
				resolve : {
					otd : function() {
						return emp;
					}
				}
			});

			modalInstance.result.then(function() {
				loadOtds();
			}, function() {
				$log.info('Modal dismissed at: ' + new Date());
			});
		}

	}

	function OtdModalCtrl($uibModalInstance, items, $scope, $rootScope, otdService) {
		var vm = angular.extend(this, {
			items : items,
			ok : ok,
			cancel : cancel
		});

		(function activate() {

		})();

		// ******************************************************

		function ok() {
			otdService.deleteOtd(items).then(function(){
				$rootScope.$broadcast('otdDeleted', {
	                data: 'something'
	            });
				$uibModalInstance.close();
			});
		}

		function cancel() {
			$uibModalInstance.dismiss('cancel');
		}
	}

	function OtdModalAddEditCtrl($uibModalInstance, otd, $scope, otdService,
			suppliertypeService, supplierService) {
		var vm = angular.extend(this, {
			ok : ok,
			cancel : cancel,
			supptypes : [],
			suppliers : [],

		});

		(function activate() {
			$scope.otd = otd;
			loadSuppliertypes();
			loadSuppliers();
			$scope.setDeletes = function() {

				$scope.otd.deletes = 1;
				$scope.s = vm.suppliers;

				var sc = $scope.otd.otd_suppliercode;
				var sn = $scope.otd.otd_suppliername;

				for (var i = 0; i < $scope.s.length; i++) {
					if (sc == $scope.s[i].supplier_code) {
						$scope.otd.otd_suppliername = $scope.s[i].supplier_name;
					} else if (sn == $scope.s[i].supplier_name) {
						$scope.otd.otd_suppliercode = $scope.s[i].supplier_code;
					}
				}
			}
		

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

		function ok(otd) {
			
			console.log(JSON.stringify(otd));
			otdService.addOtd(otd).then(function(response) {
				console.log("OTD"+JSON.stringify(otd))
				console.log("Responce"+JSON.stringify(response))
				
				$uibModalInstance.close(otd);
			});
		}

		function cancel() {
			$uibModalInstance.dismiss('cancel');
		}
	}

	function OtdModalEditCtrl($uibModalInstance, otd, $scope, otdService,
			suppliertypeService, supplierService) {
			var vm = angular.extend(this, {
			ok : ok,
			cancel : cancel,
			supptypes : [],
			suppliers : [],

		});

		(function activate() {
			$scope.otd = otd;
			loadSuppliertypes();
			loadSuppliers();
			$scope.setDeletes = function() {

				$scope.otd.deletes = 1;
				$scope.s = vm.suppliers;

				var sc = $scope.otd.otd_suppliercode;
				var sn = $scope.otd.otd_suppliername;

				for (var i = 0; i < $scope.s.length; i++) {
					if (sc == $scope.s[i].supplier_code) {
						$scope.otd.otd_suppliername = $scope.s[i].supplier_name;
					} else if (sn == $scope.s[i].supplier_name) {
						$scope.otd.otd_suppliercode = $scope.s[i].supplier_code;
					}
				}
			}
		

		})();

		// ******************************************************
		function setOtd(){
			if($scope.otd.otd_jan==""){
				$scope.otd.otd_jan=null
			}
			if($scope.otd.otd_feb==""){
				$scope.otd.otd_feb=null
			}
			if($scope.otd.otd_mar==""){
				$scope.otd.otd_mar=null
			}
			if($scope.otd.otd_apr==""){
				$scope.otd.otd_apr=null
			}
			if($scope.otd.otd_may==""){
				$scope.otd.otd_may=null
			}
			if($scope.otd.otd_june==""){
				$scope.otd.otd_june=null
			}
			if($scope.otd.otd_july==""){
				$scope.otd.otd_july=null
			}
			if($scope.otd.otd_aug==""){
				$scope.otd.otd_aug=null
			}
			if($scope.otd.otd_sep==""){
				$scope.otd.otd_sep=null
			}
			if($scope.otd.otd_oct==""){
				$scope.otd.otd_oct=null
			}
			if($scope.otd.otd_nov==""){
				$scope.otd.otd_nov=null
			}
			if($scope.otd.otd_dec==""){
				$scope.otd.otd_dec=null
			}
			
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

		function ok(otd) {
			setOtd();
			console.log(JSON.stringify(otd));
			otdService.editOtd(otd).then(function(response) {
				console.log("OTD"+JSON.stringify(otd))
				console.log("Responce"+JSON.stringify(response))
				
				$uibModalInstance.close(otd);
			});
		}

		function cancel() {
			$uibModalInstance.dismiss('cancel');
		}
	}
	
})();
