app.service('auctionService', function($http, $q) {
	return ({
		getAuctions : getAuctions,
		getAuction : getAuction,
		addItem : addItem,
		getSubscriptions : getSubscriptions,
		subscribe : subscribe,
		unsubscribe : unsubscribe,
		getMyAuctions : getMyAuctions,
		updateAuction : updateAuction
	});

	function addItem(item) {
		return $http.post("http://localhost:8080/desibazaar-rest/auctions",
				item).then(handleSuccess, handleError)
	}

	function getAuctions(email) {
		return $http({
			url : "http://localhost:8080/desibazaar-rest/auctions",
			method : "GET",
			params : {
				logged_in_user_email : email
			}
		}).then(handleSuccess, handleError)
	}

	function getAuction(auctionId) {
		return $http.get(
				"http://localhost:8080/desibazaar-rest/auctions/" + auctionId)
				.then(handleSuccess, handleError)
	}

	function updateAuction(auction) {
		return $http.put(
				"http://localhost:8080/desibazaar-rest/auctions/", auction)
				.then(handleSuccess, handleError)
	}
	function subscribe(email, auctionId) {
		return $http(
				{
					url : "http://localhost:8080/desibazaar-rest/auctions/"
							+ auctionId + "/subscribe",
					method : "GET",
					params : {
						logged_in_user_email : email
					}
				}).then(handleSuccess, handleError)
	}

	function getMyAuctions(email) {
		return $http.get(
				"http://localhost:8080/desibazaar-rest/users/" + email
						+ "/myItems").then(handleSuccess, handleError)
	}

	function getSubscriptions(email) {
		return $http.get(
				"http://localhost:8080/desibazaar-rest/users/" + email
						+ "/subscriptions").then(handleSuccess, handleError)
	}

	function unsubscribe(email, auctionId) {
		return $http(
				{
					url : "http://localhost:8080/desibazaar-rest/auctions/"
							+ auctionId + "/unsubscribe",
					method : "GET",
					params : {
						logged_in_user_email : email
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