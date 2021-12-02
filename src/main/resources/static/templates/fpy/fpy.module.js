(function() {
	'use strict';

	angular.module('myApp.fpy', [ 'datatables' ]).config(
			function($stateProvider) {
				$stateProvider.state('main.fpy', {
					url : "/fpy",
					views : {
						"sub" : {
							templateUrl : "templates/fpy/fpy.html",
							controller : "FpyController as vm"
						}
					}
				})
				
				$stateProvider.state('main.fpy2', {
					url : "/fpy2",
					views : {
						"sub" : {
							templateUrl : "templates/fpy/fpy2.html",
							controller : "FpyController as vm"
						}
					}
				})
			});

})();