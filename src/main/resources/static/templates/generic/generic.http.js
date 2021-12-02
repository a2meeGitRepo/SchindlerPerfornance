(function () {
	'use strict';

	angular
		.module('myApp.generic')
		.factory('genericHttpFactory', genericHttpFactory);

	genericHttpFactory.$inject = ['$http', '$q', '_'];

	/* @ngInject */
	function genericHttpFactory($http, $q, _) {

		var service = {
			add : add,
			get : get,
			getAll : getAll,
			delet : delet,
			active : active,
		};

		return service;
	
		
		function getAll(url){
			return $http.get(url);
		}
		
		function delet(url,entity){
			return $http.post(url,entity);
		}
		
		function active(url,entity){
			return $http.post(url,entity);
		}
		
		function add(url,entity){
			console.log(JSON.stringify(entity))
			return $http.post(url, entity);
		}
		
		function get(url,entity_id){
			console.log(JSON.stringify(entity_id))
			return $http.post(url+entity_id);
		}
		
	}
})();
