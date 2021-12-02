(function() {
	'use strict';

	angular.module('myApp.sendsms')
			.controller('SendsmsController', SendsmsController)
			.controller('SendsmsModalCtrl', SendsmsModalCtrl)
			.controller('SendsmsModalAddEditCtrl', SendsmsModalAddEditCtrl)
			.controller('TransferModalAddEditCtrl', TransferModalAddEditCtrl);

	SendsmsController.$inject = [ '$state', 'sendsmsService', '$uibModal', '$log','$scope', 'fileUpload', 'toastr', 'ApiEndpoint', 'employeeService','$window', 'supplierService'];
	SendsmsModalCtrl.$inject = [ '$uibModalInstance', 'items', '$scope','sendsmsService', 'employeeService', '$filter' ];
	SendsmsModalAddEditCtrl.$inject = [ '$uibModalInstance', '$filter', 'sendsms','$scope', 'sendsmsService' ];
	TransferModalAddEditCtrl.$inject = [ '$uibModalInstance', 'items','$scope', 'sendsmsService', 'locationService', 'departmentService','employeeService', 'categoryService' ];

	/* @ngInject */
	function SendsmsController($state, sendsmsService, $uibModal, $log, $scope,fileUpload, toastr, ApiEndpoint, employeeService,$window, supplierService) {

		var sendsmsUrl = ApiEndpoint.url + "sendsms";

		var vm = angular.extend(this, {
			
			sendsmss : [],
			suppliers : [],
			send : send
		
		});

		(function activate() {

			loadSuppliers();
			
			$scope.sms = {};
			
			
			$scope.getMob =  function(){
				
				debugger;
			
				$scope.supp_name = $scope.sms.supp_name;
				
				$scope.supp = vm.suppliers;
				
				for(var i=0; i<$scope.supp.length; i++){
					
					if($scope.supp_name==$scope.supp[i].supplier_name){
						$scope.sms.mob ='';
						if($scope.supp[i].supplier_mobile !=null){
							$scope.sms.mob +=$scope.supp[i].supplier_mobile;
						}
						if($scope.supp[i].supplier_mobile2 !=null){
							$scope.sms.mob +=$scope.supp[i].supplier_mobile2;
						}
						if($scope.supp[i].supplier_mobile3 !=null){
							$scope.sms.mob +=','+$scope.supp[i].supplier_mobile3;
						}
						if($scope.supp[i].supplier_mobile4 !=null){
							$scope.sms.mob +=','+$scope.supp[i].supplier_mobile4;
						}
						if($scope.supp[i].supplier_mobile5 !=null){
							$scope.sms.mob +=','+$scope.supp[i].supplier_mobile5;
						}
						if($scope.supp[i].supplier_mobile6 !=null){
							$scope.sms.mob +=','+$scope.supp[i].supplier_mobile6;
						}
						if($scope.supp[i].supplier_mobile7 !=null){
							$scope.sms.mob +=','+$scope.supp[i].supplier_mobile7;
						}
						if($scope.supp[i].supplier_mobile8 !=null){
							$scope.sms.mob +=','+$scope.supp[i].supplier_mobile8;
						}
						if($scope.supp[i].supplier_mobile9 !=null){
							$scope.sms.mob +=','+$scope.supp[i].supplier_mobile9;
						}
					//	$scope.sms.mob = $scope.supp[i].supplier_mobile + ","+$scope.supp[i].supplier_mobile2 + ","+$scope.supp[i].supplier_mobile3 + ","+$scope.supp[i].supplier_mobile4 + ","+$scope.supp[i].supplier_mobile5 + ","+$scope.supp[i].supplier_mobile6 + ","+$scope.supp[i].supplier_mobile7 + ","+$scope.supp[i].supplier_mobile8 + ","+$scope.supp[i].supplier_mobile9;
					
						break;
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
		
		
		function send(sms) {
			
			debugger;
			
			sendsmsService.sendSms(sms).then(function(data) {
			
				$scope.sms = {};
				loadSendsmss();
			});
		}

		

		
		$scope.selectAll = function(){
	       
	        vm.lst = vm.sendsmss;
	        
	        $scope.sendsms = vm.lst;
	        console.log("Values:"+JSON.stringify(vm.lst));
		}
		
		$scope.change = function(sendsms, active){
	        if (active)
	            vm.lst.push(sendsms);
	        else
	            vm.lst.splice(vm.lst.indexOf(sendsms), 1);
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
			
		
		}
		
		
		function view(sendsms) {
			var modalInstance = $uibModal.open({
				animation : true,
				ariaLabelledBy : 'modal-title',
				ariaDescribedBy : 'modal-body',
				templateUrl : 'templates/sendsms/sendsmsModelView.html',
				controller : 'SendsmsModalCtrl',
				controllerAs : 'vm',
				size : 'md',
				resolve : {
					items : function() {
						return sendsms;
					}
				}
			});

			modalInstance.result.then(function() {

			}, function() {
				$log.info('Modal dismissed at: ' + new Date());
			});

		}

		
		

	}

	function SendsmsModalCtrl($uibModalInstance, items, $scope, sendsmsService,
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
					    kendo.drawing.pdf.saveAs(group, vm.items.sendsms_no);
					  });



				setTimeout(function() {

					$uibModalInstance.dismiss('cancel');

					//$scope.loadEmp(vm.items);

					sendMail(items);

					sendSms(items);
					
					//loadSendsmss();

				}, 5000);

			};

			
		
			function sendMail(items) {
				debugger;

			
				sendsmsService.sendMail(items).then(function() {

				});
			}
			

			function sendSms(items) {
				debugger;
				sendsmsService.sendSms(items).then(function() {

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

	function SendsmsModalAddEditCtrl($uibModalInstance, $filter, sendsms, $scope,
			sendsmsService) {
		var vm = angular.extend(this, {
			ok : ok,
			tr : tr,
			cancel : cancel,

			sendsmss : [],
			getIinExists : getIinExists,
		});

		(function activate() {
			$scope.sendsms = sendsms;

			loadSendsmss();

			$scope.getVal = function() {

				$scope.selectedLocation = $scope.sendsms.location.location_id;

			}

			$scope.serchEmp = function() {
				$scope.employee = vm.employees;
				var id = $scope.sendsms.employee.employee_id;
				for (var i = 0; i < $scope.employee.length; i++) {
					if ($scope.employee[i].employee_id == id) {
						$scope.sendsms.emp_name = $scope.employee[i].name;
					}
				}
			}

			$scope.setFormDate = function() {

				$scope.sd = $scope.sendsms.regdate;
				$scope.gd = $scope.sendsms.po_date;
				$scope.td = $scope.sendsms.invoice_date;

				$scope.sendsms.regdate = $filter('date')($scope.sd, "yyyy-MM-dd");
				$scope.sendsms.po_date = $filter('date')($scope.gd, "yyyy-MM-dd");
				$scope.sendsms.invoice_date = $filter('date')($scope.td,
						"yyyy-MM-dd");
			}

		})();

		// ******************************************************

		function loadSendsmss() {
			sendsmsService.getSendsmss().then(function(data) {
				vm.sendsmss = data;
				console.log(JSON.stringify(vm.sendsmss));
			});
		}

		function ok(sendsms) {
			var btn = document.getElementById("submit");
			btn.disabled = true;
			sendsmsService.addSendsms(sendsms).then(function() {
				loadSendsmss();
				$uibModalInstance.close(sendsms);
				// toastr.success('Sendsms Added....', 'Succesful !!');
			});
		}

		function tr(transfer) {
			sendsmsService.addTransfer(transfer).then(function() {
				$uibModalInstance.close(transfer);
			});
		}

		function getIinExists() {
			console.log("Hi.." + $scope.sendsms.iin)
			var iin_no = $scope.sendsms.iin;
			angular.forEach(vm.sendsmss, function(sendsms, iin_no) {
				if (iin_no == sendsms.iin) {
					console.log("iin already Exists");
				}
			});
		}

		function cancel() {
			$uibModalInstance.dismiss('cancel');
		}
	}

	function TransferModalAddEditCtrl($uibModalInstance, sendsms, $scope,
			sendsmsService, locationService, departmentService, employeeService) {
		var vm = angular.extend(this, {
			ok : ok,
			cancel : cancel,
			// items : items,
			locations : [],
			departments : [],
			employees : [],
			sendsmss : []
		});

		(function activate() {
			$scope.sendsms = sendsms;
			loadLocations();
			loadDepartments();
			loadEmployees();
			loadSendsmss();

		})();

		// ******************************************************

		function loadSendsmss() {
			sendsmsService.getSendsmss().then(function(data) {
				vm.sendsmss = data;
				console.log(JSON.stringify(vm.sendsmss));
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

		function ok(sendsms) {
			var btn = document.getElementById("submit1");
			btn.disabled = true;
			sendsmsService.addSendsms(sendsms).then(function() {
				loadSendsmss();
				$uibModalInstance.close(sendsms);
				// toastr.success('Sendsms Added....', 'Succesful !!');
			});
		}

		function cancel() {
			$uibModalInstance.dismiss('cancel');
		}
	}
})();
