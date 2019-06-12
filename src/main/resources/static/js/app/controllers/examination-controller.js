cafedevApp.controller('ExaminationCtrl', ['$scope','$http','$routeParams', 'ApiProviderService',
function($scope, $http,$routeParams, apiProviderService){
	
	$scope.findByIdAndUser = function(){
		var examId = $routeParams.id;
		var promise = apiProviderService.getApi(URL_FIND_EXAMINATION_BY_ID_AND_USER+examId);
		promise.then(function(response){
			$scope.examinationDetail = response;
		}, function (errorPayload){
			alert("Can not show examination detail");
		})
	}

	$scope.onLoad = function(){
		$scope.findByIdAndUser();
	}
	$scope.onLoad();

	$scope.pushExamnination = function(){
		var formData = new FormData();
		var file = $scope.myFile;
		formData.append("avatar", file);
		var strUser = {
				"id":1,
				"firstName":"Nguyen",
				"lastName":"Trang",
				"email":"trang1610@gmail.com",
				"phoneNumber":"0906572734"
			}
		var strJson = JSON.stringify(strUser);
		formData.append("user", strJson);
		
		console.log("strJson",strJson);
		console.log("formData",formData);

		var promise = apiProviderService.postMutipartFileApi(URL_PUSH_EXAM,formData);
		console.log("formDataaaaaaa",formData);
		promise.then(function(response){
			console.log(response);
		}, function (errorPayload){
			alert("Can not show examination detail");
		})
	}
}]);
