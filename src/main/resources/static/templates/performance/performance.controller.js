(function() {
	'use strict';

	angular.module('myApp.performance').controller('PerformanceController',
			PerformanceController).controller('PerformanceModalCtrl',
			PerformanceModalCtrl).controller('PerformanceModalAddEditCtrl',
			PerformanceModalAddEditCtrl);

	PerformanceController.$inject = [ '$state', 'performanceService',
			'$uibModal', '$log', '$scope', 'toastr', 'ApiEndpoint', 'fileUpload', 'supplierService','$window'];
	PerformanceModalCtrl.$inject = [ '$uibModalInstance', 'items', '$scope', 'otdService', 'fpyService', 'ppmService', '$rootScope', 'performanceService' ];
	PerformanceModalAddEditCtrl.$inject = [ '$uibModalInstance', 'performance',
			'$scope', 'performanceService', 'supplierService','suppliertypeService' ];

	/* @ngInject */
	function PerformanceController($state, performanceService, $uibModal, $log,
			$scope, toastr, ApiEndpoint, fileUpload, supplierService,$window) {
		
		var perImportUrl = ApiEndpoint.url + "fileUpload"; 
		var performanceUrl = ApiEndpoint.url + "performance";
		
		var vm = angular.extend(this, {
			performances : [],
			performances1 : [],
			performances2 : [],
			suppliers : [],
			view : view,
			view2 : view2,
			add : add,
			delet : delet,
			uploadPerformamceXlxs : uploadPerformamceXlxs,
			uploadXlxs : uploadXlxs,
			send : send,
			send2 : send2,
			updatePath : updatePath
		});
		vm.myFile = 'afdsfd';
		(function activate() {
			$scope.showLoader = false;
			loadPerformances();
			
			$scope.performance = {};
			
			$scope.filePath = [];
			
			$scope.isDisabled = [];
			
			$scope.sendDisabled = [];
			
			loadSuppliers();
			
	//		loadPerformances1();
			
	//		loadPerformances2();
			
			$scope.file_changed = function(element){
				debugger;
				  var index = angular.element(element).scope().$index;
				  
				  $scope.isDisabled[index] = false;
				
			}
			
			
			
		})();

		// ******************************************************

		function loadPerformances2() {
			performanceService.getPerformances2().then(function(data) {
				vm.performances2 = data;
				
				console.log(JSON.stringify(vm.performances2));
			});
		}
		
		function loadPerformances1() {
			performanceService.getPerformances1().then(function(data) {
				vm.performances1 = data;
				
				console.log(JSON.stringify(vm.performances1));
			});
		}

		function loadSuppliers() {
			supplierService.getSuppliers().then(function(data) {
				vm.suppliers = data;
				
			});
		}

		$scope.$on('fileUploadExcel', function(event, data) {
			toastr.success('File Upload....', 'Succesful !!');
			//alert("data imported successfully");
			loadPerformances();

			angular.element("input[type='file']").val(null);
	    });
		
		function uploadPerformamceXlxs() {
			$scope.showLoader = true;
			var file = $scope.myXlFile;
			console.log('File is: ')
			console.dir(file);
			var uploadUrl = performanceUrl + "/uploadXlsFile";
			fileUpload.uploadFileToUrl(file, uploadUrl);
			$window.location.reload();
			angular.element("input[type='file']").val(null);
		}
		
		function uploadXlxs(val, performance) {

			debugger;
			
			$scope.performance = {};
			
			$scope.a = vm.myFile;
			
			var file = $scope.a.name;
			 
			

			console.log('File is: ')
			console.dir(file);
			var uploadUrl = perImportUrl + "/pdfFileUpload";
			fileUpload.uploadFileToUrl(file, uploadUrl);
			angular.element("input[type='file']").val(null);
			
			$scope.filePath[val] = "D:/SchindlerPerformance_pdf_files/"+ file;
			
			$scope.performance.performance_pdf = "D:/SchindlerPerformance_pdf_files/"+ file;
			
			$scope.performance.performance_suppliercode = performance.performance_suppliercode
			$scope.performance.performance_suppliername = performance.performance_suppliername 
			$scope.performance.performance_suppliertype = performance.performance_suppliertype
			$scope.performance.performance_otd = performance.performance_otd
			$scope.performance.performance_fpy = performance.performance_fpy
			$scope.performance.performance_ppm = performance.performance_ppm
 			$scope.performance.performance_year = performance.performance_year 
			$scope.performance.deletes = 1;

			$scope.performance.performance_id = performance.performance_id;
			
		//	loadPerformances();
			
			vm.updatePath(val,$scope.performance);
		}


		function updatePath(index, performance) {
			
			debugger;
			
			performanceService.addPerformance1(performance).then(function(data) {
	
				
				loadPerformances();
				$scope.sendDisabled[index] = false;
				
			});
		}

		
		
		function send(performance) {
			
			debugger;
			$scope.sup = vm.suppliers;

			$scope.supp_name = performance.performance_suppliername;

			for (var i = 0; i < $scope.sup.length; i++) {

				if ($scope.supp_name == $scope.sup[i].supplier_name) {

					$scope.performance.mail_id =  $scope.sup[i].supplier_email;
					$scope.performance.mail_id2 = $scope.sup[i].supplier_email2;
					$scope.performance.mail_id3 = $scope.sup[i].supplier_email3;
					$scope.performance.mail_id4 = $scope.sup[i].supplier_email4;
					$scope.performance.mail_id5 = $scope.sup[i].supplier_email5;
					$scope.performance.mail_id6 = $scope.sup[i].supplier_email6;
					$scope.performance.mail_id7 = $scope.sup[i].supplier_email7;
					$scope.performance.mail_id8 = $scope.sup[i].supplier_email8;
					$scope.performance.mail_id9 = $scope.sup[i].supplier_email9;
										
					break;
				}
			}

			performanceService.sendPerformance(performance).then(function() {
				
			});
		}


function send2(performance) {
			
			debugger;
			$scope.sup = vm.suppliers;

			$scope.supp_name = performance.performance_suppliername;

			for (var i = 0; i < $scope.sup.length; i++) {

				if ($scope.supp_name == $scope.sup[i].supplier_name) {

					
					$scope.performance.mobile_no =  $scope.sup[i].supplier_mobile;
					$scope.performance.mobile_no2 = $scope.sup[i].supplier_mobile2;
					$scope.performance.mobile_no3 = $scope.sup[i].supplier_mobile3;
					$scope.performance.mobile_no4 = $scope.sup[i].supplier_mobile4;
					$scope.performance.mobile_no5 = $scope.sup[i].supplier_mobile5;
					$scope.performance.mobile_no6 = $scope.sup[i].supplier_mobile6;
					$scope.performance.mobile_no7 = $scope.sup[i].supplier_mobile7;
					$scope.performance.mobile_no8 = $scope.sup[i].supplier_mobile8;
					$scope.performance.mobile_no9 = $scope.sup[i].supplier_mobile9;
		

					
					break;
				}
			}

			performanceService.sendPerformance2(performance).then(function() {
				
			});
		}

		
		
		
		function loadPerformances() {
			performanceService.getPerformances().then(function(data) {
				vm.performances = data;
				
				for(var i=0; i<vm.performances.length; i++){
					
					$scope.isDisabled[i] = true;
					
					if(vm.performances[i].performance_pdf==null){
					
						$scope.sendDisabled[i] = true;
						
					}
					
				}
				
				
				
				console.log(JSON.stringify(vm.performances));
			});
		}

		/*function delet(performance) {
			performanceService.deletePerformance(performance).then(function() {
				loadPerformances();
			});
		}*/
		
		function delet(performance){
			var usr = performance ? performance : {};
			var modalInstance = $uibModal.open({
				animation : true,
				ariaLabelledBy : 'modal-title',
				ariaDescribedBy : 'modal-body',
				templateUrl : 'templates/performance/performanceDelete.html',
				controller : 'PerformanceModalCtrl',
				controllerAs : 'vm',
				size : 'lg',
				resolve : {
					items : function() {
						return usr;
					}
				}
			});
		}
		$scope.$on('performanceDeleted', function(event, data) {
			loadPerformances();
	    });
		
		

		function view(performance) {
			var modalInstance = $uibModal
					.open({
						animation : true,
						ariaLabelledBy : 'modal-title',
						ariaDescribedBy : 'modal-body',
						templateUrl : 'templates/performance/performanceModelView.html',
						controller : 'PerformanceModalCtrl',
						controllerAs : 'vm',
						size : 'md',
						resolve : {
							items : function() {
								return performance;
							}
						}
					});

			modalInstance.result.then(function() {

			}, function() {
				$log.info('Modal dismissed at: ' + new Date());
			});
		}

		function view2(performance) {
			var modalInstance = $uibModal
					.open({
						animation : true,
						ariaLabelledBy : 'modal-title',
						ariaDescribedBy : 'modal-body',
						templateUrl : 'templates/performance/performanceModelView2.html',
						controller : 'PerformanceModalCtrl',
						controllerAs : 'vm',
						size : 'md',
						resolve : {
							items : function() {
								return performance;
							}
						}
					});

			modalInstance.result.then(function() {

			}, function() {
				$log.info('Modal dismissed at: ' + new Date());
			});
		}
		function add(performance) {
			var emp = performance ? performance : {};
			
			// alert(JSON.stringify(usr));
			var modalInstance = $uibModal
					.open({
						animation : true,
						ariaLabelledBy : 'modal-title',
						ariaDescribedBy : 'modal-body',
						templateUrl : 'templates/performance/performanceModelAddEdit.html',
						controller : 'PerformanceModalAddEditCtrl',
						controllerAs : 'vm',
						size : 'lg',
						resolve : {
							performance : function() {
								return emp;
							}
						}
					});

			modalInstance.result.then(function() {
				loadPerformances();
			}, function() {
				$log.info('Modal dismissed at: ' + new Date());
			});
		}

	}

	function PerformanceModalCtrl($uibModalInstance, items, $scope, otdService, fpyService, ppmService, $rootScope, performanceService) {
		var vm = angular.extend(this, {
			otds : [],
			fpys : [],
			ppms : [],
			items : items,
			ok : ok,
			cancel : cancel
		});

		(function activate() {
			
			$scope.onloadFun = function() {

				debugger;
				kendo.drawing.drawDOM($("#pdf")).then(function(group) {
					kendo.drawing.pdf.saveAs(group, vm.items.performance_suppliercode);
				});
			};

			loadOtds();
			loadFpys();
			loadPpms();
			
			$scope.suppliercode = vm.items.performance_suppliercode;
			$scope.suppliertype = vm.items.performance_suppliertype;
			$scope.year = vm.items.performance_year;
			
		
			
			
		
		})();

		// ******************************************************
		function loadOtds() {
			
			debugger;
			otdService.getOtds().then(function(data) {
				vm.otds = data;
				$scope.o = vm.otds;
				debugger;
				for(var i=0; i<$scope.o.length; i++){
					debugger;
					if($scope.suppliercode==$scope.o[i].otd_suppliercode && $scope.suppliertype == $scope.o[i].otd_suppliertype && $scope.year==$scope.o[i].otd_year)
				{
					$scope.otdytd = $scope.o[i].otd_ytd;
				}
				
				}
				
				console.log(JSON.stringify(vm.otds));
			});
		}
		

		function loadFpys() {
			
			fpyService.getFpys().then(function(data) {
				vm.fpys = data;
				$scope.o = vm.fpys;
				debugger;
				for(var i=0; i<$scope.o.length; i++){
					debugger;
					if($scope.suppliercode==$scope.o[i].fpy_suppliercode && $scope.suppliertype == $scope.o[i].fpy_suppliertype && $scope.year==$scope.o[i].fpy_year)
				{
					$scope.fpyytd = $scope.o[i].fpy_ytd;
					
					
				}
				
				}
				
				console.log(JSON.stringify(vm.fpys));
			});
		}
		

		function loadPpms() {
			
			ppmService.getPpms().then(function(data) {
				vm.ppms = data;
				$scope.o = vm.ppms;
				debugger;
				for(var i=0; i<$scope.o.length; i++){
					debugger;
					if($scope.suppliercode==$scope.o[i].ppm_suppliercode && $scope.suppliertype == $scope.o[i].ppm_supplierType && $scope.year==$scope.o[i].ppm_year)
				{
					$scope.ppmytd = $scope.o[i].ppm_ytd;
					
					
				}
				
				}
				
				console.log(JSON.stringify(vm.ppms));
			});
		}
		
		/*function ok() {
			$uibModalInstance.close();
		}*/
		function ok() {
			performanceService.deletePerformance(items).then(function(){
				$rootScope.$broadcast('performanceDeleted', {
	                data: 'something'
	            });
				$uibModalInstance.close();
			});
		}


		function cancel() {
			$uibModalInstance.dismiss('cancel');
		}
	}

	function PerformanceModalAddEditCtrl($uibModalInstance, performance,
			$scope, performanceService, supplierService, suppliertypeService) {
		var vm = angular.extend(this, {
			ok : ok,
			cancel : cancel,
			suppliers : [],
		});

		(function activate() {
			$scope.performance = performance;
			
			/*$scope.setPer = function() {
				$scope.potd = $scope.performance.performance_otd;
				$scope.performance.performance_otd = $scope.potd + "%";
			}*/

			$scope.setDeletes = function() {

				$scope.performance.deletes = 1;
				
				$scope.s = vm.suppliers;

				//$scope.performance.performance_otd = $scope.performance.performance_otd + "%" ;
				
				var sn = $scope.performance.performance_suppliername;

				for (var i = 0; i < $scope.s.length; i++) {

					if (sn == $scope.s[i].supplier_name) {

						$scope.performance.performance_suppliercode = $scope.s[i].supplier_code;
						
						$scope.performance.performance_suppliertype = $scope.s[i].supplier_type;
						
					}
				}
				
				if ($scope.performance.performance_suppliertype == "kw") {
					debugger;
					$scope.setFpy = false;
					
					performance.performance_pdf = null;
				}
				else{
					
					performance.performance_pdf = null;	
					$scope.setFpy = true;
				}
				
				if ($scope.performance.performance_fpy == "" || $scope.performance.performance_fpy == "%"){
					
					performance.performance_fpy = null;
				}

			}
			
			
			/*---------------year logic----------------*/
			$scope.year = new Date().getFullYear();
		    $scope.years = [];
		    for(var i = -3; i < 3; i++) {
		    		$scope.years.push({id: $scope.year + i});
		    }
		    
		    $scope.yearsStr = JSON.stringify($scope.years, null, 4);
		    /*---------------year logic----------------*/


			loadSuppliers();
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
				vm.supptypes = data;
			});
		}

		function ok(performance) {
			performanceService.addPerformance(performance).then(function() {
				$uibModalInstance.close(performance);
			});
		}

		function cancel() {
			$uibModalInstance.dismiss('cancel');
		}
	}
})();
