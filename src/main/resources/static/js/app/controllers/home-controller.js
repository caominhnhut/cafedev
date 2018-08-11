cafedevApp.controller('HomeCtrl', ['$scope','$http','AuthService', '$window','$rootScope', 
function($scope, $http, authService, $window, $rootScope){

	//Clear everything when browser close.
	$scope.onExit = function() {
		$rootScope.authenticated = false;
		authService.removeJwtToken();
		authService.removeUsername();
	};	
	//$window.onbeforeunload =  $scope.onExit;
	
	$scope.getFeedsByOwner = function(){
		var request = {
				"metadata": {
					"pagination": {
						"offset": 0,
						"maxResult": 10
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
		})
		.catch(function(response) {
			alert("Server is error, please try again!")
		});
	}
	
	$scope.loadFeeds = function(){
		var token = authService.getJwtToken();
		if(token != null){
			$rootScope.authenticated = true;
			$scope.username = authService.getUsername();
			$scope.getFeedsByOwner();
			authService.getUser()
			.then(function(response) {
				console.log(response);
			})
			.catch(function(response) {
				console.log(response);
			});
		}else{
			$rootScope.authenticated = false;
			alert("401");
		}
	}
	$scope.loadFeeds();
	
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
