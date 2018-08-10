cafedevApp.controller('HomeCtrl', ['$scope','$http','AuthService', function($scope, $http, authService){

	$scope.getAllFeeds = function(){
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
			url: 'rest/feed/find-by-owner',
			method: 'POST',
			data: request,
			headers: authService.createAuthorizationTokenHeader()
		})
		.then(function(res) {
			$scope.feeds = res.data;
			console.log($scope.feeds);
		})
		.catch(function(response) {
			alert("Server is error, please try again!")
		});
	}
	$scope.getAllFeeds();
	
	$scope.isShowReplyField = 0;
	var previousId = -1;
	$scope.showReplyField = function(id){
		if(previousId != id){
			$scope.isShowReplyField = id;
			previousId = id;
		}else{
			$scope.isShowReplyField = -1;
			previousId = -1;
		}
	}

}]);
