cafedevApp.controller('ForumCtrl', ['$scope','$http','ApiProviderService', function($scope, $http, apiProviderService){
	
	$scope.getTopic = function(){
		var promise = apiProviderService.getApi(URL_FIND_ALL_TOPIC);
		promise.then(function(response){
			$scope.topics = response;
		}, function(error){
			alert('Can not show topics. Please try again latter');
		})
	}

	$scope.onLoad = function(){
		$scope.getTopic();
	}
	$scope.onLoad();
	
}]);
