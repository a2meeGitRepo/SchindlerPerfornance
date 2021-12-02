(function() {
	'use strict';

	angular.module('myApp.password', [ 'datatables' ])
	.config(function($stateProvider) {
				$stateProvider
				.state('main.password', {
					url : "/password",
					views : {
						"sub" : {
							templateUrl : "templates/password/password.html",
							controller : "PasswordController as vm"
						}
					}
				})
			});

})();