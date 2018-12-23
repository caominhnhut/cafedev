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
}]);
