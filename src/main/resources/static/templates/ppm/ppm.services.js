(function() {
	'use strict';
	angular
		.module('myApp.ppm')
		.service('fileUpload', ['$http','toastr',function ($http,toastr) {
		   this.uploadFileToUrl = function(file, uploadUrl){
			   debugger;
			   var fd = new FormData();
			   fd.append('file', file);
			   $http.post(uploadUrl, fd,{
				  
				   transformRequest: angular.identity,
				  headers:{'Content-Type':undefined}
			   })
			   .success(function(){
					
				   toastr.success('File Upload....', 'Succesful !!');
				   $window.location.reload();
  })
			   .error(function(){
				   toastr.error('sorry...!!!');
			   })
		   }
		}]);
})();