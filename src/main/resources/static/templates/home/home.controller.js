(function() {
	'use strict';

	angular.module('myApp.home').controller('HomeController', HomeController);

	HomeController.$inject = [ 'localStorageService', '$scope', 'ApiEndpoint',
			'$state', 'userService', 'supplierService', 'fpyService','otdService', 'ppmService', 'escalationService', '$filter', 'performanceService','lowService' ];

	/* @ngInject */
	function HomeController(localStorageService, $scope, ApiEndpoint, $state,
			userService, supplierService, fpyService, otdService, ppmService,
			escalationService, $filter, performanceService,lowService) {

		var userDetail = localStorageService.get(ApiEndpoint.userKey);

		// console.log(JSON.stringify(userDetail));
		var vm = angular.extend(this, {
			user : userDetail,
			users : [],
			avgs : [],
			avgs2 : [],
			avgs3 : [],
			avgs4 : [],
			avgs5 : [],
			otds : [],
			fpys : [],
			ppms : [],
			escalations : [],
			ppm2s : [],
			performances1 : [],
			performances2 : [],
			performances : [],
			lowPer : [],
			perMst : [],
			arr2 : [],
		});

		(function activate() {
			
			const monthNames = ["January", "February", "March", "April", "May", "June",
			                    "July", "August", "September", "October", "November", "December"
			                  ];
			
			$scope.currentDate = new Date();
			$scope.year = $filter('date')($scope.currentDate, "yyyy");
			var m = $scope.currentDate.getMonth() - 1;
			if(m < 0){
				m = 11;
				$scope.back_year = $filter('date')($scope.currentDate, "yyyy")-1;
			}else{
				$scope.back_year = $filter('date')($scope.currentDate, "yyyy");
			}
			
			$scope.month =	monthNames[m];	// $scope.currentDate.getMonth();//$filter('date')($scope.currentDate, "MMMM");

			$scope.c = sessionStorage.getItem("countss");
			$scope.c2 = sessionStorage.getItem("count");
			$scope.c3 = sessionStorage.getItem("count3");

			$scope.l1 = sessionStorage.getItem("low1");
			$scope.l2 = sessionStorage.getItem("low2");
			$scope.l3 = sessionStorage.getItem("low3");

			if ($scope.l1 == null) {
				$scope.l1 = 0;
			}
			if ($scope.l2 == null) {
				$scope.l2 = 0;
			}
			if ($scope.l3 == null) {
				$scope.l3 = 0;
			}
			if ($scope.c == null) {
				$scope.c = 0;
			}
			if ($scope.c2 == null) {
				$scope.c2 = 0;
			}
			if ($scope.c3 == null) {
				$scope.c3 = 0;
			}

			$scope.count = parseInt($scope.c) + parseInt($scope.c2) + parseInt($scope.c3);
			$scope.low = parseInt($scope.l1) + parseInt($scope.l2) + parseInt($scope.l3);

			loadFpys();
			loadOtds();
			loadPpms();
			loadEscalations();
			loadUsers();
			loadAvg();
			loadAvg2();
			loadAvg3();
			loadAvg4();
			loadAvg5();

			
			loadSuppliers();
			loadOtdMonthwise();
			loadFpyMonthwise();
			loadPpmMonthwise();
			loadEscalationMonthwise();
			loadPpm2s();
			
			loadPerformances1();
			loadPerformances();
			loadPerformances2();
			lowPerLastMonth();
			
		})();
		
		function lowPerLastMonth(){
			
			var m = $scope.currentDate.getMonth() - 1;
			if(m < 0){
				m = 11;
			}
			const monthCols = ["otd_jan", "otd_feb", "otd_mar", "otd_apr", "otd_may", "otd_june",
                "otd_july", "otd_aug", "otd_sep", "otd_oct", "otd_nov", "otd_dec"
              ];
			console.log("Back Month:"+m+" Month Cols:"+monthCols[m]+"  Back Year:"+$scope.back_year);
			$scope.perfor = {};
			$scope.perfor.criteria = "otd";
			$scope.perfor.year = $scope.back_year;
			lowService.lowPefo($scope.perfor).then(function(data) {

				if ($scope.perfor.criteria == "otd") {
					$scope.otd = true;
					$scope.fpy = false;
					$scope.ppm = false;
					vm.arr2 = data.list;
					console.log("Array 2:"+JSON.stringify(vm.arr2));
					
				} 
				
				
				else if ($scope.perfor.criteria == "fpy") {

					$scope.fpy = true;
					$scope.ppm = false;
					$scope.otd = false;
					vm.arr = data.list;
					
					for (var a = 0; a < vm.arr.length; a++) {
						for (var b = 0; b < vm.performanceObject.length; b++) {
							debugger;
							var fpyPerf = parseFloat(vm.performanceObject[b].performance_fpy);
							var suppCodePerf = vm.performanceObject[b].performance_suppliercode;
							var suppTypePerf = vm.performanceObject[b].performance_suppliertype;
							var suppYearPerf = vm.performanceObject[b].performance_year;
							
							if (vm.arr[a].fpy_suppliercode == suppCodePerf && vm.arr[a].fpy_suppliertype == suppTypePerf && vm.arr[a].fpy_year == suppYearPerf)
							{
								debugger;
								var janFpy	= parseFloat(vm.arr[a].fpy_jan);
								var febFpy	= parseFloat(vm.arr[a].fpy_feb);
								var marFpy	= parseFloat(vm.arr[a].fpy_mar);
								var aprFpy	= parseFloat(vm.arr[a].fpy_apr);
								var mayFpy	= parseFloat(vm.arr[a].fpy_may);
								var juneFpy	= parseFloat(vm.arr[a].fpy_june);
								var julyFpy	= parseFloat(vm.arr[a].fpy_july);
								var augFpy	= parseFloat(vm.arr[a].fpy_aug);
								var sepFpy	= parseFloat(vm.arr[a].fpy_sep);
								var octFpy	= parseFloat(vm.arr[a].fpy_oct);
								var novFpy	= parseFloat(vm.arr[a].fpy_nov);
								var decFpy	= parseFloat(vm.arr[a].fpy_dec);
								var ytdFpy	= parseFloat(vm.arr[a].fpy_ytd);
		
								if (janFpy	< fpyPerf)	{$scope.fpyjanClass[a]	 = 'redD';} 
								if (febFpy	< fpyPerf)	{$scope.fpyfebClass[a]  = 'redD';} 
								if (marFpy	< fpyPerf)	{$scope.fpymarClass[a]  = 'redD';} 
								if (aprFpy	< fpyPerf)	{$scope.fpyaprClass[a]  = 'redD';} 
								if (mayFpy	< fpyPerf)	{$scope.fpymayClass[a]  = 'redD';} 
								if (juneFpy	< fpyPerf)	{$scope.fpyjuneClass[a] = 'redD';}
								if (julyFpy	< fpyPerf)	{$scope.fpyjulyClass[a] = 'redD';} 
								if (augFpy	< fpyPerf)	{$scope.fpyaugClass[a]  = 'redD';}
								if (sepFpy	< fpyPerf)	{$scope.fpysepClass[a]  = 'redD';} 
								if (octFpy	< fpyPerf)	{$scope.fpyoctClass[a]  = 'redD';} 
								if (novFpy	< fpyPerf)	{$scope.fpynovClass[a]  = 'redD';} 
								if (decFpy	< fpyPerf)	{$scope.fpydecClass[a]  = 'redD';}  
								if (ytdFpy	< fpyPerf) {$scope.fpyytdClass[a] = 'redD';}
								break;
							}
						}
					}
				}
				
				else if ($scope.perfor.criteria == "ppm") {
					$scope.ppm = true;
					$scope.fpy = false;
					$scope.otd = false;
					vm.arr3 = data.list;
					


					for (var a = 0; a < vm.arr3.length; a++) {
						for (var b = 0; b < vm.performanceObject.length; b++) {
							debugger;
							var ppmPerf = parseFloat(vm.performanceObject[b].performance_ppm);
							var suppCodePerf = vm.performanceObject[b].performance_suppliercode;
							var suppTypePerf = vm.performanceObject[b].performance_suppliertype;
							var suppYearPerf = vm.performanceObject[b].performance_year;
							
							if (vm.arr3[a].ppm_suppliercode == suppCodePerf && vm.arr3[a].ppm_supplierType == suppTypePerf && vm.arr3[a].ppm_year == suppYearPerf) 
							{
								debugger;
								var janPpm	= parseFloat(vm.arr3[a].ppm_jan);
								var febPpm	= parseFloat(vm.arr3[a].ppm_feb);
								var marPpm	= parseFloat(vm.arr3[a].ppm_mar);
								var aprPpm	= parseFloat(vm.arr3[a].ppm_apr);
								var mayPpm	= parseFloat(vm.arr3[a].ppm_may);
								var junePpm	= parseFloat(vm.arr3[a].ppm_june);
								var julyPpm	= parseFloat(vm.arr3[a].ppm_july);
								var augPpm	= parseFloat(vm.arr3[a].ppm_aug);
								var sepPpm	= parseFloat(vm.arr3[a].ppm_sep);
								var octPpm	= parseFloat(vm.arr3[a].ppm_oct);
								var novPpm	= parseFloat(vm.arr3[a].ppm_nov);
								var decPpm	= parseFloat(vm.arr3[a].ppm_dec);
								var ytdPpm	= parseFloat(vm.arr3[a].ppm_ytd);
		
								if (janPpm	> ppmPerf)	{$scope.ppmjanClass[a]	 = 'redD';} 
								if (febPpm	> ppmPerf)	{$scope.ppmfebClass[a]  = 'redD';} 
								if (marPpm	> ppmPerf)	{$scope.ppmmarClass[a]  = 'redD';} 
								if (aprPpm	> ppmPerf)	{$scope.ppmaprClass[a]  = 'redD';} 
								if (mayPpm	> ppmPerf)	{$scope.ppmmayClass[a]  = 'redD';} 
								if (junePpm	> ppmPerf)	{$scope.ppmjuneClass[a] = 'redD';}
								if (julyPpm	> ppmPerf)	{$scope.ppmjulyClass[a] = 'redD';} 
								if (augPpm	> ppmPerf)	{$scope.ppmaugClass[a]  = 'redD';}
								if (sepPpm	> ppmPerf)	{$scope.ppmsepClass[a]  = 'redD';} 
								if (octPpm	> ppmPerf)	{$scope.ppmoctClass[a]  = 'redD';} 
								if (novPpm	> ppmPerf)	{$scope.ppmnovClass[a]  = 'redD';} 
								if (decPpm	> ppmPerf)	{$scope.ppmdecClass[a]  = 'redD';}  
								if (ytdPpm	> ppmPerf) {$scope.ppmytdClass[a] = 'redD';}
								break;
							}
						}
					}
				}
			});
		}
		
		function loadPerformances() {
			performanceService.getPerformances().then(function(data) {
				vm.performances = data;
				console.log(JSON.stringify(vm.performances));
			});
		}
		
		function loadPerformances2() {
			performanceService.getPerformances2().then(function(data) {
				vm.performances2 = data;
				//alert(vm.performances2);
				
				$scope.kw_high = vm.performances2[0];
				$scope.occ_high = vm.performances2[1];
				$scope.kw_low = vm.performances2[2];
				$scope.occ_low = vm.performances2[3];
				
				console.log(JSON.stringify(vm.performances2));
			});
		}
		

		function loadPerformances1() {
			
			performanceService.getPerformances1().then(function(data) {
				vm.performances1 = data;
			//	alert(vm.performances1);
				debugger;
				
				
				$scope.lowmonth =  vm.performances1[1];
				$scope.highmonth = vm.performances1[0];
				
				$scope.lowPer = vm.performances1[2];
				
				console.log($scope.lowmonth);
		
			});
		}

		function loadPpm2s() {
			ppmService.getPpm2s().then(function(data) {
				
				vm.ppm2s = data;
				
				
				$scope.ppm2 = vm.ppm2s;
				
				$scope.ppm2_kw = $scope.ppm2[0].ppm2_kw;
				$scope.ppm2_occ = $scope.ppm2[0].ppm2_occ;
				
				  var $ppc = $('.progress-pie-chart4'),
				    //percent = parseInt($ppc.data('percent')),
					percent = parseInt($scope.ppm2_kw),
				    deg = 360*percent/100;
				  if (percent > 50) {
				    $ppc.addClass('gt-50');
				  }
				  $('.ppc-progress-fill4').css('transform','rotate('+ deg +'deg)');
				  $('.ppc-percents4 span').html(percent+'');
				
				
				console.log(JSON.stringify(vm.ppm2s));
				
				
				
				
				 var $ppc = $('.progress-pie-chart5'),
				    //percent = parseInt($ppc.data('percent')),
					percent2 = parseInt($scope.ppm2_occ),
				    deg = 360*percent2/100;
				  if (percent2 > 50) {
				    $ppc.addClass('gt-50');
				  }
				  $('.ppc-progress-fill5').css('transform','rotate('+ deg +'deg)');
				  $('.ppc-percents5 span').html(percent2+'');
			});
		}


		function loadUsers() {
			userService.getUsers().then(function(data) {
				vm.users = data;

			});
		}
		
		function loadAvg() {
			otdService.getAvg().then(function(data) {
				vm.avgs = data;
				
				$scope.avg = vm.avgs[0];
				if(vm.avgs[0]==null){
					$scope.avg=0;
				}else{
					$scope.avg=$scope.avg;
				}
				 var $ppc = $('.progress-pie-chart'),
				    //percent = parseInt($ppc.data('percent')),
					percent = parseInt($scope.avg),
				    deg = 360*percent/100;
				  if (percent > 50) {
				    $ppc.addClass('gt-50');
				  }
				  $('.ppc-progress-fill').css('transform','rotate('+ deg +'deg)');
				  $('.ppc-percents span').html(percent+'%');

			});
		}

		
		function loadOtdMonthwise() {
			otdService.getOtdMonthwise().then(function(data) {
				vm.otds = data;
			
				$scope.otdkw = vm.otds[0];
				$scope.otdocc = vm.otds[1];
			});
		}


		function loadFpyMonthwise() {
			fpyService.getFpyMonthwise().then(function(data) {
				vm.fpys = data;
			
				$scope.fpykw = vm.fpys[0];
				$scope.fpyocc = vm.fpys[1];
			});
		}
		

		function loadPpmMonthwise() {
			ppmService.getPpmMonthwise().then(function(data) {
				vm.ppms = data;
			
				$scope.ppmkw = vm.ppms[0];
				$scope.ppmocc = vm.ppms[1];
			});
		}


		function loadEscalationMonthwise() {
			escalationService.getEscalationMonthwise().then(function(data) {
			//	vm.escalations = data;
			
				$scope.escalationkw = data[0];
				$scope.escalationocc = data[1];
			});
		}
		
		
		function loadAvg2() {
			fpyService.getAvg().then(function(data) {
				vm.avgs2 = data;
				
				
				if(vm.avgs2[0]==null){
					$scope.avg2=0;
				}else{
					$scope.avg2 = vm.avgs2[0];
				}
				 var $ppc = $('.progress-pie-chart2'),
				    //percent = parseInt($ppc.data('percent')),
					percent = parseInt($scope.avg2),
				    deg = 360*percent/100;
				  if (percent > 50) {
				    $ppc.addClass('gt-50');
				  }
				  $('.ppc-progress-fill2').css('transform','rotate('+ deg +'deg)');
				  $('.ppc-percents2 span').html(percent+'%');

			});
		}

		function loadAvg3() {
			otdService.getAvg3().then(function(data) {
				vm.avgs3 = data;
				
				
				if(vm.avgs3[0]==null){
					$scope.avg3=0;
				
				}else{
					$scope.avg3 = vm.avgs3[0];
					
				}
				 var $ppc = $('.progress-pie-chart3'),
				    //percent = parseInt($ppc.data('percent')),
					percent = parseInt($scope.avg3),
				    deg = 360*percent/100;
				  if (percent > 50) {
				    $ppc.addClass('gt-50');
				  }
				  $('.ppc-progress-fill3').css('transform','rotate('+ deg +'deg)');
				  $('.ppc-percents3 span').html(percent+'%');
			});
		}
		
		
		function loadAvg4() {
			fpyService.getAvg4().then(function(data) {
				vm.avgs4 = data;
				if(vm.avgs4[0]==null){
					$scope.avg4=0;
				
				}else{
					$scope.avg4 = vm.avgs4[0];
					
				}

				$scope.avg4 = vm.avgs4[0];

			});
		}
		
		function loadAvg5() {
			fpyService.getAvg4().then(function(data) {
				vm.avgs5 = 20;
				$scope.avg5 = 20;
				if(vm.avgs5[0]==null){
					$scope.avg5=0;
				
				}else{
					$scope.avg5 = vm.avgs5[0];
					
				}
			});
		}
		
		function loadEscalations() {
			escalationService.getEscalations().then(function(data) {
				vm.escalations = data;
				console.log("Escalation:"+JSON.stringify(vm.escalations));
			});
		}

		function loadSuppliers() {
			supplierService.getSuppliers().then(function(data) {
				vm.suppliers = data;
				console.log(JSON.stringify(vm.suppliers));
			});
		}

		function loadFpys() {

			
			fpyService.getFpys().then(function(data) {
				vm.fpys = data;
				console.log(JSON.stringify(vm.fpys));
			});
		}

		function loadOtds() {
			otdService.getOtds().then(function(data) {
				vm.otds = data;
				console.log(JSON.stringify(vm.otds));
			});
		}

		function loadPpms() {
			ppmService.getPpms().then(function(data) {
				vm.ppms = data;
				console.log(JSON.stringify(vm.ppms));
			});
		}

		// ******************************************************

	}
})();
