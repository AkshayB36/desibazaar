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

	function loadRemoteData() {
		auctionService.getAuctions().then(function(auctions) {
			applyAuctions(auctions);
		});
	}
	
	function applyAuctions(auctions) {
		$scope.auctions = auctions;
	}
});

app.controller('AuctionDetailsController', function($scope, $routeParams,
		auctionService) {
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

app.controller('AddItemController', function($scope, categoryService,
		auctionService) {

	$scope.categories = [];

	loadRemoteData();

	$scope.addItem = function() {
		auctionService.addItem($scope.newItem);
	}

	function loadRemoteData() {
		categoryService.getCategories().then(function(categories) {
			applyCategories(categories);
		});
	}

	function applyCategories(categories) {
		$scope.categories = categories;
	}

});

app.controller('SubscribeController', function($scope, auctionService) {
	$scope.subscriptions = {};

	loadRemoteData();

	function applyRemoteData(subscriptions) {
		$scope.subscriptions = subscriptions;
	}

	function loadRemoteData() {
		auctionService.getSubscriptions($routeParams.email).then(
				function(subscriptions) {
					applyRemoteData(subscriptions);
				});
	}
});