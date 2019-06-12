cafedevApp.controller('HomeCtrl', ['$scope','AuthFactory', '$window','$rootScope', 'ApiProviderService', '$uibModal', 'CommonService',
function($scope, authFactory, $window, $rootScope, apiProviderService, $uibModal, commonService){

	//Clear everything when browser close.
//	$scope.onExit = function() {
//		$rootScope.authenticated = false;
//		authFactory.removeJwtToken();
//		authFactory.removeUsername();
//	};	
	//$window.onbeforeunload =  $scope.onExit;
	
	$rootScope.numOfNotify = 0;

	$scope.setClass = function(path){
		$scope.className = path;
		$window.location.href = '#/'+path;
	}
	
	$scope.fullScreen = function(){
		if($scope.isFullScreen){
			$scope.isFullScreen = false;
		}else{
			$scope.isFullScreen = true;
		}
	}

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
			commonService.getAssignment();
			commonService.getExamination();
			commonService.getNumOfNotify();
			commonService.countFeedComment();
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

	$scope.logout = function(){
		$rootScope.authenticated = false;
		$scope.isError = false;
		authFactory.removeByKey(TOKEN_KEY);
		authFactory.removeByKey(USERNAME_KEY);
		$window.location.reload();
	}

	$scope.openLoginDialog = function (){
		var modalInstance = $uibModal.open({
			templateUrl: "views/modal/login.html",
			controller: "UserCtrl"
		})
	}

	$scope.openNewFeedDialog = function (){
		var modalInstance = $uibModal.open({
			templateUrl: "views/modal/addfeed.html",
			controller: "AddFeedCtrl"
			// ,
			// resolve: {
			// 	params: function () {
			// 		return description = "John";
			// 	}
			// }
		})

		modalInstance.result.then(function (result){
			$scope.resultxxx = result;
			console.log("$scope.resultxxx", $scope.resultxxx);
		}, function () {
			console.log("Dialog dismissed");
		})
	}

	$scope.openRegisterDialog = function(){
		var modalInstance = $uibModal.open({
			templateUrl: "views/modal/register.html",
			controller: "UserCtrl"
		})
	}

}]);
