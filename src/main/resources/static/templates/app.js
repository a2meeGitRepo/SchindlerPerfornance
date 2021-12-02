angular.module('myApp', [
	'ui.router',
	'LocalStorageModule',
	'ui.bootstrap',
	'toastr',
	'chart.js',
	
	'myApp.main',
	'myApp.home',
	'myApp.user',
	'myApp.login_form',
	'myApp.generic',
	'myApp.employee',
	'myApp.performance',
	'myApp.suppliertype',
	'myApp.supplier',
	'myApp.sale',
	'myApp.report',
	'myApp.report2',
	'myApp.password',
	'myApp.fpy',
	'myApp.otd',
	'myApp.ppm',
	'myApp.supplierstatus',
	'myApp.vendorPerformance',
	'myApp.highPerformance',
	'myApp.lowPerformance',
	'myApp.escalation',
	'myApp.sendsms'

])

.value('_', window._)

.constant('ApiEndpoint', {
  url: '/schindlerperformance/',// 162.251.83.105
  userKey : 'myeplanAdminUser'
})

.run(function(localStorageService, $location, $rootScope, $state){
//	debugger;
	$rootScope.$on('$stateChangeStart', function(event, toState, toParams, fromState, fromParams){
		//console.log(JSON.stringify(localStorageService.get('myeplanAdminUser')));
		if(localStorageService.get('myeplanAdminUser') == null){
			$location.url('/login_form');
		}
	});
	
})
.config(function($urlRouterProvider) {
	// if none of the above states are matched, use this as the fallback
	$urlRouterProvider.otherwise("/main/home");
});