app.controller('ReviewController', function($scope, reviewService) {
	$scope.reviews = reviewService.getReviews();
	$scope.addReview = function() {
		if ($scope.newReview.desc != '') {
			reviewService.createReview($scope.newReview.desc, new Date());
			$scope.newReview.desc = '';
		}
	};
});

app.controller('AuctionController', function($scope, auctionService,categoryService) {
	$scope.auctions = [];

	loadRemoteData();

	function applyRemoteData(auctions) {
		$scope.auctions = auctions;
	}
	function applyCategories(categories) {

		$scope.categories = categories;
	}

	$scope.addItem = function() {
		auctionService.addItem($scope.newItem);
	}
	function loadRemoteData() {
		auctionService.getAuctions().then(function(auctions) {
			applyRemoteData(auctions);
		});
		categoryService.getCategory().then(function(categories) {
			applyCategories(categories);
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

app.controller('AccountController', function($scope, accountService) {
	$scope.newUser = {};

	$scope.addUser = function() {
		accountService.addUser($scope.newUser);
	}

});


