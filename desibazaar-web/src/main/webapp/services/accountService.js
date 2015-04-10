app.service('accountService', function($http, $q, $rootScope, $cookieStore) {
	return ({
		addUser : addUser,
		setCredentials : setCredentials,
		clearCredentials : clearCredentials,
		login : login
	});

	function addUser(user) {
		return $http.post("http://localhost:8080/desibazaar-rest/users", user)
				.then(handleSuccess, handleError)
	}

	function login(username, password, callback) {
		var authdata = {
			"email" : username,
			"password" : password
		}
		return $http.post(
				"http://localhost:8080/desibazaar-rest/users/authenticate",
				authdata).then(handleSuccess, handleError)
	}

	function setCredentials(username, password) {
		$rootScope.globals = {
			currentUser : {
				username : username,
			}
		};
		$cookieStore.put('globals', $rootScope.globals);
	}

	function clearCredentials() {
		$rootScope.globals = {};
		$cookieStore.remove('globals');
		return $http.get("http://localhost:8080/desibazaar-rest/users/logout")
				.then(handleSuccess, handleError)
	}

	function handleError(response) {
		if (!angular.isObject(response.data) || !response.data.message) {
			return ($q.reject("An unknown error occurred."));
		}
		return ($q.reject(response.data.message));
	}

	function handleSuccess(response) {
		return (response.data);
	}

});