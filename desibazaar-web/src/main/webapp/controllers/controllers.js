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
		auctionService.getAuctions("ss8990@gmail.com").then(function(auctions) {
			applyAuctions(auctions);
		});
	}

	function applyAuctions(auctions) {
		$scope.auctions = auctions;
	}

});

app.controller('MyAuctionsController', function($scope, auctionService) {
	$scope.auctions = [];

	loadRemoteData();

	function loadRemoteData() {
		auctionService.getMyAuctions('xyz@gmail.com').then(function(auctions) {
			getMyAuctions(auctions);
		});
	}

	function getMyAuctions(auctions) {
		$scope.auctions = auctions;
	}

});

app.controller('EditItemsController', function($scope, $routeParams, $location,$window,
		auctionService) {
	$scope.auction = {};

	loadRemoteData();

	function loadRemoteData() {
		auctionService.getAuction($routeParams.itemId).then(function(auction) {
			$scope.auction = auction;
		});
	}
	$scope.editItem = function() {

		auctionService.updateAuction($scope.auction).then(function() {
			$location.path("/myAuctionDetails/" + $scope.auction.itemId);
			$window.alert("Your item has been modified!");
		});

	}
});

app.controller('AuctionDetailsController', function($scope, $routeParams,
		auctionService) {
	$scope.auction = {};

	loadRemoteData();

	function loadRemoteData() {
		auctionService.getAuction($routeParams.itemId).then(function(auction) {
			$scope.auction = auction;
		});
	}
	
});

app.controller('DeleteItemController', function($scope, $location,
		auctionService) {
	

	
	$scope.deleteItem = function() {
		auctionService.deleteAuction($scope.auction.itemId).then(function() {
			$location.path("/listMyItems/");
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
	$scope.subscriptions = [];

	loadRemoteData();

	function applyRemoteData(subscriptions) {
		$scope.subscriptions = subscriptions;
	}

	function loadRemoteData() {
		auctionService.getSubscriptions("ss8990@gmail.com").then(
				function(subscriptions) {
					applyRemoteData(subscriptions);
				});
	}
});

app.controller('ButtonController', function($scope, auctionService) {
	$scope.subscribed = false;
	$scope.subscribeButton = $scope.subscribed ? 'Unsubscribe' : 'Subscribe';
	$scope.toggleSubscribe = function() {
		if ($scope.subscribed == false) {
			auctionService.subscribe("ss8990@gmail.com", $scope.auction.itemId)
					.then(function() {
						toggle();
					});
		} else {
			auctionService.unsubscribe("ss8990@gmail.com",
					$scope.auction.itemId).then(function() {
				toggle();
			});
		}
		function toggle() {
			$scope.subscribed = !$scope.subscribed; // Handle subscription...
			$scope.subscribeButton = $scope.subscribed ? 'Unsubscribe'
					: 'Subscribe';
		}
	};
});

app.controller('ButtonUnsubscribeController', function($scope, auctionService) {
	$scope.subscribe = true;
	$scope.subscribeButton = $scope.subscribe ? 'Unsubscribe' : 'Subscribe';
	$scope.toggleUnsubscribe = function() {
		if ($scope.subscribe == true) {

			auctionService.unsubscribe("ss8990@gmail.com",
					$scope.auction.itemId).then(function() {
				toggle();
			});
		}
		function toggle() {
			$scope.subscribe = !$scope.subscribe; // Handle subscription...
			$scope.subscribeButton = $scope.subscribe ? 'Unsubscribe'
					: 'Subscribe';
		}
	};

});

app.controller('ViewReviewController',
		function($scope, viewReviewService) {
			$scope.reviews = [];
			function applyRemoteData(reviews) {
				$scope.sellerItems = reviews;
			}

			$scope.reviewed = false;
			$scope.reviewButton = $scope.reviewed ? 'Hide Reviews'
					: 'View Reviews';
			$scope.toggleReview = function() {

				if ($scope.reviewed == false) {
					viewReviewService.getReviews("cooldude_sarath@yahoo.co.in")
							.then(function(reviews) {
								applyRemoteData(reviews);
							});
				} else {

				}
				$scope.reviewed = !$scope.reviewed; // Handle subscription...
				$scope.reviewButton = $scope.reviewed ? 'Hide Reviews'
						: 'View Reviews';
			};
		});

app.controller('BidController', function($scope) {

	$scope.reviewed = false;
	$scope.bidButton = $scope.reviewed ? 'Hide Bidding History'
			: 'View Bidding History';
	$scope.toggleReview = function() {

		if ($scope.reviewed == false) {

		} else {

		}
		$scope.reviewed = !$scope.reviewed; // Handle subscription...
		$scope.bidButton = $scope.reviewed ? 'Hide Bidding History'
				: 'View Bidding History';
	};
});
