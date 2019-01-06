cafedevApp.controller('TopicCtrl', ['$scope','$http','AuthService','$routeParams', 
function($scope, $http, authService,$routeParams){
	
	$scope.getArticles = function(){
		console.log($routeParams.id)
		var request = {
				"metadata": {
					"pagination": {
						"offset": 0,
						"maxResult": 3
					},
					"sortType": "DESC",
					"sortValue": "createDate"
				},
				"data": $routeParams.id
			}
		$http({
			url: 'rest/no-auth/article/find-all-by-topic-id',
			method: 'POST',
			data: request,
			headers: authService.createAuthorizationTokenHeader()
		})
		.then(function(res) {
			$scope.articleData = res.data;
			console.log($scope.articleData);
		})
		.catch(function(response) {
			alert("Server is error, please try again!")
		});
	}
	$scope.getArticles();
}]);


