(function() {
	'use strict';

	angular.module('myApp.lowPerformance').controller(
			'LowPerformanceController', LowPerformanceController);

	LowPerformanceController.$inject = [ '$state', '$uibModal', '$log',
			'$scope', 'toastr', '$filter', 'lowService', 'suppliertypeService', 'performanceService' ];

	/* @ngInject */
	function LowPerformanceController($state, $uibModal, $log, $scope, toastr,
			$filter, lowService, suppliertypeService, performanceService) {

		var vm = angular.extend(this, {
			arr : [],
			arr2 : [],
			arr3 : [],
			suppliertypes : [],
			performanceObject : [],
			lowPefo : lowPefo,
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


			loadSuppliertypes();
			loadPerformances();

			$scope.isDisabled = true;
			$scope.enable = function() {

				if ($scope.perfor.criteria != null	&& $scope.perfor.year != null) {
					$scope.isDisabled = false;
				}
			}
		})();

		// ******************************************************

		function loadPerformances() {
			performanceService.getPerformances().then(function(data) {
				vm.performanceObject = data;
			});
		}

		function loadSuppliertypes() {
			suppliertypeService.getSuppliertypes().then(function(data) {
				vm.suppliertypes = data;
				console.log(JSON.stringify(vm.suppliertypes));
			});
		}

		function lowPefo(perfor) {
			lowService.lowPefo(perfor).then(function(data) {

				if ($scope.perfor.criteria == "otd") {
					$scope.otd = true;
					$scope.fpy = false;
					$scope.ppm = false;
					vm.arr2 = removeDuplicateOTD(data.list);
					
					
					for (var a = 0; a < vm.arr2.length; a++) {
						for (var b = 0; b < vm.performanceObject.length; b++) {
							debugger;
							var otdPerf = parseFloat(vm.performanceObject[b].performance_otd);
							var suppCodePerf = vm.performanceObject[b].performance_suppliercode;
							var suppTypePerf = vm.performanceObject[b].performance_suppliertype;
							var suppYearPerf = vm.performanceObject[b].performance_year;
							
							if (vm.arr2[a].otd_suppliercode == suppCodePerf && vm.arr2[a].otd_suppliertype == suppTypePerf && vm.arr2[a].otd_year == suppYearPerf)
							{
								debugger;
								var janOtd	= parseFloat(vm.arr2[a].otd_jan);
								var febOtd	= parseFloat(vm.arr2[a].otd_feb);
								var marOtd	= parseFloat(vm.arr2[a].otd_mar);
								var aprOtd	= parseFloat(vm.arr2[a].otd_apr);
								var mayOtd	= parseFloat(vm.arr2[a].otd_may);
								var juneOtd	= parseFloat(vm.arr2[a].otd_june);
								var julyOtd	= parseFloat(vm.arr2[a].otd_july);
								var augOtd	= parseFloat(vm.arr2[a].otd_aug);
								var sepOtd	= parseFloat(vm.arr2[a].otd_sep);
								var octOtd	= parseFloat(vm.arr2[a].otd_oct);
								var novOtd	= parseFloat(vm.arr2[a].otd_nov);
								var decOtd	= parseFloat(vm.arr2[a].otd_dec);
								var ytdOtd	= parseFloat(vm.arr2[a].otd_ytd);
		
								if (janOtd	< otdPerf)	{$scope.otdjanClass[a]	 = 'redD';} 
								if (febOtd	< otdPerf)	{$scope.otdfebClass[a]  = 'redD';} 
								if (marOtd	< otdPerf)	{$scope.otdmarClass[a]  = 'redD';} 
								if (aprOtd	< otdPerf)	{$scope.otdaprClass[a]  = 'redD';} 
								if (mayOtd	< otdPerf)	{$scope.otdmayClass[a]  = 'redD';} 
								if (juneOtd	< otdPerf)	{$scope.otdjuneClass[a] = 'redD';}
								if (julyOtd	< otdPerf)	{$scope.otdjulyClass[a] = 'redD';} 
								if (augOtd	< otdPerf)	{$scope.otdaugClass[a]  = 'redD';}
								if (sepOtd	< otdPerf)	{$scope.otdsepClass[a]  = 'redD';} 
								if (octOtd	< otdPerf)	{$scope.otdoctClass[a]  = 'redD';} 
								if (novOtd	< otdPerf)	{$scope.otdnovClass[a]  = 'redD';} 
								if (decOtd	< otdPerf)	{$scope.otddecClass[a]  = 'redD';}  
								if (ytdOtd	< otdPerf) {$scope.otdytdClass[a] = 'redD';}
								break;
							}
						}
					}
				} 
				
				
				else if ($scope.perfor.criteria == "fpy") {

					$scope.fpy = true;
					$scope.ppm = false;
					$scope.otd = false;
					vm.arr =removeDuplicateFPY( data.list);
					
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
					vm.arr3 = removeDuplicatePPM(data.list);
					


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
		function removeDuplicateOTD (arr){ 
			var newArr = [];
			angular.forEach(arr, function(value, key) {
				var exists = false;
				angular.forEach(newArr, function(val2, key) {
					if(angular.equals(value.otd_id, val2.otd_id)){ exists = true }; 
				});
				if(exists == false && value.otd_id != "") { newArr.push(value); }
			});
		  return newArr;
		}
		function removeDuplicateFPY (arr){ 
			var newArr = [];
			angular.forEach(arr, function(value, key) {
				var exists = false;
				angular.forEach(newArr, function(val2, key) {
					if(angular.equals(value.fpy_id, val2.fpy_id)){ exists = true }; 
				});
				if(exists == false && value.fpy_id != "") { newArr.push(value); }
			});
		  return newArr;
		}
		function removeDuplicatePPM (arr){ 
			var newArr = [];
			angular.forEach(arr, function(value, key) {
				var exists = false;
				angular.forEach(newArr, function(val2, key) {
					if(angular.equals(value.ppm_id, val2.ppm_id)){ exists = true }; 
				});
				if(exists == false && value.ppm_id != "") { newArr.push(value); }
			});
		  return newArr;
		}
	}
})();
