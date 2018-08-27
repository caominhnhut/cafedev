cafedevApp.controller('IndexContrl', ['$scope', '$http', '$rootScope', '$location', 'AuthService','$window',
function($scope, $http, $rootScope, $location, authService, $window){
	
	$scope.isFullScreen = true;
	$scope.isError = false;
	
	$scope.loadPage = function(){
		var token = authService.getValueByKey(TOKEN_KEY);
		if(token != null){
			$rootScope.authenticated = true;
			$scope.username = authService.getValueByKey(USERNAME_KEY);
		}else{
			$rootScope.authenticated = false;
		}
	}
	$scope.loadPage();
	
	$scope.fullScreen = function(){
		if($scope.isFullScreen){
			$scope.isFullScreen = false;
		}else{
			$scope.isFullScreen = true;
		}
	}
	
	$scope.credentials = {};
	$scope.login = function() {
		$http({
			url: 'auth/login',
			method: 'POST',
			data: $scope.credentials,
			headers: authService.createAuthorizationTokenHeader()
		})
		.then(function(res) {
			$rootScope.authenticated = true;
			$scope.isError = false;
			authService.setKeyValue(TOKEN_KEY, res.data.access_token);
			$('#modal-login').modal('hide');
			authService.getUser()
			.then(function(user) {
				$scope.username = user.data.firstName+" "+user.data.lastName;
				authService.setKeyValue(USERNAME_KEY, $scope.username);
				authService.setKeyValue(USERID_KEY, user.data.id);
				$window.location.href = '#/';
			}).catch(function(error) {
				alert("Server is busy now. Please try again later.");
			});
		})
		.catch(function(response) {
			$rootScope.authenticated = false;
			$scope.isError = true;
			authService.removeByKey(TOKEN_KEY);
			authService.removeByKey(USERNAME_KEY);
			authService.removeByKey(USERID_KEY);
		});
	};
	
	$scope.logout = function(){
		$rootScope.authenticated = false;
		$scope.isError = false;
		authService.removeByKey(TOKEN_KEY);
		authService.removeByKey(USERNAME_KEY);
		authService.removeByKey(USERID_KEY);
		$window.location.reload();
	}
	
	$scope.setClass = function(path){
		$scope.className = path;
		$window.location.href = '#/'+path;
	}
}]);
