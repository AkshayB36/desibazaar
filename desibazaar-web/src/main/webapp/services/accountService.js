app.service('accountService', function($http, $q) {
	return ({
		addUser : addUser,
	});

	function addUser(user) {
		return $http.post("http://localhost:8080/desibazaar-rest/users", user)
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