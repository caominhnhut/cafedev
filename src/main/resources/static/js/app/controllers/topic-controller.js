cafedevApp.controller('TopicCtrl', ['$scope','$http','AuthService', '$window','$rootScope', 
function($scope, $http, authService, $window, $rootScope){
	
	$scope.getArticles = function(){
		var request = {
				"metadata": {
					"pagination": {
						"offset": 0,
						"maxResult": 3
					},
					"sortType": "DESC",
					"sortValue": "createDate"
				},
				"data": 1
			}
		$http({
			url: 'rest/article/find-all-by-topic-id',
			method: 'POST',
			data: request,
			headers: authService.createAuthorizationTokenHeader()
		})
		.then(function(res) {
			$scope.articles = res.data;
		})
		.catch(function(response) {
			alert("Server is error, please try again!")
		});
	}
	$scope.getArticles();
}]);
