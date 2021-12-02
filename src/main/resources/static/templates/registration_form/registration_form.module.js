(function(){
	'use strict';

	angular.module('myApp.registration_form',[

		])
	.config(function($stateProvider){
		$stateProvider
		.state('registration_form', {
            url: "/registration_form",
            views: {
                "main": 
                	{
                		templateUrl: "templates/registration_form/registration_form.html",
                		controller:"RegistrationController as vm"
                	}
            }
        })
	});

})();