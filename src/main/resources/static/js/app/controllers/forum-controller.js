cafedevApp.controller('ForumCtrl', ['$scope','$http','AuthService', function($scope, $http, authService){
	
	$scope.getTopic = function(){
		$http({
			url: 'rest/no-auth/topic',
			method: 'GET',
			headers: authService.createAuthorizationTokenHeader()
		})
		.then(function(res) {
			$scope.topics = res.data;
			console.log($scope.topics);
		})
		.catch(function(response) {
			alert("Server is error, please try again!")
		});
	}
	$scope.getTopic();
	
}]);
