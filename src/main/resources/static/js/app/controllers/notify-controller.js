cafedevApp.controller('NotifyCtrl', [ '$scope', '$http', 'AuthService','$routeParam',
function($scope, $http, authService,$routeParam) {

		$scope.isFullScreen = false;
		$scope.isError = false;

		$scope.setClass = function(path) {
		$scope.className = path;
		$window.location.href = '#/' + path;
		}
			
		$scope.getNotify = function(){
			var request = {
				"metadata": {
					"pagination": {
						"offset": offset,
						"maxResult": 3
					},
					"sortType": "DESC",
					"sortValue": "createDate"
				},
				
			}
			$http({
				url: 'rest/notify/find-by-user-id',
				method: 'GET',
				data: request,
				headers: authService.createAuthorizationTokenHeader()
			})
			.then(function(res){
				$scope.notifies = res.data;
				console.log($scope.notifies);
			})
			.catch(function(response) {
				console.log("Can't not show your notifies. Please try again a minute");
			});
		}
		$scope.getNotify();

		$scope.showMore = function(){
		$http({
			url: 'rest/no-auth/article/count-list-article?id=' + $routeParams.id,
			method: 'GET',
			headers: authService.createAuthorizationTokenHeader()
		})
		.then(function(res){
			var maxItem = res.data;
			if(numOfItem <= maxItem){
				numOfItem +=3;
				$scope.getMoreArticles(numOfItem);
				console.log("Dang lay du lieu");
			}else {
				console.log("Het du lieu");
			}	
		})
		.catch(function(response) {
			console.log("Can't not show your topic. Please try again a minute");
		});
	}
}]);
