cafedevApp.controller('IndexContrl', ['$scope', '$http', '$rootScope', '$location', 'ApiProviderService','$window',
'$uibModal', 'AuthFactory', function($scope, $http, $rootScope, $location, apiProviderService, $window, $uibModal, authFactory){
	
	$scope.isFullScreen = false;
	$scope.isError = false;
	$rootScope.numOfNotify = 0;
	$scope.credentials = {};
	$scope.registerInfo = {};
	
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

	$scope.getAssignment = function(){
		var promise = apiProviderService.getApi(URL_GET_ALL_ASSIGNMENT);
		promise.then(function (response) {
			$rootScope.assignments = response;
		}, function (errorPayload) {
			console.log("errorPayload",errorPayload);
		})
	}

	$scope.getExamination = function(){
		var promise = apiProviderService.getApi(URL_GET_ALL_EXAMINATION);
		promise.then(function (response) {
			$rootScope.examinations = response;
		}, function (errorPayload) {
			console.log("errorPayload",errorPayload);
		})
	}

	$scope.getNumOfNotify = function(){
		var promise = apiProviderService.getApi(URL_COUNT_NOTIFY);
		promise.then(function (response) {
			$rootScope.numOfNotify = response;
			if ($rootScope.numOfNotify < 10 && $rootScope.numOfNotify > 0){
				$rootScope.numOfNotify = '0' + $rootScope.numOfNotify;
			}
		}, function (errorPayload) {
			console.log("errorPayload",errorPayload);
		})
	}

	$scope.countFeedComment = function(){
		var promise = apiProviderService.getApi(URL_COUNT_COMMENT);
		promise.then(function (response) {
			$rootScope.feedcomment = response;
		}, function (errorPayload) {
			console.log("errorPayload",errorPayload);
		})
	}

	$scope.refreshToken = function(){
		var promise = apiProviderService.postApi(URL_REFRESH_TOKEN, null);
		promise.then(function (response) {
			 console.log('response',response);
		}, function (errorPayload) {
			console.log("errorPayload",errorPayload);
		})
	}

	$scope.onLoad = function(){
		var token = authFactory.getValueByKey(TOKEN_KEY);
		if(token != null){
			$rootScope.authenticated = true;
			$scope.username = authFactory.getValueByKey(USERNAME_KEY);
			$scope.getAssignment();
			$scope.getExamination();
			$scope.getNumOfNotify();
			$scope.countFeedComment();
		}else{
			$rootScope.authenticated = false;
		}

		var url = $location.url();
		if(url.length > 1){
			url = url.substring(1, url.length);
		}else{
			url = "home";
		}
		$scope.setClass(url);
	}
	$scope.onLoad();
	
	$scope.register = function(){
		$scope.isError = false;
		if($scope.registerInfo.password != $scope.registerInfo.confirmPassword){
			$scope.isError = true;
			$scope.errorMessage = "Password is not the same with the informed password";
		}else{
			var promise = apiProviderService.postApi(URL_USER_REGISTER, $scope.registerInfo);
			promise.then(function(response){
				if(response.data == true){
					let credentials = {
						"username": $scope.registerInfo.userName, 
						"password": $scope.registerInfo.password
					}
					$scope.$dismiss();
					login(credentials);
				}else{
					$scope.isError = true;
					$scope.errorMessage = response.errorMessage;
				}
			})
			.catch(function(error) {
				alert("Server is busy now. Please try again later.");
			});
		}
	}

	$scope.login = function() {
		login($scope.credentials);
	}

	function login(credentials){
		var promise = apiProviderService.postApi(URL_USER_LOGIN, credentials);
		promise.then(function (response) {
			if(response.data != null){
				$rootScope.authenticated = true;
				$scope.isError = false;
				authFactory.setKeyValue(TOKEN_KEY, response.data.access_token);	
				return apiProviderService.getApi(URL_WHO_AM_I);
			}else{
				$scope.errorMessage = response.errorMessage;
				$scope.isError = true;
			}
		})
		.then((user)=> {
			if(!$scope.isError){
				if(user.firstName == null || user.lastName){
					$rootScope.username = "How are you";
				}else{
					$rootScope.username = user.firstName+" "+user.lastName;
				}
				authFactory.setKeyValue(USERNAME_KEY, $rootScope.username);
				$scope.getAssignment();
				$scope.getExamination();
				$scope.getNumOfNotify();
				$scope.countFeedComment();
				$scope.$dismiss();
			}
		}).catch(function(error){
			console.log("error", error);
		});
	}
	
	$scope.logout = function(){
		$rootScope.authenticated = false;
		$scope.isError = false;
		authFactory.removeByKey(TOKEN_KEY);
		authFactory.removeByKey(USERNAME_KEY);
		$window.location.reload();
	}

	$scope.openNewFeedDialog = function (){
		var modalInstance = $uibModal.open({
			templateUrl: "views/modal/addfeed.html",
			controller: "addFeedController"
			// ,
			// resolve: {
			// 	params: function () {
			// 		return description = "John";
			// 	}
			// }
		})

		// modalInstance.result.then(function (result){
		// 	$scope.resultxxx = result;
		// }, function () {
		// 	console.log("Dialog dismissed");
		// })
	}

	$scope.openLoginDialog = function (){
		var modalInstance = $uibModal.open({
			templateUrl: "views/modal/login.html",
			controller: "IndexContrl"
		})
	}

	$scope.openRegisterDialog = function(){
		var modalInstance = $uibModal.open({
			templateUrl: "views/modal/register.html",
			controller: "IndexContrl"
		})
	}
	
	$scope.cancel = function () {
		$scope.$dismiss();
	};

	$scope.onKeyUpEvent = function(){
		$scope.isError = false;
		$scope.errorMessage = "";
	}
}]);
