(function() {
	'use strict';

	angular.module('myApp.highPerformance').controller(
			'HighPerformanceController', HighPerformanceController);

	HighPerformanceController.$inject = [ '$state', '$uibModal', '$log',
			'$scope', 'toastr', '$filter', 'highService', 'suppliertypeService' ];

	/* @ngInject */
	function HighPerformanceController($state, $uibModal, $log, $scope, toastr,
			$filter, highService, suppliertypeService) {

		var vm = angular.extend(this, {

			arr : [],
			arr2 : [],
			arr3 : [],
			suppliertypes : [],
			highPefo : highPefo,
		});

		(function activate() {

			loadSuppliertypes();

			$scope.isDisabled = true;
			$scope.enable = function() {

				if ($scope.perfor.criteria != null
						&& $scope.perfor.year != null) {
					$scope.isDisabled = false;
				}
			}
		})();

		// ******************************************************

		function loadSuppliertypes() {
			suppliertypeService.getSuppliertypes().then(function(data) {
				vm.suppliertypes = data;
				console.log(JSON.stringify(vm.suppliertypes));
			});
		}

		function highPefo(perfor) {
			debugger;
			highService.highPefo(perfor).then(function(data) {
				console.log(" RESPONCE DATS"+JSON.stringify( data.list));
				
				if ($scope.perfor.criteria == "otd") {
					debugger;
					$scope.otd = true;
					$scope.fpy = false;
					$scope.ppm = false;
					vm.arr2 = removeDuplicateOTD (data.list);
					console.log(" WO filter DATS"+data.list.length);
					console.log(" filter DATS"+vm.arr2.length);
				} else if ($scope.perfor.criteria == "fpy") {

					$scope.fpy = true;
					$scope.ppm = false;
					$scope.otd = false;

					vm.arr = removeDuplicateFPY (data.list);
					console.log(" WO filter DATS"+data.list.length);
					console.log(" filter DATS"+vm.arr.length);
				} else if ($scope.perfor.criteria == "ppm") {
					$scope.ppm = true;
					$scope.fpy = false;
					$scope.otd = false;
					vm.arr3 = removeDuplicatePPM (data.list);
					console.log(" WO filter DATS"+data.list.length);
					console.log(" filter DATS"+vm.arr3.length);
				}

				/*
				 * vm.arr = data.list;
				 * 
				 * vm.arr2 = data.list;
				 * 
				 * vm.arr3 = data.list;
				 */

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
