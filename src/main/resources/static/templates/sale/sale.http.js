(function () {
	'use strict';

	angular
		.module('myApp.sale')
		.factory('saleHttpService', saleHttpService);

	saleHttpService.$inject = ['$http', '$q', '_', 'localStorageService', 'ApiEndpoint'];

	/* @ngInject */
	function saleHttpService($http, $q, _, localStorageService, ApiEndpoint) {
		
		var saleUrl = ApiEndpoint.url+"sale";   // User Url
		
		// Variables
		var users = {};

		var service = {
			addSale : addSale,
			addTransfer : addTransfer,
			getSales : getSales,
			getSales2 : getSales2,
			getSales1 : getSales1,
			getSaleByVerified:getSaleByVerified,
			getSaleByNonVerified:getSaleByNonVerified,
			getSale : getSale,
			searchSale : searchSale,
			searchSale1 : searchSale1,
			searchhSale : searchhSale,
			deleteSale : deleteSale,
			downloadFile : downloadFile,
			uploadSale : uploadSale,
			sendMail : sendMail,
			sendSms : sendSms,
			delete1Sale : delete1Sale
			
		};

		return service;
	
		function sendMail(sale){
			
			debugger;
			//console.log(JSON.stringify(user))
			return $http.post(saleUrl+'/sendmail', sale);
		}
		
		function sendSms(sale){
			//console.log(JSON.stringify(user))
			return $http.post(saleUrl+'/sendsms', sale);
		}
		
		function getSales(){
			return $http.get(saleUrl+'/list');
		}
		
		function getSales2(){
			return $http.get(saleUrl+'/list2');
		}
		
		function getSales1(){
			return $http.get(saleUrl+'/list1');
		}
		
		
		
		function getSaleByVerified(){
			return $http.get(saleUrl+'/exportbyverified');
		}
		
		
		
		function getSaleByNonVerified(){
			return $http.get(saleUrl+'/exportbynonverified');
		}
		
		
		function searchSale(sale){
			return $http.post(saleUrl+'/search',sale);
		}
		
		

		function searchSale1(sale){
			return $http.post(saleUrl+'/search1',sale);
		}
		
		
		function searchhSale(sale){
			return $http.post(saleUrl+'/getlist',sale);
		}
		
		function getSale(location_id){
			return $http.get(saleUrl+'/getlist/'+location_id);
		}
		
		function deleteSale(sale){
			return $http.get(saleUrl+'/delete/'+sale.sale_id);
		}
				
		
		function delete1Sale(sale){
			return $http.get(saleUrl+'/permdelete/'+sale.sale_id);
		}
		
		function downloadFile(){
			return $http.get(saleUrl+'/export');
		}
		
		function addSale(sale){
			console.log(JSON.stringify(sale))
			return $http.post(saleUrl+'/create', sale);
		}
		
		function addTransfer(transfer){
			console.log(JSON.stringify(transfer))
			return $http.post(saleUrl+'/transfer', sale);
		}
		
		
		function uploadSale(file){
			return $http.post(saleUrl+'/uploadFile', file);
		}
	}
})();
