cafedevApp.controller('ExaminationCtrl', ['$scope','$http','$routeParams','AuthService', function($scope, $http,$routeParams,authService){
	
	$scope.getExaminationDetail = function(){
		var userId = authService.getValueByKey("userId");
		var examId = $routeParams.id;
		$http({
			url: 'rest/examination/find-by-user-exam?userId='+userId+' &examId='+examId,
			method: 'GET'
		})
		.then(function(res) {
			$scope.examinationDetail= res.data;
		})
		.catch(function(response) {
			alert("Server is error, please try again!")
		});
		
	}
	//$scope.getExaminationDetail();

	$scope.pushExamnination = function(){
		var formData = new FormData();
		var file = $scope.myFile;
		formData.append("file", file);

		formData.append("userName", "Thanh Nhan");
		console.log(formData);

		var token = authService.getValueByKey(TOKEN_KEY);

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
	}
}]);
