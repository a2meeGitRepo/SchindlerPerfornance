(function() {
	'use strict';

	angular
		.module('myApp.generic')
		.factory('genericFactory', genericFactory);

	genericFactory.$inject = ['genericHttpFactory', '$q', 'toastr'];
	
	/* @ngInject */
	function genericFactory(genericHttpFactory, $q, toastr) {
		var service = {			
			getAll : getAll,
			delet : delet,
			add : add,
			get :get,
			active : active,
		};
		return service;

		// ***************************************************************

		function getAll(msg,url) {
			var deferred = $q.defer();
			genericHttpFactory.getAll(url).then(function(response){
				//toastr.success(msg);
				deferred.resolve(response);
			}, function(err){
				alert(JSON.stringify(err.statusText));
			});
			return deferred.promise;
		}
		
		function delet(msg,url,entity){
			var deferred = $q.defer();
			genericHttpFactory.delet(url,entity).then(function(response){
				toastr.success(msg);
				deferred.resolve(response);
			}, function(err){
				toastr.error(err.status+' : '+err.statusText, 'Error !!');
			});
			return deferred.promise;
		}
		
		function active(msg,url,entity){
			var deferred = $q.defer();
			genericHttpFactory.active(url,entity).then(function(response){
				toastr.success(msg);
				deferred.resolve(response);
			}, function(err){
				toastr.error(err.status+' : '+err.statusText, 'Error !!');
			});
			return deferred.promise;
		}
		
		function add(msg,url,entity){
			var deferred = $q.defer();
			genericHttpFactory.add(url,entity).then(function(response){
				toastr.success(msg);
				deferred.resolve(response);
			}, function(err){
				toastr.error(err.status+' : '+err.statusText, 'Error !!');
			});
			return deferred.promise;
		}

		function get(msg,url,entity_id){
			var deferred = $q.defer();
			genericHttpFactory.get(msg,url,entity_id).then(function(response){
				toastr.success(msg);
				deferred.resolve(response);
			}, function(err){
				toastr.error(err.status+' : '+err.statusText, 'Error !!');
			});
			return deferred.promise;
		}
	}

})();