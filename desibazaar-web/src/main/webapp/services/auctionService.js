app
		.service(
				'auctionService',
				function($http, $q) {
					return ({
						getAuctions : getAuctions,
						getAuction : getAuction,
						addItem : addItem,
						getSubscriptions : getSubscriptions
					});

					function addItem(item) {
						return $http
								.post(
										"http://localhost:8080/desibazaar-rest/auctions",
										item).then(handleSuccess, handleError)
					}

					function getAuctions() {
						return $http
								.get(
										"http://localhost:8080/desibazaar-rest/auctions")
								.then(handleSuccess, handleError)
					}

					function getAuction(auctionId) {
						return $http.get(
								"http://localhost:8080/desibazaar-rest/auctions/"
										+ auctionId).then(handleSuccess,
								handleError)
					}

					function getSubscriptions(email) {
						return $http(
								{
									"http://localhost:8080/desibazaar-rest/auctions" : user_subscription.itemId,
									method : "GET",
									params : {
										email : user_subscriptions.email
									}
								}).then(handleSuccess, handleError)
					}

					function handleError(response) {
						if (!angular.isObject(response.data)
								|| !response.data.message) {
							return ($q.reject("An unknown error occurred."));
						}
						return ($q.reject(response.data.message));
					}

					function handleSuccess(response) {
						return (response.data);
					}
				});