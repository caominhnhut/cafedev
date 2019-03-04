cafedevApp.factory('AuthService', function($http) {
	
	
	//------USER NAME------
	var getValueByKey = function(key){
		return localStorage.getItem(key);
	}
	
	var setKeyValue = function(key, value) {
		localStorage.setItem(key, value);
	};
	
	var removeByKey = function(key) {
		localStorage.removeItem(key);
	};

	var createAuthorizationTokenHeader = function() {
		var token = getValueByKey(TOKEN_KEY);
		if (token) {
			return {
				"Authorization" : "Bearer " + token,
				'Content-Type' : 'application/json'
			};
		} else {
			return {
				'Content-Type' : 'application/json'
			};
		}
	};

	var getUser = function() {
		return $http({
			headers : createAuthorizationTokenHeader(),
			method : 'GET',
			url : '/rest/whoami'
		});
	};

	return {
		getUser : getUser,
		getValueByKey : getValueByKey,
		setKeyValue : setKeyValue,
		removeByKey : removeByKey,
		createAuthorizationTokenHeader : createAuthorizationTokenHeader
	};
});