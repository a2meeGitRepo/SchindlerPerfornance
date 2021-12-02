(function() {
	'use strict';

	angular.module('myApp.highPerformance').factory('highService', highService);

	highService.$inject = [ 'highHttpService', '$q', 'toastr' ];

	/* @ngInject */
	function highService(highHttpService, $q, toastr) {
		var service = {
			highPefo : highPefo,
		};
		return service;

		// ***************************************************************

		function highPefo(perfor) {
			var deferred = $q.defer();
			highHttpService.highPefo(perfor).then(function(response) {
				deferred.resolve(response.data);

			}, function(err) {
				alert(JSON.stringify(err.statusText));
			});
			return deferred.promise;
		}
	}
})();