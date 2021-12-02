(function() {
	'use strict';

	angular
		.module('myApp.sale')
		.service('fileUpload', ['$http','toastr','$rootScope',function ($http,toastr,$rootScope) {
		   this.uploadFileToUrl = function(file, uploadUrl){
			   var fd = new FormData();
			   fd.append('file', file);
			   $http.post(uploadUrl, fd,{
				  
				   transformRequest: angular.identity,
				  headers:{'Content-Type':undefined}
			   })
			   .success(function(){
					
//				   toastr.success('File Upload....', 'Succesful !!');
				   $rootScope.$broadcast('fileUploadExcel', {
		                data: 'something'
		            });
				   
			   })
			   .error(function(){
				   toastr.success('File Upload....', 'Succesful !!');
			   })
		   }
		}]);
})();