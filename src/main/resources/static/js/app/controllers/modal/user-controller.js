cafedevApp.controller('UserCtrl', ['$scope', 'ApiProviderService', 'AuthFactory', '$rootScope', 'CommonService',
function($scope, apiProviderService, authFactory, $rootScope, commonService){
	
	$scope.credentials = {};
	$rootScope.numOfNotify = 0;

	$scope.login = function() {
		login($scope.credentials);
	}

	$scope.cancel = function () {
		$scope.$dismiss();
	};

	$scope.onKeyUpEvent = function(){
		$scope.isError = false;
		$scope.errorMessage = "";
	}

	function login(credentials){
		var promise = apiProviderService.postApi(URL_USER_LOGIN, credentials);
		promise.then(function (response) {
			console.log("response from login: ", response);
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
				console.log("response form whoami : ", user);
				if(user.firstName == null || user.lastName == null){
					$rootScope.username = "How are you";
				}else{
					$rootScope.username = user.firstName+" "+user.lastName;
				}
				authFactory.setKeyValue(USERNAME_KEY, $rootScope.username);
				commonService.getAssignment();
				commonService.getExamination();
				commonService.getNumOfNotify();
				commonService.countFeedComment();
				$scope.cancel();
			}
		}).catch(function(error){
			console.log("error", error);
		});
	}

	$scope.registerInfo = {};
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
}]);
