cafedevApp.controller('IndexContrl', ['$scope', function($scope){
	$scope.isFullScreen = false;
	
	$scope.fullScreen = function(){
		if($scope.isFullScreen){
			$scope.isFullScreen = false;
		}else{
			$scope.isFullScreen = true;
		}
	}
}]);
