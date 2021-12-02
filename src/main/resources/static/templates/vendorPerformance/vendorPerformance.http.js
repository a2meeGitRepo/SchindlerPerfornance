(function () {
	'use strict';

	angular
		.module('myApp.vendorPerformance')
		.factory('vpHttpService', vpHttpService);

	vpHttpService.$inject = ['$http', '$q', '_', 'localStorageService', 'ApiEndpoint'];

	/* @ngInject */
	function vpHttpService($http, $q, _, localStorageService, ApiEndpoint) {
		
		var saleUrl = ApiEndpoint.url+"vendorPerformance";   // User Url
		var ppmUrl = ApiEndpoint.url+"ppm";
		// Variables
		var users = {};

		var service = {
			addSale : addSale,
			getSales : getSales,
			getMaxPpm:getMaxPpm,
			downloadFile : downloadFile,
			vendorPefo : vendorPefo,
			sendMail : sendMail
		};

		return service;
	
		function sendMail(otd){
			
			return $http.post(saleUrl+'/sendmail', otd);
		}
		
		function vendorPefo(otd){
			return $http.post(saleUrl+'/venderData/?supplierCode='+otd.otd_suppliercode+'&supplierType='+otd.otd_suppliertype+'&supplierYear='+otd.year);
		}
		
	
		function getSales(){
			return $http.get(saleUrl+'/list1');
		}
		
		
		function downloadFile(){
			return $http.get(saleUrl+'/export');
		}
		
		function addSale(sale){
			console.log(JSON.stringify(sale))
			return $http.post(saleUrl+'/create', sale);
		}
		
		function getMaxPpm(supplierCode,supplierType,year){
			console.log(JSON.stringify(supplierCode+"/"+supplierType+"/"+year))
			return $http.get(ppmUrl+'/getmaxPpm/'+supplierCode+"/"+supplierType+"/"+year);
		}
		
	}
})();
