(function() {
	'use strict';

	angular.module('myApp.report2').controller('Report2Controller',
			Report2Controller);

	Report2Controller.$inject = [ '$state', 'performanceService', '$uibModal',
			'$log', '$scope', 'toastr', '$filter', 'supplierService',
			'suppliertypeService' ];

	/* @ngInject */
	function Report2Controller($state, performanceService, $uibModal, $log,
			$scope, toastr, $filter, supplierService, suppliertypeService) {

		var vm = angular.extend(this, {
			performances : [],
			suppliers : [],
			suppliertypes : [],
			performanceObject : [],
			search : search,
			view : view,
			fnExcelReport:fnExcelReport
		});

		(function activate() {

			$scope.yr = new Date().getFullYear();

			$scope.myDynamicClassOtd = [];
			$scope.febClassOtd = [];
			$scope.marClassOtd = [];
			$scope.aprClassOtd = [];
			$scope.mayClassOtd = [];
			$scope.juneClassOtd = [];
			$scope.julyClassOtd = [];
			$scope.augClassOtd = [];
			$scope.sepClassOtd = [];
			$scope.octClassOtd = [];
			$scope.novClassOtd = [];
			$scope.decClassOtd = [];
			$scope.ytdClassOtd = [];

			$scope.myDynamicClassPpm = [];
			$scope.febClassPpm = [];
			$scope.marClassPpm = [];
			$scope.aprClassPpm = [];
			$scope.mayClassPpm = [];
			$scope.juneClassPpm = [];
			$scope.julyClassPpm = [];
			$scope.augClassPpm = [];
			$scope.sepClassPpm = [];
			$scope.octClassPpm = [];
			$scope.novClassPpm = [];
			$scope.decClassPpm = [];
			$scope.ytdClassPpm = [];

			$scope.myDynamicClassFpy = [];
			$scope.febClassFpy = [];
			$scope.marClassFpy = [];
			$scope.aprClassFpy = [];
			$scope.mayClassFpy = [];
			$scope.juneClassFpy = [];
			$scope.julyClassFpy = [];
			$scope.augClassFpy = [];
			$scope.sepClassFpy = [];
			$scope.octClassFpy = [];
			$scope.novClassFpy = [];
			$scope.decClassFpy = [];
			$scope.ytdClassFpy = [];

			$scope.setDeletes = function() {

				debugger;

				$scope.s = vm.suppliers;

				var sc = $scope.performance.performance_suppliercode;
				var sn = $scope.performance.performance_suppliername;

				for (var i = 0; i < $scope.s.length; i++) {

					if (sc == $scope.s[i].supplier_code) {

						$scope.performance.performance_suppliername = $scope.s[i].supplier_name;

					} else if (sn == $scope.s[i].supplier_name) {

						$scope.performance.performance_suppliercode = $scope.s[i].supplier_code;

					}
				}

			}

			loadPerformances();
			loadSuppliers();
			loadSuppliertypes();
		})();

		// ******************************************************
		
		
		function fnExcelReport()
		{
		    var tab_text="<table border='2px'><tr bgcolor='#87AFC6'>";
		    var textRange; var j=0;
		  var   tab = document.getElementById('t_body1'); // id of table

		    for(j = 0 ; j < tab.rows.length ; j++) 
		    {     
		        tab_text=tab_text+tab.rows[j].innerHTML+"</tr>";
		        //tab_text=tab_text+"</tr>";
		    }

		    tab_text=tab_text+"</table>";
		    tab_text= tab_text.replace(/<A[^>]*>|<\/A>/g, "");//remove if u want links in your table
		    tab_text= tab_text.replace(/<img[^>]*>/gi,""); // remove if u want images in your table
		    tab_text= tab_text.replace(/<input[^>]*>|<\/input>/gi, ""); // reomves input params

		    var ua = window.navigator.userAgent;
		    var msie = ua.indexOf("MSIE "); 

		    if (msie > 0 || !!navigator.userAgent.match(/Trident.*rv\:11\./))      // If Internet Explorer
		    {
		        txtArea1.document.open("txt/html","replace");
		        txtArea1.document.write(tab_text);
		        txtArea1.document.close();
		        txtArea1.focus(); 
		        sa=txtArea1.document.execCommand("SaveAs",true,"Say Thanks to Sumit.xls");
		    }  
		    else                 //other browser not tested on IE 11
		        sa = window.open('data:application/vnd.ms-excel,' + encodeURIComponent(tab_text));  

		    return (sa);
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

		function loadPerformances() {
			performanceService.getPerformances().then(function(data) {
				vm.performanceObject = data;
				console.log(JSON.stringify(vm.performanceObject));
			});
		}

		function search(per) {

			performanceService.searchPerformance(per).then(function(data) {

								vm.performances = data;
								
								
								if(vm.performances.length == 0){
									$scope.fpy = false;
									toastr.error('Insert valid year');
								}else{
									$scope.fpy = true;
								}
								
								for (var a = 0; a < vm.performances.length; a++) {

									for (var b = 0; b < vm.performanceObject.length; b++) {

										var fpyPerf = parseFloat(vm.performanceObject[b].performance_fpy);
										var otdPerf = parseFloat(vm.performanceObject[b].performance_otd);
										var ppmPerf = parseFloat(vm.performanceObject[b].performance_ppm);

										var suppCodePerf = vm.performanceObject[b].performance_suppliercode;
										var suppTypePerf = vm.performanceObject[b].performance_suppliertype;
										var suppYearPerf = vm.performanceObject[b].performance_year;

										if (vm.performances[a].ppm_suppliercode == suppCodePerf
												&& vm.performances[a].ppm_suppliertype == suppTypePerf
												&& per.performance_year == suppYearPerf) {

											var janFpy = parseFloat(vm.performances[a].fpy_jan);
											var febFpy = parseFloat(vm.performances[a].fpy_feb);
											var marFpy = parseFloat(vm.performances[a].fpy_mar);
											var aprFpy = parseFloat(vm.performances[a].fpy_apr);
											var mayFpy = parseFloat(vm.performances[a].fpy_may);
											var juneFpy = parseFloat(vm.performances[a].fpy_june);
											var julyFpy = parseFloat(vm.performances[a].fpy_july);
											var augFpy = parseFloat(vm.performances[a].fpy_aug);
											var sepFpy = parseFloat(vm.performances[a].fpy_sep);
											var octFpy = parseFloat(vm.performances[a].fpy_oct);
											var novFpy = parseFloat(vm.performances[a].fpy_nov);
											var decFpy = parseFloat(vm.performances[a].fpy_dec);
											var ytdFpy = parseFloat(vm.performances[a].fpy_ytd);

											if (janFpy < fpyPerf) {
												$scope.myDynamicClassFpy[a] = 'redD';
											}
											if (febFpy < fpyPerf) {
												$scope.febClassFpy[a] = 'redD';
											}
											if (marFpy < fpyPerf) {
												$scope.marClassFpy[a] = 'redD';
											}
											if (aprFpy < fpyPerf) {
												$scope.aprClassFpy[a] = 'redD';
											}
											if (mayFpy < fpyPerf) {
												$scope.mayClassFpy[a] = 'redD';
											}
											if (juneFpy < fpyPerf) {
												$scope.juneClassFpy[a] = 'redD';
											}
											if (julyFpy < fpyPerf) {
												$scope.julyClassFpy[a] = 'redD';
											}
											if (augFpy < fpyPerf) {
												$scope.augClassFpy[a] = 'redD';
											}
											if (sepFpy < fpyPerf) {
												$scope.sepClassFpy[a] = 'redD';
											}
											if (octFpy < fpyPerf) {
												$scope.octClassFpy[a] = 'redD';
											}
											if (novFpy < fpyPerf) {
												$scope.novClassFpy[a] = 'redD';
											}
											if (decFpy < fpyPerf) {
												$scope.decClassFpy[a] = 'redD';
											}
											if (ytdFpy < fpyPerf) {
												$scope.ytdClassFpy[a] = 'redD';
											}

											var janOtd = parseFloat(vm.performances[a].otd_jan);
											var febOtd = parseFloat(vm.performances[a].otd_feb);
											var marOtd = parseFloat(vm.performances[a].otd_mar);
											var aprOtd = parseFloat(vm.performances[a].otd_apr);
											var mayOtd = parseFloat(vm.performances[a].otd_may);
											var juneOtd = parseFloat(vm.performances[a].otd_june);
											var julyOtd = parseFloat(vm.performances[a].otd_july);
											var augOtd = parseFloat(vm.performances[a].otd_aug);
											var sepOtd = parseFloat(vm.performances[a].otd_sep);
											var octOtd = parseFloat(vm.performances[a].otd_oct);
											var novOtd = parseFloat(vm.performances[a].otd_nov);
											var decOtd = parseFloat(vm.performances[a].otd_dec);
											var ytdOtd = parseFloat(vm.performances[a].otd_ytd);

											if (janOtd < otdPerf) {
												$scope.myDynamicClassOtd[a] = 'redD';
											}
											if (febOtd < otdPerf) {
												$scope.febClassOtd[a] = 'redD';
											}
											if (marOtd < otdPerf) {
												$scope.marClassOtd[a] = 'redD';
											}
											if (aprOtd < otdPerf) {
												$scope.aprClassOtd[a] = 'redD';
											}
											if (mayOtd < otdPerf) {
												$scope.mayClassOtd[a] = 'redD';
											}
											if (juneOtd < otdPerf) {
												$scope.juneClassOtd[a] = 'redD';
											}
											if (julyOtd < otdPerf) {
												$scope.julyClassOtd[a] = 'redD';
											}
											if (augOtd < otdPerf) {
												$scope.augClassOtd[a] = 'redD';
											}
											if (sepOtd < otdPerf) {
												$scope.sepClassOtd[a] = 'redD';
											}
											if (octOtd < otdPerf) {
												$scope.octClassOtd[a] = 'redD';
											}
											if (novOtd < otdPerf) {
												$scope.novClassOtd[a] = 'redD';
											}
											if (decOtd < otdPerf) {
												$scope.decClassOtd[a] = 'redD';
											}
											if (ytdOtd < otdPerf) {
												$scope.ytdClassOtd[a] = 'redD';
											}

											var janPpm = parseFloat(vm.performances[a].ppm_jan);
											var febPpm = parseFloat(vm.performances[a].ppm_feb);
											var marPpm = parseFloat(vm.performances[a].ppm_mar);
											var aprPpm = parseFloat(vm.performances[a].ppm_apr);
											var mayPpm = parseFloat(vm.performances[a].ppm_may);
											var junePpm = parseFloat(vm.performances[a].ppm_june);
											var julyPpm = parseFloat(vm.performances[a].ppm_july);
											var augPpm = parseFloat(vm.performances[a].ppm_aug);
											var sepPpm = parseFloat(vm.performances[a].ppm_sep);
											var octPpm = parseFloat(vm.performances[a].ppm_oct);
											var novPpm = parseFloat(vm.performances[a].ppm_nov);
											var decPpm = parseFloat(vm.performances[a].ppm_dec);
											var ytdPpm = parseFloat(vm.performances[a].ppm_ytd);

											if (janPpm > ppmPerf) {
												$scope.myDynamicClassPpm[a] = 'redD';
											}
											if (febPpm > ppmPerf) {
												$scope.febClassPpm[a] = 'redD';
											}
											if (marPpm > ppmPerf) {
												$scope.marClassPpm[a] = 'redD';
											}
											if (aprPpm > ppmPerf) {
												$scope.aprClassPpm[a] = 'redD';
											}
											if (mayPpm > ppmPerf) {
												$scope.mayClassPpm[a] = 'redD';
											}
											if (junePpm > ppmPerf) {
												$scope.juneClassPpm[a] = 'redD';
											}
											if (julyPpm > ppmPerf) {
												$scope.julyClassPpm[a] = 'redD';
											}
											if (augPpm > ppmPerf) {
												$scope.augClassPpm[a] = 'redD';
											}
											if (sepPpm > ppmPerf) {
												$scope.sepClassPpm[a] = 'redD';
											}
											if (octPpm > ppmPerf) {
												$scope.octClassPpm[a] = 'redD';
											}
											if (novPpm > ppmPerf) {
												$scope.novClassPpm[a] = 'redD';
											}
											if (decPpm > ppmPerf) {
												$scope.decClassPpm[a] = 'redD';
											}
											if (ytdPpm < ppmPerf) {
												$scope.ytdClassPpm[a] = 'redD';
											}
										}
									}

								}
							});
		}

		function view(performance) {
			var modalInstance = $uibModal.open({
				animation : true,
				ariaLabelledBy : 'modal-title',
				ariaDescribedBy : 'modal-body',
				templateUrl : 'templates/report2/performanceModelView.html',
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

	}
})();
