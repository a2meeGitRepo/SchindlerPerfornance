(function() {
	'use strict';

	angular
		.module('myApp.sale')
		.directive('fileModel', ['$parse',function ($parse) {
		    return {
		        restrict :'A',
		        link: function (scope, element, attributes) {
		        	var model = $parse(attributes.fileModel);
		        	var modelSetter = model.assign;
		            element.bind("change", function (changeEvent) {
		                scope.$apply(function () {
		                    modelSetter(scope, element[0].files[0]);
		                });
		            });
		        }
		    }
		}]);
})();