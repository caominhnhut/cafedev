cafedevApp.controller('TopicCtrl', ['$scope','$http','AuthService','$routeParams', 
function($scope, $http, authService,$routeParams){
	var numOfItem = 0;
	$scope.arrData = [];

	$scope.getArticles = function(offset){
		var request = {
				"metadata": {
					"pagination": {
						"offset": offset,
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
			$scope.topicName = res.data.topicName;
			if(res.data.articles !== undefined){
				for(var i = 0; i < res.data.articles.length; i++){
	 				$scope.arrData.push(res.data.articles[i]);
				}
			}
		})
		.catch(function(response) {
			alert("Server is error, please try again!")
		});
	}
	$scope.getArticles(0);

	$scope.getMoreArticles = function(offset){
		var request = {
				"metadata": {
					"pagination": {
						"offset": offset,
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
			if(res.data.articles !== undefined){
				for(var i = 0; i < res.data.articles.length; i++){
	 				$scope.arrData.push(res.data.articles[i]);
				}
			}
		})
		.catch(function(response) {
			alert("Server is error, please try again!")
		});
	}
	
	$scope.showMore = function(){
		$http({
			url: 'rest/no-auth/article/count-list-article?id=' + $routeParams.id,
			method: 'GET',
			headers: authService.createAuthorizationTokenHeader()
		})
		.then(function(res){
			var maxItem = res.data;
			if(numOfItem <= maxItem){
				numOfItem +=3;
				$scope.getMoreArticles(numOfItem);
				console.log("Dang lay du lieu");
			}else {
				console.log("Het du lieu");
			}	
		})
		.catch(function(response) {
			console.log("Can't not show your topic. Please try again a minute");
		});
	}
}]);
