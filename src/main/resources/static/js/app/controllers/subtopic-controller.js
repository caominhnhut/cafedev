cafedevApp.controller('SubTopicCtrl', ['$scope','$http','AuthFactory','$routeParams', 
function($scope, $http, authFactory,$routeParams){
	$scope.getContent = function(){
		$http({
			url: 'rest/no-auth/article/content?id=' + $routeParams.id,
			method: 'GET',
			headers: authFactory.createAuthorizationTokenHeader()
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
