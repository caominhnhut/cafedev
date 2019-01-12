cafedevApp.controller('SubTopicCtrl', ['$scope','$http','AuthService','$routeParams', 
function($scope, $http, authService,$routeParams){
	$scope.getContent = function(){
		$http({
			url: 'rest/no-auth/article/content?id=' + $routeParams.id,
			method: 'GET',
			headers: authService.createAuthorizationTokenHeader()
		})
		.then(function(res) {
			$scope.article = res.data;
			console.log($scope.article);
		})
		.catch(function(response) {
			alert("Server is error, please try again!")
		}); 
	}
	$scope.getContent();
}]);
