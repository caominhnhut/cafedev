cafedevApp.controller('IndexContrl', ['$scope', '$http', '$rootScope', '$location', 'AuthService',
function($scope, $http, $rootScope, $location, authService){
	
	$scope.isFullScreen = false;
	$scope.isError = false;
	
	$scope.loadPage = function(){
		var token = authService.getJwtToken();
		if(token != null){
			$rootScope.authenticated = true;
			$scope.username = authService.getUsername();
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
			authService.setJwtToken(res.data.access_token);
			authService.setUsername($scope.credentials.username);
			$scope.username = $scope.credentials.username;
			$('#modal-login').modal('hide');
		})
		.catch(function(response) {
			$rootScope.authenticated = false;
			$scope.isError = true;
			authService.removeJwtToken();
			authService.removeUsername();			
		});
	};
	
	$scope.logout = function(){
		$rootScope.authenticated = false;
		$scope.isError = false;
		authService.removeJwtToken();
		authService.removeUsername();
	}
	
	$scope.getAllUserInfo = function() {
		$http({
		      headers: authService.createAuthorizationTokenHeader(),
		      method: 'GET',
		      url: 'api/user/all'
		})
		.then(function(res) {
			console.log(res);
		}).catch(function(response) {
			console.log(response);
		});
	}
}]);
