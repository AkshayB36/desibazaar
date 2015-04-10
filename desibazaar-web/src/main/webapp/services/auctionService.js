app.service('auctionService', function($http, $q) {
	return ({
		getAuctions : getAuctions,
		getAuction : getAuction,
		addItem : addItem,
		getSubscriptions : getSubscriptions,
		subscribe : subscribe,
		unsubscribe : unsubscribe,
		getMyAuctions : getMyAuctions,
		updateAuction : updateAuction,
		deleteAuction : deleteAuction
	});

	function addItem(item) {
		return $http.post("http://localhost:8080/desibazaar-rest/auctions",
				item).then(handleSuccess, handleError)
	}

	function getAuctions() {
		return $http.get("http://localhost:8080/desibazaar-rest/auctions").then(handleSuccess, handleError)
	}

	function getAuction(auctionId) {
		return $http.get(
				"http://localhost:8080/desibazaar-rest/auctions/" + auctionId)
				.then(handleSuccess, handleError)
	}

	function updateAuction(auction) {
		return $http.put("http://localhost:8080/desibazaar-rest/auctions/",
				auction).then(handleSuccess, handleError)
	}

	function deleteAuction(auctionId) {
		return $http.delete(
				"http://localhost:8080/desibazaar-rest/auctions/" + auctionId)
				.then(handleSuccess, handleError)
	}

	function subscribe(auctionId) {
		return $http.get("http://localhost:8080/desibazaar-rest/auctions/"
							+ auctionId + "/subscribe").then(handleSuccess, handleError)
	}

	function getMyAuctions() {
		return $http.get(
				"http://localhost:8080/desibazaar-rest/users/myItems").then(handleSuccess, handleError)
	}

	function getSubscriptions() {
		return $http.get(
				"http://localhost:8080/desibazaar-rest/users/subscriptions").then(handleSuccess, handleError)
	}

	function unsubscribe(auctionId) {
		return $http.get("http://localhost:8080/desibazaar-rest/auctions/"
							+ auctionId + "/unsubscribe").then(handleSuccess, handleError)
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