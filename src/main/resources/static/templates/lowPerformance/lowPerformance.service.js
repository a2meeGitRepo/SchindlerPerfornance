(function() {
	'use strict';

	angular.module('myApp.lowPerformance').factory('lowService', lowService);

	lowService.$inject = [ 'lowHttpService', '$q', 'toastr' ];

	/* @ngInject */
	function lowService(lowHttpService, $q, toastr) {
		var service = {
			lowPefo : lowPefo,
		};
		return service;

		// ***************************************************************

		function lowPefo(perfor) {
			var deferred = $q.defer();
			lowHttpService.lowPefo(perfor).then(function(response) {
				deferred.resolve(response.data);

			}, function(err) {
				alert(JSON.stringify(err.statusText));
			});
			return deferred.promise;
		}
	}
})();