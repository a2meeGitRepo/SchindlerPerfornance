(function() {
	'use strict';

	angular.module('myApp.report').controller('ReportController', ReportController);
		

	ReportController.$inject = [ '$state', 'escalationService', '$uibModal', '$log', '$scope', 'toastr', '$filter', 'supplierService', 'suppliertypeService'  ];
	
	/* @ngInject */
	function ReportController($state, escalationService, $uibModal, $log, $scope, toastr, $filter, supplierService, suppliertypeService) {
		
		var vm = angular.extend(this, {
			escalations : [],
			suppliers : [],
			suppliertypes : [],
			search : search,
			view : view,
		});

		(function activate() {
			
			
			$scope.setDeletes = function() {
				
				debugger;
				
				$scope.s = vm.suppliers;

				var sc = $scope.escalation.escalation_suppliercode;
				var sn = $scope.escalation.escalation_suppliername;

				for (var i = 0; i < $scope.s.length; i++) {

					if (sc == $scope.s[i].supplier_code) {

						$scope.escalation.escalation_suppliername = $scope.s[i].supplier_name;

					} else if (sn == $scope.s[i].supplier_name) {

						$scope.escalation.escalation_suppliercode = $scope.s[i].supplier_code;

					}
				}

			}
			
			loadEscalations();
			loadSuppliers();
			loadSuppliertypes();
		})();

		// ******************************************************
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
		
		
		
		function loadEscalations() {
			escalationService.getEscalations().then(function(data) {
				vm.escalations = data;
				console.log(JSON.stringify(vm.escalations));
			});
		}
		
	
		function search(escalation) {
			
			if(escalation == undefined){
				toastr.error('Select atleast one feild', 'Error !!');
				return;
			}
			escalationService.searchEscalation(escalation).then(function(data){
				vm.escalations = data;
				console.log(JSON.stringify(vm.escalations))
			});
		}
		
		function view(escalation) {
			var modalInstance = $uibModal.open({
				animation : true,
				ariaLabelledBy : 'modal-title',
				ariaDescribedBy : 'modal-body',
				templateUrl : 'templates/report/escalationModelView.html',
				controller : 'EscalationModalCtrl',
				controllerAs : 'vm',
				size : 'md',
				resolve : {
					items : function() {
						return escalation;
					}
				}
			});

			modalInstance.result.then(function() {

			}, function() {
				$log.info('Modal dismissed at: ' + new Date());
			});
		}


	}
})();
