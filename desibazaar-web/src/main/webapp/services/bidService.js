app.service('bidService', function($http, $q) {
	return ({
		getBids : getBids,
		createBid : createBid
	});

	function getBids(auctionId) {
		return $http.get(
				"http://localhost:8080/desibazaar-rest/auctions/" + auctionId
						+ "/bids").then(handleSuccess, handleError)
	}

	function createBid(auctionId, bid) {
		return $http(
				{
					url : "http://localhost:8080/desibazaar-rest/auctions/"
							+ auctionId + "/bids",
					method : "POST",
					params : {
						bid : bid
					}
				}).then(handleSuccess, handleError)
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