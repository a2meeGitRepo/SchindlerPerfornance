(function() {
	'use strict';

	angular.module('myApp.escalation').controller('EscalationController',
			EscalationController).controller('EscalationModalCtrl',
			EscalationModalCtrl);

	EscalationController.$inject = [ '$state', 'escalationService',
			'$uibModal', '$log', '$scope', 'fileUpload', 'toastr',
			'ApiEndpoint', '$filter', 'localStorageService', '$window',
			'supplierService', 'suppliertypeService', 'performanceService' ];
	EscalationModalCtrl.$inject = [ '$uibModalInstance', 'items', '$scope',
			'$window' ];

	/* @ngInject */
	function EscalationController($state, escalationService, $uibModal, $log,
			$scope, fileUpload, toastr, ApiEndpoint, $filter,
			localStorageService, $window, supplierService, suppliertypeService,
			performanceService) {

		var escalationImportUrl = ApiEndpoint.url + "fileUpload";
		var vm = angular.extend(this, {
			escalations : [],
			performances : [],
			suppliers : [],
			suppliertypes : [],
			esc : [],
			ok : ok,
			delet : delet,
			uploadXlxs : uploadXlxs,
			send : send,
			view : view,
			view2 : view2,
		});

		(function activate() {

			$scope.escalation = {};

			$scope.no = "No";
			$scope.no1 = "No";
			$scope.no2 = "No";
			$scope.no3 = "No";
			$scope.no4 = "No";
			$scope.no5 = "No";
			$scope.no6 = "No";
			$scope.no7 = "No";
			$scope.no8 = "No";

			vm.searchHide = true;

			$scope.input1 = true;

			$scope.sendDisable1 = true;
			$scope.sendDisable2 = true;
			$scope.sendDisable3 = true;
			$scope.sendDisable4 = true;
			$scope.sendDisable5 = true;
			$scope.sendDisable6 = true;
			$scope.sendDisable7 = true;
			$scope.sendDisable8 = true;
			$scope.sendDisable9 = true;

			$scope.isDisabled = true;
			$scope.enable = function() {
				$scope.isDisabled = false;
			}
			$scope.isDisabled2 = true;
			$scope.enable2 = function() {
				$scope.isDisabled2 = false;
			}
			$scope.isDisabled3 = true;
			$scope.enable3 = function() {
				$scope.isDisabled3 = false;
			}
			$scope.isDisabled4 = true;
			$scope.enable4 = function() {
				$scope.isDisabled4 = false;
			}
			$scope.isDisabled5 = true;
			$scope.enable5 = function() {
				$scope.isDisabled5 = false;
			}
			$scope.isDisabled6 = true;
			$scope.enable6 = function() {
				$scope.isDisabled6 = false;
			}
			$scope.isDisabled7 = true;
			$scope.enable7 = function() {
				$scope.isDisabled7 = false;
			}
			$scope.isDisabled8 = true;
			$scope.enable8 = function() {
				$scope.isDisabled8 = false;
			}
			$scope.isDisabled9 = true;
			$scope.enable9 = function() {
				$scope.isDisabled9 = false;
			}

			$scope.setDeletes = function() {
				$scope.escalation.deletes = 1;
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
			$scope.refresh1 = function() {
				toastr.success('Escalation Added....', 'Succesful !!');
				$window.location.reload();
			}
			$scope.refresh = function() {
				view2();
			}

			$scope.getExistingData = function(escalation) {
				$scope.code = escalation.escalation_suppliercode;
				$scope.sup_name = escalation.escalation_suppliername;
				$scope.sup_type = escalation.escalation_suppliertype;
				$scope.yr = escalation.escalation_year;
				$scope.esc = vm.escalations;
				for (var i = 0; i < $scope.esc.length; i++) {
					if ($scope.code == $scope.esc[i].escalation_suppliercode
							&& $scope.sup_name == $scope.esc[i].escalation_suppliername
							&& $scope.sup_type == $scope.esc[i].escalation_suppliertype
							&& $scope.yr == $scope.esc[i].escalation_year) {
						$scope.escalation.escalation_warning = $scope.esc[i].escalation_warning;
						$scope.escalation.escalation_warningdate = $scope.esc[i].escalation_warningdate;
						if ($scope.escalation.escalation_warning != null) {
							$scope.yes = "Yes";
							$scope.no = "";
							$scope.isDisabled = true;
							$scope.sendDisable1 = false;
						}
						$scope.escalation.escalation_planreceive1 = $scope.esc[i].escalation_planreceive1;
						$scope.escalation.escalation_planreceive1date = $scope.esc[i].escalation_planreceive1date;
						if ($scope.escalation.escalation_planreceive1 != null) {
							$scope.yes1 = "Yes";
							$scope.no1 = "";
							$scope.isDisabled2 = true;
							$scope.sendDisable2 = false;
						}
						$scope.escalation.escalation_planvalidated1 = $scope.esc[i].escalation_planvalidated1;
						$scope.escalation.escalation_planvalidated1date = $scope.esc[i].escalation_planvalidated1date;
						if ($scope.escalation.escalation_planvalidated1 != null) {
							$scope.yes2 = "Yes";
							$scope.no2 = "";
							$scope.isDisabled3 = true;
							$scope.sendDisable3 = false;
						}
						$scope.escalation.escalation_level1 = $scope.esc[i].escalation_level1;
						$scope.escalation.escalation_level1date = $scope.esc[i].escalation_level1date;
						if ($scope.escalation.escalation_level1 != null) {
							$scope.yes3 = "Yes";
							$scope.no3 = "";
							$scope.isDisabled4 = true;
							$scope.sendDisable4 = false;
						}
						$scope.escalation.escalation_planreceive2 = $scope.esc[i].escalation_planreceive2;
						$scope.escalation.escalation_planreceive2date = $scope.esc[i].escalation_planreceive2date;
						if ($scope.escalation.escalation_planreceive2 != null) {
							$scope.yes4 = "Yes";
							$scope.no4 = "";
							$scope.isDisabled5 = true;
							$scope.sendDisable5 = false;
						}
						$scope.escalation.escalation_planvalidated2 = $scope.esc[i].escalation_planvalidated2;
						$scope.escalation.escalation_planvalidated2date = $scope.esc[i].escalation_planvalidated2date;
						if ($scope.escalation.escalation_planvalidated2 != null) {
							$scope.yes5 = "Yes";
							$scope.no5 = "";
							$scope.isDisabled6 = true;
							$scope.sendDisable6 = false;
						}
						$scope.escalation.escalation_deescalated = $scope.esc[i].escalation_deescalated;
						$scope.escalation.escalation_deescalateddate = $scope.esc[i].escalation_deescalateddate;
						if ($scope.escalation.escalation_deescalated != null) {
							$scope.yes6 = "Yes";
							$scope.no6 = "";
							$scope.isDisabled7 = true;
							$scope.sendDisable7 = false;
						}
						$scope.escalation.escalation_level2 = $scope.esc[i].escalation_level2;
						$scope.escalation.escalation_level2date = $scope.esc[i].escalation_level2date;
						if ($scope.escalation.escalation_level2 != null) {
							$scope.yes7 = "Yes";
							$scope.no7 = "";
							$scope.isDisabled8 = true;
							$scope.sendDisable8 = false;
						}
						$scope.escalation.escalation_discontinued = $scope.esc[i].escalation_discontinued;
						$scope.escalation.escalation_date = $scope.esc[i].escalation_date;
						if ($scope.escalation.escalation_discontinued != null) {
							$scope.yes8 = "Yes";
							$scope.no8 = "";
							$scope.isDisabled9 = true;
							$scope.sendDisable9 = false;
						}
						break;
					}

					else {
						$scope.escalation.escalation_warning = null;
						$scope.escalation.escalation_warningdate = null;
						$scope.escalation.escalation_planreceive1 = null;
						$scope.escalation.escalation_planreceive1date = null;
						$scope.escalation.escalation_planvalidated1 = null;
						$scope.escalation.escalation_planvalidated1date = null;
						$scope.escalation.escalation_level1 = null;
						$scope.escalation.escalation_level1date = null;
						$scope.escalation.escalation_planreceive2 = null;
						$scope.escalation.escalation_planreceive2date = null;
						$scope.escalation.escalation_planvalidated2 = null;
						$scope.escalation.escalation_planvalidated2date = null;
						$scope.escalation.escalation_deescalated = null;
						$scope.escalation.escalation_deescalateddate = null;
						$scope.escalation.escalation_level2 = null;
						$scope.escalation.escalation_level2date = null;
						$scope.escalation.escalation_discontinued = null;
						$scope.escalation.escalation_date = null;
					}
				}
			}

			$scope.findSupplie = function() {
				debugger;
				$scope.sup = vm.performances;
				$scope.supp_code = $scope.escalation.escalation_suppliercode;
				$scope.supp_type = $scope.escalation.escalation_suppliertype;
				$scope.supp_year = $scope.escalation.escalation_year;

				for (var i = 0; i < $scope.sup.length; i++) {
					if ($scope.supp_code == $scope.sup[i].performance_suppliercode
							&& $scope.supp_type == $scope.sup[i].performance_suppliertype
							&& $scope.supp_year == $scope.sup[i].performance_year) {

						vm.searchHide = false;
						$scope.input1 = false;
						break;
					} else {

						vm.searchHide = true;
						$scope.sendDisable1 = true;
						$scope.sendDisable2 = true;
						$scope.sendDisable3 = true;
						$scope.sendDisable4 = true;
						$scope.sendDisable5 = true;
						$scope.sendDisable6 = true;
						$scope.sendDisable7 = true;
						$scope.sendDisable8 = true;
						$scope.sendDisable9 = true;
						$scope.isDisabled = true;
						$scope.isDisabled2 = true;
						$scope.isDisabled3 = true;
						$scope.isDisabled4 = true;
						$scope.isDisabled5 = true;
						$scope.isDisabled6 = true;
						$scope.isDisabled7 = true;
						$scope.isDisabled8 = true;
						$scope.isDisabled9 = true;
						$scope.input1 = true;
					}
				}
			}

			loadEscalations();
			loadSuppliers();
			loadSuppliertypes();
			loadPerformances();

		})();

		// ******************************************************

		function uploadXlxs(val) {

			var file = $scope.myFile;
			console.log('File is: ')
			console.dir(file);
			var uploadUrl = escalationImportUrl + "/pdfFileUpload";
			fileUpload.uploadFileToUrl(file, uploadUrl);
			angular.element("input[type='file']").val(null);
			$scope.currentDate = new Date();

			if (val == 1) {
				$scope.escalation.escalation_warning = "C:/SchindlerPerformance_pdf_files/"
						+ file.name;
				$scope.yes = "Yes";
				$scope.no = "";
				$scope.sendDisable1 = false;
				$scope.escalation.escalation_warningdate = $filter('date')(
						$scope.currentDate, "dd/MM/yyyy");
				$scope.isDisabled = true;
			}

			if (val == 2) {
				$scope.escalation.escalation_planreceive1 = "C:/SchindlerPerformance_pdf_files/"
						+ file.name;
				$scope.yes1 = "Yes";
				$scope.no1 = "";
				$scope.sendDisable2 = false;
				$scope.isDisabled2 = true;
				$scope.escalation.escalation_planreceive1date = $filter('date')
						($scope.currentDate, "dd/MM/yyyy");
			}

			if (val == 3) {
				$scope.escalation.escalation_planvalidated1 = "C:/SchindlerPerformance_pdf_files/"
						+ file.name;
				$scope.yes2 = "Yes";
				$scope.no2 = "";
				$scope.sendDisable3 = false;
				$scope.isDisabled3 = true;
				$scope.escalation.escalation_planvalidated1date = $filter(
						'date')($scope.currentDate, "dd/MM/yyyy");
			}

			if (val == 4) {
				$scope.escalation.escalation_level1 = "C:/SchindlerPerformance_pdf_files/"
						+ file.name;
				$scope.yes3 = "Yes";
				$scope.no3 = "";
				$scope.sendDisable4 = false;
				$scope.isDisabled4 = true;
				$scope.escalation.escalation_level1date = $filter('date')(
						$scope.currentDate, "dd/MM/yyyy");
			}

			if (val == 5) {
				$scope.escalation.escalation_planreceive2 = "C:/SchindlerPerformance_pdf_files/"
						+ file.name;
				$scope.yes4 = "Yes";
				$scope.no4 = "";
				$scope.sendDisable5 = false;
				$scope.isDisabled5 = true;
				$scope.escalation.escalation_planreceive2date = $filter('date')
						($scope.currentDate, "dd/MM/yyyy");
			}

			if (val == 6) {
				$scope.escalation.escalation_planvalidated2 = "C:/SchindlerPerformance_pdf_files/"
						+ file.name;
				$scope.yes5 = "Yes";
				$scope.no5 = "";
				$scope.sendDisable6 = false;
				$scope.isDisabled6 = true;
				$scope.escalation.escalation_planvalidated2date = $filter(
						'date')($scope.currentDate, "dd/MM/yyyy");
			}

			if (val == 7) {
				$scope.escalation.escalation_deescalated = "C:/SchindlerPerformance_pdf_files/"
						+ file.name;
				$scope.yes6 = "Yes";
				$scope.no6 = "";
				$scope.sendDisable7 = false;
				$scope.isDisabled7 = true;
				$scope.escalation.escalation_deescalateddate = $filter('date')(
						$scope.currentDate, "dd/MM/yyyy");
			}

			if (val == 8) {
				$scope.escalation.escalation_level2 = "C:/SchindlerPerformance_pdf_files/"
						+ file.name;
				$scope.yes7 = "Yes";
				$scope.no7 = "";
				$scope.sendDisable8 = false;
				$scope.isDisabled8 = true;
				$scope.escalation.escalation_level2date = $filter('date')(
						$scope.currentDate, "dd/MM/yyyy");
			}

			if (val == 9) {
				$scope.escalation.escalation_discontinued = "C:/SchindlerPerformance_pdf_files/"
						+ file.name;
				$scope.yes8 = "Yes";
				$scope.no8 = "";
				$scope.sendDisable9 = false;
				$scope.isDisabled9 = true;
				$scope.escalation.escalation_date = $filter('date')(
						$scope.currentDate, "dd/MM/yyyy");
			}
		}

		function view(escalation) {
			var modalInstance = $uibModal.open({
				animation : true,
				ariaLabelledBy : 'modal-title',
				ariaDescribedBy : 'modal-body',
				templateUrl : 'templates/escalation/escalationModelView.html',
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

		function view2(escalation) {
			var modalInstance = $uibModal.open({
				animation : true,
				ariaLabelledBy : 'modal-title',
				ariaDescribedBy : 'modal-body',
				templateUrl : 'templates/escalation/escalationNewPage.html',
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

		function ok(escalation) {

			escalationService.addEscalation(escalation).then(function() {
				loadEscalations();
			});
		}

		function send(escalation) {

			$scope.sup = vm.suppliers;
			$scope.supp_name = escalation.escalation_suppliername;
			for (var i = 0; i < $scope.sup.length; i++) {

				if ($scope.supp_name == $scope.sup[i].supplier_name) {
					$scope.escalation.mail_id = $scope.sup[i].supplier_email;
					$scope.escalation.mail_id2 = $scope.sup[i].supplier_email2;
					$scope.escalation.mail_id3 = $scope.sup[i].supplier_email3;
					$scope.escalation.mail_id4 = $scope.sup[i].supplier_email4;
					$scope.escalation.mail_id5 = $scope.sup[i].supplier_email5;
					$scope.escalation.mail_id6 = $scope.sup[i].supplier_email6;
					$scope.escalation.mail_id7 = $scope.sup[i].supplier_email7;
					$scope.escalation.mail_id8 = $scope.sup[i].supplier_email8;
					$scope.escalation.mail_id9 = $scope.sup[i].supplier_email9;
				}
			}
			escalationService.sendMail(escalation).then(function() {
				loadEscalations();
			});
		}

		function loadPerformances() {
			performanceService.getPerformances().then(function(data) {
				vm.performances = data;
				console.log(JSON.stringify(vm.performances));
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

		function loadEscalations() {
			escalationService.getEscalations().then(function(data) {

				if ($scope.escalation.escalation_suppliercode == null) {

					vm.searchHide = false;
				}
				vm.escalations = data;
				console.log(JSON.stringify(vm.escalations));
			});
		}

		function delet(escalation) {
			escalationService.deleteEscalation(escalation).then(function() {
				loadEscalations();
			});
		}
	}

	function EscalationModalCtrl($uibModalInstance, items, $scope, $window) {
		var vm = angular.extend(this, {
			items : items,
			ok : ok,
			yes : yes,
			cancel : cancel
		});

		(function activate() {

		})();

		// ******************************************************

		function yes() {
			$window.location.reload();
		}

		function ok() {
			$uibModalInstance.close();
		}

		function cancel() {
			$uibModalInstance.dismiss('cancel');
		}
	}
})();
