(function() {
	'use strict';

	angular
	.module('myApp.user', [ 'datatables' ])
	.config(function($stateProvider) {
				$stateProvider
				.state('main.user', {
					url : "/user",
					views : {
						"sub" : {
							templateUrl : "templates/user/user.html",
							controller : "UserController as vm"
						}
					}
				})
			});

})();