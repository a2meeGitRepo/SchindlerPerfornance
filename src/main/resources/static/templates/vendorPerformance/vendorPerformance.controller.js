(function() {
	'use strict';

	angular.module('myApp.vendorPerformance').controller(
			'VendorPerformanceController', VendorPerformanceController)
			.filter('unique', function () {

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
				});

;

	VendorPerformanceController.$inject = [ '$state', '$uibModal', '$log',
			'$scope', 'toastr', '$filter', 'supplierService', 'vpService',
			'suppliertypeService', 'performanceService', '$window' ];

	/* @ngInject */
	function VendorPerformanceController($state, $uibModal, $log, $scope,
			toastr, $filter, supplierService, vpService, suppliertypeService,
			performanceService, $window) {

		var vm = angular.extend(this, {
			suppliers : [],
			suppliertypes : [],
			fpy : [],
			otd : [],
			ppm : [],
			performance : [],
			vendorPefo : vendorPefo,
			getGraphs : getGraphs
		});

		(function activate() {

			$scope.otdjanClass = [];
			$scope.otdfebClass = [];
			$scope.otdmarClass = [];
			$scope.otdaprClass = [];
			$scope.otdmayClass = [];
			$scope.otdjuneClass = [];
			$scope.otdjulyClass = [];
			$scope.otdaugClass = [];
			$scope.otdsepClass = [];
			$scope.otdoctClass = [];
			$scope.otdnovClass = [];
			$scope.otddecClass = [];
			$scope.otdytdClass = [];

			$scope.fpyjanClass = [];
			$scope.fpyfebClass = [];
			$scope.fpymarClass = [];
			$scope.fpyaprClass = [];
			$scope.fpymayClass = [];
			$scope.fpyjuneClass = [];
			$scope.fpyjulyClass = [];
			$scope.fpyaugClass = [];
			$scope.fpysepClass = [];
			$scope.fpyoctClass = [];
			$scope.fpynovClass = [];
			$scope.fpydecClass = [];
			$scope.fpyytdClass = [];

			$scope.ppmjanClass = [];
			$scope.ppmfebClass = [];
			$scope.ppmmarClass = [];
			$scope.ppmaprClass = [];
			$scope.ppmmayClass = [];
			$scope.ppmjuneClass = [];
			$scope.ppmjulyClass = [];
			$scope.ppmaugClass = [];
			$scope.ppmsepClass = [];
			$scope.ppmoctClass = [];
			$scope.ppmnovClass = [];
			$scope.ppmdecClass = [];
			$scope.ppmytdClass = [];
			
			loadPerformances();
			$scope.data = [];

			$scope.vp = {};
			$scope.isDisabled = true;
			vm.fpytable = false;
			vm.fpyhidden = false;
			vm.searchHide = true;
			

			// $scope.percent = '100';
			$scope.newSupplier = function(otd) {
				$scope.otd={}
				loadSuppliers();
				 $window.location.reload();
				
			}
			
			
			$scope.setCode= function() {

				$scope.s = vm.suppliers;
			
				var sn = $scope.otd.otd_suppliername;
					for (var i = 0; i < $scope.s.length; i++) {
					 if (sn == $scope.s[i].supplier_name) {
						$scope.otd.otd_suppliercode = $scope.s[i].supplier_code;
					}
				}
			}
			$scope.setName = function() {

				$scope.s = vm.suppliers;
				var sc = $scope.otd.otd_suppliercode;
			
					for (var i = 0; i < $scope.s.length; i++) {
					if (sc == $scope.s[i].supplier_code) {
											
						$scope.otd.otd_suppliername = $scope.s[i].supplier_name;
						
					
					} 
				}
			}
			$scope.enable = function() {
				if ($scope.otd.otd_suppliercode != null
						&& $scope.otd.otd_suppliertype != null
						&& $scope.otd.year != null) {
					$scope.isDisabled = false;
				}
			}

			$scope.download = function(otd) {
				console.log("abcd"+JSON.stringify(otd))
				$scope.sup = vm.suppliers;
				$scope.supp_name = otd.otd_suppliername;
				var random_no = Math.floor(Math.random() * 1001);
				$scope.vp.file_name = $scope.supp_name + "-" + random_no;
				//$scope.vp.vendor_name = $scope.supp_name;
				$scope.file_name = $scope.vp.file_name;
				for (var i = 0; i < $scope.sup.length; i++) {
					if ($scope.supp_name == $scope.sup[i].supplier_name) {
						$scope.vp.vendor_name = $scope.sup[i].supplier_contactperson;;
						$scope.vp.mail_id = $scope.sup[i].supplier_email;
						$scope.vp.mail_id2 = $scope.sup[i].supplier_email2;
						$scope.vp.mail_id3 = $scope.sup[i].supplier_email3;
						$scope.vp.mail_id4 = $scope.sup[i].supplier_email4;
						$scope.vp.mail_id5 = $scope.sup[i].supplier_email5;
						$scope.vp.mail_id6 = $scope.sup[i].supplier_email6;
						$scope.vp.mail_id7 = $scope.sup[i].supplier_email7;
						$scope.vp.mail_id8 = $scope.sup[i].supplier_email8;
						$scope.vp.mail_id9 = $scope.sup[i].supplier_email9;
					}
				}

				kendo.drawing.drawDOM($("#pdf")).then(function(group) {
					kendo.drawing.pdf.saveAs(group, $scope.file_name);
				});

				setTimeout(function() {
					sendMail($scope.vp);
				}, 5000);

			}

			$scope.download1 = function(otd) {

				kendo.drawing.drawDOM($("#pdf")).then(function(group) {
					kendo.drawing.pdf.saveAs(group, otd.otd_suppliercode);
				});

			}

			function sendMail(otd) {
				vpService.sendMail(otd).then(function() {
				});
			}

			loadSuppliers();
			loadSuppliertypes();

		})();

		// ******************************************************

		function loadPerformances() {
			performanceService.getPerformances().then(function(data) {
				vm.performance = data;
			});
		}
		function vendorPefo(otd) {
			console.log("OTD +==="+JSON.stringify(otd))
			vm.searchHide = false;
			vm.buttonHide = true;
			vm.buttonHidenew = true;
			var ele = document.getElementById('newDiv');
			var con = document.getElementById('container');

			var ele1 = document.getElementById('newDiv1');
			var con1 = document.getElementById('container1');

			var ele2 = document.getElementById('newDiv2');
			var con2 = document.getElementById('container2');

			if (ele && ele.childNodes && ele.childNodes.length > 0)
				ele.removeChild(ele.childNodes[0]);
			if (con && con.childNodes && con.childNodes.length > 0)
				con.removeChild(con.childNodes[0]);

			if (ele1 && ele1.childNodes && ele1.childNodes.length > 0)
				ele1.removeChild(ele1.childNodes[0]);
			if (con1 && con1.childNodes && con1.childNodes.length > 0)
				con1.removeChild(con1.childNodes[0]);

			if (ele2 && ele2.childNodes && ele2.childNodes.length > 0)
				ele2.removeChild(ele2.childNodes[0]);
			if (con2 && con2.childNodes && con2.childNodes.length > 0)
				con2.removeChild(con2.childNodes[0]);

			vpService
					.vendorPefo(otd)
					.then(
							function(data) {
								vm.fpy = data.fpy;
								vm.otd = data.otd;
								vm.ppm = data.ppm;
								
								console.log(JSON.stringify(data));

								if (vm.otd != 0) {
									$scope.fpy = true;
								} else {

									$scope.fpy = false;
									vm.buttonHide = false;
									
									toastr.error('supplier does not exist');
								}

								var target_otd;
								var target_fpy;
								var target_ppm;

								for (var a = 0; a < vm.performance.length; a++) {
									var otd_suppliercode = vm.otd[0].otd_suppliercode;
									var otd_suppliertype = vm.otd[0].otd_suppliertype;
									var otd_year = vm.otd[0].otd_year;

									if (vm.performance[a].performance_suppliercode == otd_suppliercode
											&& vm.performance[a].performance_suppliertype == otd_suppliertype
											&& vm.performance[a].performance_year == otd_year) {
										var target_otd = parseFloat(vm.performance[a].performance_otd);
										var target_fpy = parseFloat(vm.performance[a].performance_fpy);
										var target_ppm = parseFloat(vm.performance[a].performance_ppm);
										$scope.percentotd = parseFloat(vm.performance[a].performance_otd);
										console.log("OTD TARGET =="+$scope.percentotd)
										$scope.percentfpy = parseFloat(vm.performance[a].performance_fpy);
										console.log("FPY TARGET =="+$scope.percentfpy)
										$scope.percentppm = parseFloat(vm.performance[a].performance_ppm);
										console.log("FPY TARGET =="+$scope.percentppm)

									}
								}

								/* .....................chart-----otd........................ */
								var otdjan1 = vm.otd[0].otd_jan;
								if(otdjan1==null){
									otdjan1=""
								}
								var otdjan = parseFloat(otdjan1.replace(/%/gi,
										""));
								var otd_feb1 = vm.otd[0].otd_feb;
								
								
								if(otd_feb1==null){
									otd_feb1=""
								}
								var otd_feb = parseFloat(otd_feb1.replace(
										/%/gi, ""));
								
								
								var otd_mar1 = vm.otd[0].otd_mar;
								if(otd_mar1==null){
									otd_mar1=""
								}
								var otd_mar = parseFloat(otd_mar1.replace(
										/%/gi, ""));
								
								
								var otd_apr1 = vm.otd[0].otd_apr;
								if(otd_apr1==null){
									otd_apr1=""
								}
								var otd_apr = parseFloat(otd_apr1.replace(
										/%/gi, ""));
								
								var otd_may1 = vm.otd[0].otd_may;
								if(otd_may1==null){
									otd_may1=""
								}
								var otd_may = parseFloat(otd_may1.replace(
										/%/gi, ""));
								
								
								var otd_june1 = vm.otd[0].otd_june;
								if(otd_june1==null){
									otd_june1=""
								}
								var otd_june = parseFloat(otd_june1.replace(
										/%/gi, ""));
								
								
								var otd_july1 = vm.otd[0].otd_july;
								if(otd_july1==null){
									otd_july1=""
								}
								var otd_july = parseFloat(otd_july1.replace(
										/%/gi, ""));
								
								
								var otd_aug1 = vm.otd[0].otd_aug;
								if(otd_aug1==null){
									otd_aug1=""
								}
								var otd_aug = parseFloat(otd_aug1.replace(
										/%/gi, ""));
								
								
								var otd_sep1 = vm.otd[0].otd_sep;
								if(otd_sep1==null){
									otd_sep1=""
								}
								var otd_sep = parseFloat(otd_sep1.replace(
										/%/gi, ""));
								
								
								var otd_oct1 = vm.otd[0].otd_oct;
								if(otd_oct1==null){
									otd_oct1=""
								}
								var otd_oct = parseFloat(otd_oct1.replace(
										/%/gi, ""));
								
								
								var otd_nov1 = vm.otd[0].otd_nov;
								if(otd_nov1==null){
									otd_nov1=""
								}
								var otd_nov = parseFloat(otd_nov1.replace(
										/%/gi, ""));
								
								
								var otd_dec1 = vm.otd[0].otd_dec;
								if(otd_dec1==null){
									otd_dec1=""
								}
								var otd_dec = parseFloat(otd_dec1.replace(
										/%/gi, ""));
								
								

								Highcharts.chart('container1', {
									chart : {
										type : 'line'
									},
									title : {
										text : "Target-" + target_otd + "%",
									},
									xAxis : {
										categories : [ 'Jan', 'Feb', 'Mar',
												'Apr', 'May', 'Jun', 'Jul',
												'Aug', 'Sep', 'Oct', 'Nov',
												'Dec' ]
									},
									yAxis : {
										max: 100,
								        min:0
									},
									plotOptions : {
										line : {
											dataLabels : {
												enabled : true
											},
											enableMouseTracking : false
										}
									},
									series : [
											{
												name : 'OTD',
												data : [ otdjan, otd_feb,
														otd_mar, otd_apr,
														otd_may, otd_june,
														otd_july, otd_aug,
														otd_sep, otd_oct,
														otd_nov, otd_dec ]
											},
											{
												name : 'TARGET',
												data : [ ]
											} ]
								});

								/*----------------------------otd graph-----------------------------------*/

								/* .....................chartppm........................ */
								var ppmjan1 = vm.ppm[0].ppm_jan;
								var ppmjan = parseFloat(ppmjan1.replace(/%/gi,
										""));
								var ppm_feb1 = vm.ppm[0].ppm_feb;
								var ppm_feb = parseFloat(ppm_feb1.replace(
										/%/gi, ""));
								var ppm_mar1 = vm.ppm[0].ppm_mar;
								var ppm_mar = parseFloat(ppm_mar1.replace(
										/%/gi, ""));
								var ppm_apr1 = vm.ppm[0].ppm_apr;
								var ppm_apr = parseFloat(ppm_apr1.replace(
										/%/gi, ""));
								var ppm_may1 = vm.ppm[0].ppm_may;
								var ppm_may = parseFloat(ppm_may1.replace(
										/%/gi, ""));
								var ppm_june1 = vm.ppm[0].ppm_june;
								var ppm_june = parseFloat(ppm_june1.replace(
										/%/gi, ""));
								var ppm_july1 = vm.ppm[0].ppm_july;
								var ppm_july = parseFloat(ppm_july1.replace(
										/%/gi, ""));
								var ppm_aug1 = vm.ppm[0].ppm_aug;
								var ppm_aug = parseFloat(ppm_aug1.replace(
										/%/gi, ""));
								var ppm_sep1 = vm.ppm[0].ppm_sep;
								var ppm_sep = parseFloat(ppm_sep1.replace(
										/%/gi, ""));
								var ppm_oct1 = vm.ppm[0].ppm_oct;
								var ppm_oct = parseFloat(ppm_oct1.replace(
										/%/gi, ""));
								var ppm_nov1 = vm.ppm[0].ppm_nov;
								var ppm_nov = parseFloat(ppm_nov1.replace(
										/%/gi, ""));
								var ppm_dec1 = vm.ppm[0].ppm_dec;
								var ppm_dec = parseFloat(ppm_dec1.replace(
										/%/gi, ""));
								
								vpService.getMaxPpm(otd.otd_suppliercode,otd.otd_suppliertype,otd.year).then(function(responce){
									
									if(responce<=450){
										$scope.maxppm=450
									}else{
										$scope.maxppm=responce
									}
									console.log("RESPONCE MAX  =============="+$scope.maxppm)
									
								
								Highcharts.chart('container2', {
									chart : {
										type : 'line'
									},
									title : {
										text : "Target-" + target_ppm,
									},
									xAxis : {
										categories : [ 'Jan', 'Feb', 'Mar',
												'Apr', 'May', 'Jun', 'Jul',
												'Aug', 'Sep', 'Oct', 'Nov',
												'Dec' ]
									},
									yAxis : {
										max: $scope.maxppm,
								        min:0
									},
									plotOptions : {
										line : {
											dataLabels : {
												enabled : true
											},
											enableMouseTracking : false
										}
									},
									series : [
											{
												name : 'PPM',
												data : [ ppmjan, ppm_feb,
														ppm_mar, ppm_apr,
														ppm_may, ppm_june,
														ppm_july, ppm_aug,
														ppm_sep, ppm_oct,
														ppm_nov, ppm_dec ]
											},
											{
												name : 'TARGET',
												data : [  ]
											} ]
								});
							})		
								/* .....................chart----end-----ppm........................ */

								/* .....................chart------------Fpy........................ */
								if(vm.fpy && vm.fpy.length > 0){
									
									var Fpyjan1 = vm.fpy[0].fpy_jan;
																		

									if(Fpyjan1 != null){
										var Fpyjan = parseFloat(Fpyjan1.replace(
												/%/gi, ""));
									}else{
										var Fpyjan =null
									}
									
									
									var fpy_feb1 = vm.fpy[0].fpy_feb;
									
									if(fpy_feb1 != null){
										var fpy_feb = parseFloat(fpy_feb1.replace(
												/%/gi, ""));
									}else{
										var fpy_feb =null
									}
									
									
									
									var fpy_mar1 = vm.fpy[0].fpy_mar;
									if(fpy_mar1 != null){
										var fpy_mar = parseFloat(fpy_mar1.replace(
												/%/gi, ""));
									}else{
										var fpy_mar =null
									}
									
									/*var fpy_mar = parseFloat(fpy_mar1.replace(
											/%/gi, ""));*/
									
									
									var fpy_apr1 = vm.fpy[0].fpy_apr;
									
									if(fpy_apr1 != null){
										var fpy_apr = parseFloat(fpy_apr1.replace(
												/%/gi, ""));
									}else{
										var fpy_apr =null
									}
									
									/*var fpy_apr = parseFloat(fpy_apr1.replace(
											/%/gi, ""));*/
									
									var fpy_may1 = vm.fpy[0].fpy_may;
									
									if(fpy_may1 != null){
										var fpy_may = parseFloat(fpy_may1.replace(
												/%/gi, ""));
									}else{
										var fpy_may =null
									}
									
									/*var fpy_may = parseFloat(fpy_may1.replace(
											/%/gi, ""));*/
									
									var fpy_june1 = vm.fpy[0].fpy_june;
									
									if(fpy_june1 != null){
										var fpy_june = parseFloat(fpy_june1.replace(
												/%/gi, ""));
									}else{
										var fpy_june =null
									}
									
									/*var fpy_june = parseFloat(fpy_june1.replace(
											/%/gi, ""));*/
									
									
									var fpy_july1 = vm.fpy[0].fpy_july;
									
									if(fpy_july1 != null){
										var fpy_july = parseFloat(fpy_july1.replace(
												/%/gi, ""));
									}else{
										var fpy_july =null
									}
									
									var fpy_aug1 = vm.fpy[0].fpy_aug;
									
									if(fpy_aug1 != null){
										var fpy_aug = parseFloat(fpy_aug1.replace(
												/%/gi, ""));
									}else{
										var fpy_aug =null
									}
									/*var fpy_aug = parseFloat(fpy_aug1.replace(
											/%/gi, ""));*/
									
									var fpy_sep1 = vm.fpy[0].fpy_sep;
									if(fpy_sep1 != null){
										var fpy_sep = parseFloat(fpy_sep1.replace(
												/%/gi, ""));
									}else{
										var fpy_sep =null
									}
									/*var fpy_sep = parseFloat(fpy_sep1.replace(
											/%/gi, ""));*/
									var fpy_oct1 = vm.fpy[0].fpy_oct;
									
									if(fpy_oct1 != null){
										var fpy_oct = parseFloat(fpy_oct1.replace(
												/%/gi, ""));
									}else{
										var fpy_oct =null
									}
									/*var fpy_oct = parseFloat(fpy_oct1.replace(
											/%/gi, ""));*/
									
									var fpy_nov1 = vm.fpy[0].fpy_nov;
									if(fpy_nov1 != null){
										var fpy_nov = parseFloat(fpy_nov1.replace(
												/%/gi, ""));
									}else{
										var fpy_nov =null
									}
									/*var fpy_nov = parseFloat(fpy_nov1.replace(
											/%/gi, ""));*/
									
									var fpy_dec1 = vm.fpy[0].fpy_dec;
									if(fpy_dec1 != null){
										var fpy_dec = parseFloat(fpy_dec1.replace(
												/%/gi, ""));
									}else{
										var fpy_dec =null
									}
									/*var fpy_dec = parseFloat(fpy_dec1.replace(
											/%/gi, ""));
*/
									Highcharts.chart('container', {
										chart : {
											type : 'line'
										},
										title : {
											text : "Target-" + target_fpy + "%",
										},
										xAxis : {
											categories : [ 'Jan', 'Feb', 'Mar',
													'Apr', 'May', 'Jun', 'Jul',
													'Aug', 'Sep', 'Oct', 'Nov',
													'Dec' ]
										},
										yAxis : {
											 	max: 100,
										        min:0
										},
										plotOptions : {
											line : {
												dataLabels : {
													enabled : true
												},
												enableMouseTracking : false
											}
										},
										series : [
												{
													name : 'FPY',
													data : [ Fpyjan, fpy_feb,
															fpy_mar, fpy_apr,
															fpy_may, fpy_june,
															fpy_july, fpy_aug,
															fpy_sep, fpy_oct,
															fpy_nov, fpy_dec ]
												},
												{
													name : 'TARGET',
													data : [  ]
												} ]
									});
									
								}
								
								/* .....................chart-----end------Fpy........................ */

								
/*---------------------------------------------------------fpy not available---------------------------------------------------------*/
								Highcharts.chart('container4', {
									chart : {
										type : 'line'
									},
									title : {
										text : "Target-" + target_otd + "%",
									},
									xAxis : {
										categories : [ 'Jan', 'Feb', 'Mar',
												'Apr', 'May', 'Jun', 'Jul',
												'Aug', 'Sep', 'Oct', 'Nov',
												'Dec' ]
									},
									yAxis : {
										max: 100,
								        min:0
									},
									plotOptions : {
										line : {
											dataLabels : {
												enabled : true
											},
											enableMouseTracking : false
										}
									},
									series : [
											{
												name : 'OTD',
												data : [ otdjan, otd_feb,
														otd_mar, otd_apr,
														otd_may, otd_june,
														otd_july, otd_aug,
														otd_sep, otd_oct,
														otd_nov, otd_dec ]
											},
											{
												name : 'TARGET',
												data : [ ]
											} ]
								});
								
							
								
								vpService.getMaxPpm(otd.otd_suppliercode,otd.otd_suppliertype,otd.year).then(function(responce){
									
									if(responce<=450){
										$scope.maxppm=450
									}else{
										$scope.maxppm=responce
									}
								Highcharts.chart('container5', {
									chart : {
										type : 'line'
									},
									title : {
										text : "Target-" + target_ppm,
									},
									xAxis : {
										categories : [ 'Jan', 'Feb', 'Mar',
												'Apr', 'May', 'Jun', 'Jul',
												'Aug', 'Sep', 'Oct', 'Nov',
												'Dec' ]
									},
									yAxis : {
										max: $scope.maxppm,
								        min:0
									},
									plotOptions : {
										line : {
											dataLabels : {
												enabled : true
											},
											enableMouseTracking : false
										}
									},
									series : [
											{
												name : 'PPM',
												data : [ ppmjan, ppm_feb,
														ppm_mar, ppm_apr,
														ppm_may, ppm_june,
														ppm_july, ppm_aug,
														ppm_sep, ppm_oct,
														ppm_nov, ppm_dec ]
											},
											{
												name : 'TARGET',
												data : [  ]
											} ]
								});
							});
/*---------------------------------------------------------fpy not available---------------------------------------------------------*/								
								
								/* .....................low----performance---fpy---red........................ */
								for (var a = 0; a < vm.fpy.length; a++) {
									for (var b = 0; b < vm.performance.length; b++) {
										debugger;
										var fpyPerf = parseFloat(vm.performance[b].performance_fpy);
										var suppCodePerf = vm.performance[b].performance_suppliercode;
										var suppTypePerf = vm.performance[b].performance_suppliertype;
										var suppYearPerf = vm.performance[b].performance_year;

										if (vm.fpy[a].fpy_suppliercode == suppCodePerf
												&& vm.fpy[a].fpy_suppliertype == suppTypePerf
												&& vm.fpy[a].fpy_year == suppYearPerf) {
											debugger;

											$scope.percentotd = parseFloat(vm.performance[a].performance_otd);
											console.log("OTD TARGET =="+$scope.percentotd)
											$scope.percentfpy = parseFloat(vm.performance[a].performance_fpy);
											console.log("FPY TARGET =="+$scope.percentfpy)
											$scope.percentppm = vm.performance[a].performance_ppm;
											console.log("FPY TARGET =="+$scope.percentppm)

											break;
										}
									}
								}
								/* .....................end---low----performance---fpy---red........................ */
								
								getGraphs();

							});

		}

		function getGraphs() {
			if(vm.fpy && vm.fpy.length > 0){
				vm.fpytable = true;
				vm.fpyhidden = false;
			}else{
				vm.fpytable = false;
				vm.fpyhidden = true
				
			}
			setTimeout(function() {

						var svg1 = document.getElementById('container1').childNodes[0].childNodes[0];
						var xml1 = new XMLSerializer().serializeToString(svg1);
						var data1 = "data:image/svg+xml;base64," + btoa(xml1);
						var img1 = new Image();
						img1.src = data1;
						document.getElementById('newDiv1').appendChild(img1);
						
						var ele = document.getElementById('fpytable');
						
						if(vm.fpy && vm.fpy.length > 0){
							var svg = document.getElementById('container').childNodes[0].childNodes[0];
							var xml = new XMLSerializer().serializeToString(svg);
							var data = "data:image/svg+xml;base64," + btoa(xml);
							var img = new Image();
							img.src = data;
							document.getElementById('newDiv').appendChild(img);
						}	
						
						
							
						var svg2 = document.getElementById('container2').childNodes[0].childNodes[0];					
						var xml2 = new XMLSerializer().serializeToString(svg2);
						var data2 = "data:image/svg+xml;base64," + btoa(xml2);
						var img2 = new Image();
						img2.src = data2;
						document.getElementById('newDiv2').appendChild(img2);

						
						var svg4 = document.getElementById('container4').childNodes[0].childNodes[0];					
						var xml4 = new XMLSerializer().serializeToString(svg4);
						var data4 = "data:image/svg+xml;base64," + btoa(xml4);
						var img4 = new Image();
						img4.src = data4;
						document.getElementById('newDiv4').appendChild(img4);
						
						
						var svg5 = document.getElementById('container5').childNodes[0].childNodes[0];					
						var xml5 = new XMLSerializer().serializeToString(svg5);
						var data5 = "data:image/svg+xml;base64," + btoa(xml5);
						var img5 = new Image();
						img5.src = data5;
						document.getElementById('newDiv5').appendChild(img5);
					}, 2000);
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

	}
})();
