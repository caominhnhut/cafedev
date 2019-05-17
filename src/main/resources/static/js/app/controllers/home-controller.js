cafedevApp.controller('HomeCtrl', ['$scope','$http','AuthFactory', '$window','$rootScope', 'ApiProviderService',
function($scope, $http, authFactory, $window, $rootScope, apiProviderService){

	//Clear everything when browser close.
//	$scope.onExit = function() {
//		$rootScope.authenticated = false;
//		authFactory.removeJwtToken();
//		authFactory.removeUsername();
//	};	
	//$window.onbeforeunload =  $scope.onExit;
	
	$scope.findFeedsByOwner = function(){
		var promise = apiProviderService.getApi(URL_FIND_FEED_BY_OWNER+0);
		promise.then(function (response) {
			$scope.feeds = response;
		}, function (errorPayload) {
			alert('Can not show your feeds. Please try again latter');
		})
	}
	
	$scope.findLatestFeeds = function(){
		var promise = apiProviderService.getApi(URL_FIND_LASTEST_FEED+0);
		promise.then(function (response) {
			$scope.feeds = response;
		}, function (errorPayload) {
			alert('Can not show your feeds. Please try again latter');
		})
	}
	
	$scope.onLoad = function(){
		var token = authFactory.getValueByKey(TOKEN_KEY);
		if(token != null){
			$rootScope.authenticated = true;
			$scope.username = authFactory.getValueByKey(USERNAME_KEY);
			$scope.findFeedsByOwner();
		}else{
			$rootScope.authenticated = false;
			$scope.findLatestFeeds();
		}
	}
	$scope.onLoad();
	
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

	$scope.autoGrow = function(event) {
		event.currentTarget
		event.currentTarget.style.height = "5px";
		event.currentTarget.style.height = (event.currentTarget.scrollHeight)+"px";
	}

}]);
