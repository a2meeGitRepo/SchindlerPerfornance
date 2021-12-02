(function() {
	'use strict';

	angular.module('myApp.home', [

	]).config(function($stateProvider) {
		$stateProvider.state('main.home', {
			url : "/home",
			views : {
				"sub" : {
					templateUrl : "templates/home/home.html",
					controller : "HomeController as vm"
				}
			}
		})
		
		$stateProvider.state('main.home2', {
			url : "/home2",
			views : {
				"sub" : {
					templateUrl : "templates/home/home2.html",
					controller : "HomeController as vm"
				}
			}
		})
		
		$stateProvider.state('main.home3', {
			url : "/home3",
			views : {
				"sub" : {
					templateUrl : "templates/home/home3.html",
					controller : "HomeController as vm"
				}
			}
		})
		
		$stateProvider.state('main.home4', {
			url : "/home4",
			views : {
				"sub" : {
					templateUrl : "templates/home/home4.html",
					controller : "HomeController as vm"
				}
			}
		})
	});
})();