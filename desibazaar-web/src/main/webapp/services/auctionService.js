app.service('auctionService', function auctionService($http) {
	this.getAuctions = function() {
		return $http.get("localhost:8080/desibazaar-rest/auctions").then(
				handleSuccess, handleError('Error retrieving auctions'));
	}
	function handleSuccess(data) {
		return data;
	}

	function handleError(error) {
		return function() {
			return {
				success : false,
				message : error
			};
		};
	}
});