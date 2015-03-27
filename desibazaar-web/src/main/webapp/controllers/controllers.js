app.controller('ReviewController', function($scope, reviewService) {
	$scope.reviews = reviewService.getReviews();
	$scope.addReview = function() {
		if ($scope.newReview.desc != '') {
			reviewService.createReview($scope.newReview.desc, new Date());
			$scope.newReview.desc = '';
		}
	};
});

app.controller('AuctionController', function($scope, auctionService) {
	$scope.auctions = [];

	loadRemoteData();

	function applyRemoteData(auctions) {
		$scope.auctions = auctions;
	}

	function loadRemoteData() {
		auctionService.getAuctions().then(function(auctions) {
			applyRemoteData(auctions);
		});
	}
});

app.controller('AuctionDetailsController', function($scope, $routeParams, auctionService) {
	$scope.auction = {};

	loadRemoteData();
	
	function applyRemoteData(auction) {
		$scope.auction = auction;
	}

	function loadRemoteData() {
		auctionService.getAuction($routeParams.itemId).then(function(auction) {
			applyRemoteData(auction);
		});
	}
});