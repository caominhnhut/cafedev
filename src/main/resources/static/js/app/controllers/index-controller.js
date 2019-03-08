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

	$scope.getAssignment = function(){
		$http({
			url: 'rest/assignment/find-by-user-id',
			method: 'GET',
			headers: authService.createAuthorizationTokenHeader()
		})
		.then(function(res){
			$scope.assignments = res.data;
		})
		.catch(function(response) {
			console.log("Can't not show your assigments. Please try again a minute");
		});
	}

	$scope.getExamination = function(){
		$http({
			url: 'rest/examination/find-by-user',
			method: 'GET',
			headers: authService.createAuthorizationTokenHeader()
		})
		.then(function(res) {
			$scope.examinations= res.data;
		})
		.catch(function(response) {
			console.log("Can't not show your examination. Please try again a minute");
		});
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
			return authService.getUser();
		})
		.then(function(user) {
			$scope.username = user.data.firstName+" "+user.data.lastName;
			console.log("Xxxxx",$scope.username);
			authService.setKeyValue(USERNAME_KEY, $scope.username);
			authService.setKeyValue(USERID_KEY, user.data.id);
			$scope.getAssignment();
			$scope.getExamination();
			$window.location.href = '#/';
		})
		.catch(function(response) {
			$rootScope.authenticated = false;
			$scope.isError = true;
			authService.removeByKey(TOKEN_KEY);
			authService.removeByKey(USERNAME_KEY);
			authService.removeByKey(USERID_KEY);
		});
	}
	
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
	
	$scope.doOperation = function($event,idExam){
		if(id ==2|| id==1){
			e.preventDefault();
		}
	}
}]);
