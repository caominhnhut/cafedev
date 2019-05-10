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

/*	$scope.pushExamnination = function(){
		var formData = new FormData();
		var file = $scope.myFile;
		formData.append("file", file);

		formData.append("userName", "Thanh Nhan");
		console.log(formData);

		var token = authFactory.getValueByKey(TOKEN_KEY);

		$http.post('/rest/examination/push-exercise', formData, {
            transformRequest: angular.identity,
			headers: {
				'Content-Type': undefined,
				'Authorization': "Bearer " + token
			}
        })
        .success(function(res){
			console.log(res);
        })
        .error(function(){
        });
	}*/
}]);
