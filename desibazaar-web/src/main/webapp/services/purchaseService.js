app.service('purchaseService', function($http, $q) {
	return ({
		getPurchases : getPurchases,
		getPurchase : getPurchase 
	});



	function getPurchases() {
		return $http.get(
				"http://localhost:8080/desibazaar-rest/users/myPurchases").then(handleSuccess, handleError)
	}
	
	function getPurchase(auctionId) {
		return $http.get(
				"http://localhost:8080/desibazaar-rest/auctions/" + auctionId)
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
