cafedevApp.service('ApiProviderService',[ '$http', '$q', 'AuthFactory', function($http, $q, authFactory) {

    this.getApi = function (urlEndpoint) {
        var deffered = $q.defer();
        $http({
			url: urlEndpoint,
			method: 'GET',
			headers: authFactory.createAuthorizationTokenHeader()
		})
		.success(function (response) {
            deffered.resolve(response);
        }).error(function (response) {
            deffered.reject(response);
        });
        return deffered.promise;
    }

    this.postApi = function(urlEndpoint, data){
        var deffered = $q.defer();
        $http({
			url: urlEndpoint,
			method: 'POST',
			data: data,
			headers: authFactory.createAuthorizationTokenHeader()
        })
        .success(function (response) {
            deffered.resolve(response);
        }).error(function (response) {
            deffered.reject(response);
        });
        return deffered.promise;
    }

    this.postMutipartFileApi = function(urlEndpoint, data){
        var token = authFactory.getValueByKey(TOKEN_KEY);
        var deffered = $q.defer();
        $http({
			url: urlEndpoint,
			method: 'POST',
			data: data,
			transformRequest: angular.identity,
			headers: {
				'Content-Type': undefined,
				'Authorization': "Bearer " + token
			}
        })
        .success(function (response) {
            deffered.resolve(response);
        }).error(function (response) {
            deffered.reject(response);
        });
        return deffered.promise;
    }
}])