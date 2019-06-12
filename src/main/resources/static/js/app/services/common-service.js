cafedevApp.service('CommonService',[ '$http', '$q', 'ApiProviderService', '$rootScope',
function($http, $q, apiProviderService, $rootScope) {

    this.getAssignment = function(){
		var promise = apiProviderService.getApi(URL_GET_ALL_ASSIGNMENT);
		promise.then(function (response) {
			$rootScope.assignments = response;
			console.log("$rootScope.assignments", $rootScope.assignments);
		}, function (errorPayload) {
			console.log("errorPayload",errorPayload);
		})
	}
		
	this.getExamination = function(){
		var promise = apiProviderService.getApi(URL_GET_ALL_EXAMINATION);
		promise.then(function (response) {
			$rootScope.examinations = response;
		}, function (errorPayload) {
			console.log("errorPayload",errorPayload);
		})
	}
	
	this.getNumOfNotify = function(){
		var promise = apiProviderService.getApi(URL_COUNT_NOTIFY);
		promise.then(function (response) {
			$rootScope.numOfNotify = response;
			if ($rootScope.numOfNotify < 10 && $rootScope.numOfNotify > 0){
				$rootScope.numOfNotify = '0' + $rootScope.numOfNotify;
			}
		}, function (errorPayload) {
			console.log("errorPayload",errorPayload);
		})
	}

	this.countFeedComment = function(){
		var promise = apiProviderService.getApi(URL_COUNT_COMMENT);
		promise.then(function (response) {
			$rootScope.feedcomment = response;
		}, function (errorPayload) {
			console.log("errorPayload",errorPayload);
		})
	}
}])