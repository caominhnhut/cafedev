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
	
	$scope.signup = function(){

		if($scope.registerInfo.password==$scope.registerInfo.confirmPassword)
		{
			
			$http({
				url: '/rest/no-auth/create',
				method: 'POST',
				data: $scope.registerInfo
			})
			.then(function(res){
				
				if(res.data.id!=null)
				{
					alert("Successful Register, please check your email and try your first login");
					$('#modal-register').modal('hide');
					$('#modal-login').modal('show');
					//$window.location.href = '#/';
				}
				else
				{
					$scope.userError = true;
				}
			})
			.catch(function(error) {
				alert("Server is busy now. Please try again later.");
			});
		}
		else
		{
			$scope.isError = true;
		}
	}

	$scope.login = function() {
		var promise = apiProviderService.postApi(URL_USER_LOGIN, $scope.credentials);
		promise.then(function (response) {
			console.log("response.data", response.data);
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
				$rootScope.username = user.firstName+" "+user.lastName;
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
	
	$scope.cancel = function () {
		$scope.$dismiss();
	};

	$scope.onKeyUpEvent = function(){
		$scope.isError = false;
	}
}]);
