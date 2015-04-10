app.controller('LoginController', function($scope, $location, accountService) {
	accountService.clearCredentials();

	$scope.login = function() {
		accountService.login($scope.username, $scope.password).then(function() {
			accountService.setCredentials($scope.username, $scope.password);
			$location.path('/');
		}, function() {
			$scope.error = "Username or Password do not match";
		});
	};
	// var source = new EventSource('/stats');
	// source.addEventListener('message', handleCallback, false);
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

app.controller('MyAuctionsController', function($scope, auctionService) {
	$scope.auctions = [];

	loadRemoteData();

	function loadRemoteData() {
		auctionService.getMyAuctions().then(function(auctions) {
			getMyAuctions(auctions);
		});
	}

	function getMyAuctions(auctions) {
		$scope.auctions = auctions;
	}

});

app.controller('EditItemsController', function($scope, $routeParams, $location,
		$window, auctionService) {
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
		auctionService.getSubscriptions().then(function(subscriptions) {
			applyRemoteData(subscriptions);
		});
	}
});

app.controller('ButtonController', function($scope, auctionService) {
	$scope.subscribed = false;
	$scope.subscribeButton = $scope.subscribed ? 'Unsubscribe' : 'Subscribe';
	$scope.toggleSubscribe = function() {
		if ($scope.subscribed == false) {
			auctionService.subscribe($scope.auction.itemId).then(function() {
				toggle();
			});
		} else {
			auctionService.unsubscribe($scope.auction.itemId).then(function() {
				toggle();
			});
		}
		function toggle() {
			$scope.subscribed = !$scope.subscribed;
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
			auctionService.unsubscribe($scope.auction.itemId).then(function() {
				toggle();
			});
		}
		function toggle() {
			$scope.subscribe = !$scope.subscribe;
			$scope.subscribeButton = $scope.subscribe ? 'Unsubscribe'
					: 'Subscribe';
		}
	};

});

app.controller('ViewReviewController',
		function($scope, reviewService) {
			$scope.reviews = [];
			function applyRemoteData(reviews) {
				$scope.sellerItems = reviews;
			}

			$scope.reviewed = false;
			$scope.reviewButton = $scope.reviewed ? 'Hide Reviews'
					: 'View Reviews';
			$scope.toggleReview = function() {

				if ($scope.reviewed == false) {
					reviewService.getReviews().then(function(reviews) {
						applyRemoteData(reviews);
					});
				} else {

				}
				$scope.reviewed = !$scope.reviewed;
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
		$scope.reviewed = !$scope.reviewed;
		$scope.bidButton = $scope.reviewed ? 'Hide Bidding History'
				: 'View Bidding History';
	};
});
