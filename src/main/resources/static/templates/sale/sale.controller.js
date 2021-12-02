(function() {
	'use strict';

	angular.module('myApp.sale')
			.controller('SaleController', SaleController)
			.controller('SaleModalCtrl', SaleModalCtrl)
			.controller(	'SaleModalAddEditCtrl', SaleModalAddEditCtrl)
			.controller('TransferModalAddEditCtrl', TransferModalAddEditCtrl);

	SaleController.$inject = [ '$state', 'saleService', '$uibModal', '$log','$scope', 'fileUpload', 'toastr', 'ApiEndpoint', 'employeeService','$window' ];
	SaleModalCtrl.$inject = [ '$uibModalInstance', 'items', '$scope','saleService', 'employeeService', '$filter' ];
	SaleModalAddEditCtrl.$inject = [ '$uibModalInstance', '$filter', 'sale','$scope', 'saleService' ];
	TransferModalAddEditCtrl.$inject = [ '$uibModalInstance', 'items','$scope', 'saleService', 'locationService', 'departmentService','employeeService', 'categoryService' ];

	/* @ngInject */
	function SaleController($state, saleService, $uibModal, $log, $scope,fileUpload, toastr, ApiEndpoint, employeeService,$window) {

		var saleUrl = ApiEndpoint.url + "sale";

		var vm = angular.extend(this, {
			sales : [],
			emps : [],
			lst : [],
			locations : [],
			departments : [],
			categories : [],
			view : view,
			uploadXlxs : uploadXlxs,
			downloadFile : downloadFile,
			add : add,
			transfer : transfer,
			delet : delet,
			delet1 : delet1,
			search : search,
		});

		(function activate() {

			loadSales();
			loadEmployees();
			
			/* var getAllSelected = function () {
			      
				 loadSales();
				 
			    }
			
			var setAllSelected = function (value) {
				
				
				
		        angular.forEach($scope.sales, function (sale) {
		        	abcd = value;
		        });
		    }
			
			 $scope.allSelected = function (value) {
				
			        if (value !== undefined) {
			            return setAllSelected(value);
			        } else {
			            return getAllSelected();
			        }
			    }*/

		})();

		// ******************************************************

		$scope.selectAll = function(){
	       
	        vm.lst = vm.sales;
	        
	        $scope.sale = vm.lst;
	        console.log("Values:"+JSON.stringify(vm.lst));
		}
		
		$scope.change = function(sale, active){
	        if (active)
	            vm.lst.push(sale);
	        else
	            vm.lst.splice(vm.lst.indexOf(sale), 1);
	        console.log("Values:"+JSON.stringify(vm.lst));
		}
		
		$scope.sendSelected = function(){
			
			debugger;
			
			
			 for (var i = 0; i < vm.lst.length; i++) {
			        (function(i){
			           
			            setTimeout(function() {
			            	
			            	delet(vm.lst[i]);
							view(vm.lst[i]);
			            	
			            }, (6000 * i));
			        })(i);
			    }
			
			
			/*angular.forEach(vm.lst, function(sale, key) {
	
				 delet(sale);

				

					view(sale);
					 
				
				
			});*/
		}
		
		function loadSales() {
			saleService.getSales().then(function(data) {
				vm.sales = data;
				
				console.log(JSON.stringify(vm.sales));
			//	$window.location.reload();
			});
			
			
		}

		function loadEmployees() {
			employeeService.getEmployees().then(function(data) {
				vm.emps = data;

			});
		}

		function search(sale) {
			console.log(JSON.stringify(sale))
			saleService.searchSale(sale).then(function(data) {
				vm.sales = data;
			});
		}

		function delet(sale) {
			saleService.deleteSale(sale).then(function() {
				loadSales();
			});
		}
		
		function delet1(sale) {
			saleService.delete1Sale(sale).then(function() {
				loadSales();
			});
		}

		function view(sale) {
			var modalInstance = $uibModal.open({
				animation : true,
				ariaLabelledBy : 'modal-title',
				ariaDescribedBy : 'modal-body',
				templateUrl : 'templates/sale/saleModelView.html',
				controller : 'SaleModalCtrl',
				controllerAs : 'vm',
				size : 'md',
				resolve : {
					items : function() {
						return sale;
					}
				}
			});

			modalInstance.result.then(function() {

			}, function() {
				$log.info('Modal dismissed at: ' + new Date());
			});

		}

		
		function uploadXlxs() {

			debugger;
			
			var file = $scope.myFile;
			console.log('File is: ')
			console.dir(file);
			var uploadUrl = saleUrl + "/uploadFile";
			console.log("File Upload Status:"+fileUpload.uploadFileToUrl(file, uploadUrl));
		
			//	$window.location.reload();
			
			
				angular.element("input[type='file']").val(null);
				
			  
			 setTimeout(function(){ 
				 
				 	$window.location.reload();
					
			    }, 3000);
		
		}
		
		

		function downloadFile() {
			saleService.downloadFile().then(function() {
				loadSales();
			});
		}

		function add(sale) {

			var ast = sale ? sale : {};
			// alert(JSON.stringify(usr));
			var modalInstance = $uibModal.open({
				animation : true,
				ariaLabelledBy : 'modal-title',
				ariaDescribedBy : 'modal-body',
				templateUrl : 'templates/sale/saleModelAddEdit.html',
				controller : 'SaleModalAddEditCtrl',
				controllerAs : 'vm',
				size : 'lg',
				resolve : {
					sale : function() {
						return ast;
					}
				}
			});

			modalInstance.result.then(function() {
				loadSales();
			}, function() {
				$log.info('Modal dismissed at: ' + new Date());
			});
		}

		function transfer(sale) {
			var modalInstance = $uibModal.open({
				animation : true,
				ariaLabelledBy : 'modal-title',
				ariaDescribedBy : 'modal-body',
				templateUrl : 'templates/sale/transferModelAddEdit.html',
				controller : 'TransferModalAddEditCtrl',
				controllerAs : 'vm',
				size : 'md',
				resolve : {
					items : function() {
						return sale;
					}
				}
			});

			modalInstance.result.then(function() {

			}, function() {
				$log.info('Modal dismissed at: ' + new Date());
			});
		}

	}

	function SaleModalCtrl($uibModalInstance, items, $scope, saleService,
			employeeService,$filter) {
		var vm = angular.extend(this, {
			items : items,
			ok : ok,
			tr : tr,
			cancel : cancel,
			emps : [],
			Arr : []
		});

		(function activate() {

			loadEmployees();

			$scope.Arr = [];
			
			$scope.c_date  = new Date();
			$scope.cd =   $filter('date')($scope.c_date, "dd-MMM-yyyy");
			
			$scope.onloadFun = function() {
				
			
				
				debugger;
				
				 kendo.drawing.drawDOM($("#pdf")).then(function(group) {
					    kendo.drawing.pdf.saveAs(group, vm.items.sale_no);
					  });



				setTimeout(function() {

					$uibModalInstance.dismiss('cancel');

					//$scope.loadEmp(vm.items);

					sendMail(items);

					sendSms(items);
					
					//loadSales();

				}, 5000);

			};

			
			
			
			/*$scope.loadEmp = function(sale) {

				$scope.emp = vm.emps;

				$scope.sales_man = sale.acp;

				for (var i = 0; i < $scope.emp.length; i++) {

					if ($scope.sales_man == $scope.emp[i].name) {

						vm.items.mails = $scope.emp[i].email;
						vm.items.mob = $scope.emp[i].mobile_no;

					}

				}

			}
*/
			
			function sendMail(items) {
				debugger;

			
				saleService.sendMail(items).then(function() {

				});
			}
			

			function sendSms(items) {
				debugger;
				saleService.sendSms(items).then(function() {

				});
			}

		})();

		// ******************************************************

		function loadEmployees() {
			employeeService.getEmployees().then(function(data) {
				vm.emps = data;

			});
		}

		function ok() {
			$uibModalInstance.close();
		}

		function tr() {
			$uibModalInstance.close();
		}

		function cancel() {
			$uibModalInstance.dismiss('cancel');
		}
	}

	function SaleModalAddEditCtrl($uibModalInstance, $filter, sale, $scope,
			saleService) {
		var vm = angular.extend(this, {
			ok : ok,
			tr : tr,
			cancel : cancel,

			sales : [],
			getIinExists : getIinExists,
		});

		(function activate() {
			$scope.sale = sale;

			loadSales();

			$scope.getVal = function() {

				$scope.selectedLocation = $scope.sale.location.location_id;

			}

			$scope.serchEmp = function() {
				$scope.employee = vm.employees;
				var id = $scope.sale.employee.employee_id;
				for (var i = 0; i < $scope.employee.length; i++) {
					if ($scope.employee[i].employee_id == id) {
						$scope.sale.emp_name = $scope.employee[i].name;
					}
				}
			}

			$scope.setFormDate = function() {

				$scope.sd = $scope.sale.regdate;
				$scope.gd = $scope.sale.po_date;
				$scope.td = $scope.sale.invoice_date;

				$scope.sale.regdate = $filter('date')($scope.sd, "yyyy-MM-dd");
				$scope.sale.po_date = $filter('date')($scope.gd, "yyyy-MM-dd");
				$scope.sale.invoice_date = $filter('date')($scope.td,
						"yyyy-MM-dd");
			}

		})();

		// ******************************************************

		function loadSales() {
			saleService.getSales().then(function(data) {
				vm.sales = data;
				console.log(JSON.stringify(vm.sales));
			});
		}

		function ok(sale) {
			var btn = document.getElementById("submit");
			btn.disabled = true;
			saleService.addSale(sale).then(function() {
				loadSales();
				$uibModalInstance.close(sale);
				// toastr.success('Sale Added....', 'Succesful !!');
			});
		}

		function tr(transfer) {
			saleService.addTransfer(transfer).then(function() {
				$uibModalInstance.close(transfer);
			});
		}

		function getIinExists() {
			console.log("Hi.." + $scope.sale.iin)
			var iin_no = $scope.sale.iin;
			angular.forEach(vm.sales, function(sale, iin_no) {
				if (iin_no == sale.iin) {
					console.log("iin already Exists");
				}
			});
		}

		function cancel() {
			$uibModalInstance.dismiss('cancel');
		}
	}

	function TransferModalAddEditCtrl($uibModalInstance, sale, $scope,
			saleService, locationService, departmentService, employeeService) {
		var vm = angular.extend(this, {
			ok : ok,
			cancel : cancel,
			// items : items,
			locations : [],
			departments : [],
			employees : [],
			sales : []
		});

		(function activate() {
			$scope.sale = sale;
			loadLocations();
			loadDepartments();
			loadEmployees();
			loadSales();

		})();

		// ******************************************************

		function loadSales() {
			saleService.getSales().then(function(data) {
				vm.sales = data;
				console.log(JSON.stringify(vm.sales));
			});
		}

		function loadLocations() {
			locationService.getLocations().then(function(data) {
				vm.locations = data;
			});
		}

		function loadEmployees() {
			employeeService.getEmployees().then(function(data) {
				vm.employees = data;
				console.log(JSON.stringify(vm.employees));
			});
		}

		function loadDepartments() {
			departmentService.getDepartments().then(function(data) {
				vm.departments = data;
			});
		}

		function ok(sale) {
			var btn = document.getElementById("submit1");
			btn.disabled = true;
			saleService.addSale(sale).then(function() {
				loadSales();
				$uibModalInstance.close(sale);
				// toastr.success('Sale Added....', 'Succesful !!');
			});
		}

		function cancel() {
			$uibModalInstance.dismiss('cancel');
		}
	}
})();
