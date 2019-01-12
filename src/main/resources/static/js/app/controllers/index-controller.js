cafedevApp.controller('IndexContrl', ['$scope', '$http', '$rootScope', '$location', 'AuthService','$window',
function($scope, $http, $rootScope, $location, authService, $window){
	
	$scope.isFullScreen = false;
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
	$scope.registerInfo = {};
	
	$scope.create= function(){
		if($scope.registerInfo.password==$scope.registerInfo.confirmPassword)
		{
			
		$http({
			url: '/rest/no-auth/create',
			method: 'POST',
			data: $scope.registerInfo
		});
		alert("Successful Register, please try the first login");
			$('#modal-register').modal('hide');
			$('#modal-login').modal('show');
			//$window.location.href = '#/';
		}
		else
		{
			$scope.isError = true;
		}
		
	}
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
	
	$scope.getExamination = function(){
		$http({
			url: 'rest/examination/find-by-user?idUser=1',
			method: 'GET'
		})
		.then(function(res) {
			$scope.examinations= res.data;
		})
		.catch(function(response) {
			alert("Server is error, please try again!")
		});
	}
	//$scope.getExamination();
	
	$scope.doOperation = function($event,idExam){
		if(id ==2|| id==1){
			e.preventDefault();
		}
	}

	$scope.getAssignment = function(){
		$http({
			url: 'rest/assignment/find-by-user-id',
			method: 'GET'
		})
		.then(function(res){
			$scope.assignments = res.data;
			console.log($scope.assignment);
		})
		.catch(function(response) {
			alert("Sever is error, please try again!")
		});
	}
	//$scope.getAssignment();

}]);
