cafedevApp.controller('addFeedController', ['$scope', '$http', 'ApiProviderService', '$window',
function($scope, $http, apiProviderService, $window){
	// var params = $scope.$resolve.params;
	// $scope.name = params.name;
	// $scope.age = params.age;
	$scope.isError = false;

	$scope.cancel = function () {
		$scope.$dismiss();
	};

	$scope.save = function () {
		var formData = new FormData();
		
		if(typeof $scope.description !== 'undefined'){
			$scope.description.trim();
			if($scope.description.length == 0){
				$scope.isError = true;
				$scope.errorMessage = "Description should not be empty";
				return;	
			}
			formData.append("description", $scope.description);
		}else{
			$scope.isError = true;
			$scope.errorMessage = "Description should not be empty";
			return;
		}

		var file = $scope.myFile;
		if(typeof file !== 'undefined'){
			formData.append("file", file);
		}

		var promise = apiProviderService.postMutipartFileApi(URL_ADD_FEED, formData);
		promise.then(function (response) {
			$scope.feed = response;
			$scope.$close(response);
			$window.location.reload();
		}, function (errorPayload) {
			alert('Can not add new feed. Please try again later');
		})
	};

	$scope.loadFile = function(element) {
		$scope.currentFile = element.files[0];
		var reader = new FileReader();
		reader.onload = function(event) {
			$scope.imageSource = event.target.result
			$scope.$apply()
		}
		reader.readAsDataURL(element.files[0]);
	}
}]);
