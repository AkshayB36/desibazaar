app.service('categoryService', function($http, $q) {
	return ({
		getCategory : getCategory,
	});
	
	function getCategory() {
		return $http.get("http://localhost:8080/desibazaar-rest/categories")
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