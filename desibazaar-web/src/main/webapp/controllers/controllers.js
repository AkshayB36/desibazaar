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
			$location.path("/myItemDetails/" + $scope.auction.itemId);
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
			$location.path("/myItems/");
		});
	}
});

app.controller('AccountController', function($scope, $location, $window, accountService) {
	$scope.newUser = {};

	$scope.addUser = function() {
		accountService.addUser($scope.newUser);
		$location.path("/login/");
		$window.alert("User has been registered!");
	}
});

app.controller('ImageUpload', function($scope, $rootScope, categoryService,
		$location, $window, auctionService) {
	$scope.$on('flow::fileSuccess', function(file, message, chunk) {
		$scope.newItem.image = "img/" + chunk.uniqueIdentifier;
		auctionService.addItem($scope.newItem).then(function() {
			$location.path("/myItems/");
			$window.alert("Your item has been added!");
		});

	});
	$scope.$on('flow::fileAdded', function(event, $flow, flowFile) {
		$scope.uploaded = true;
	});
});


app.controller('AddItemController', function($scope, categoryService,
		auctionService) {

	$scope.newItem = {};
	$scope.newItem.endsAt = new Date();
	$scope.newItem.startTime = new Date();
	$scope.flow = {};
	$scope.categories = [];
	$scope.newItem.category={};
	$scope.uploaded = false;
	loadRemoteData();

	$scope.addItem = function() {
		var d1 = +document.getElementById("inputDuration").value;
		$scope.newItem.startsAt.setHours($scope.newItem.startTime.getHours());
		$scope.newItem.startsAt.setMinutes($scope.newItem.startTime
				.getMinutes());
		$scope.newItem.startsAt.setSeconds(00);
		$scope.newItem.endsAt.setTime($scope.newItem.startsAt.getTime() + (d1*60000));
		var e = document.getElementById("select");
		var categ = e.options[e.selectedIndex].text;
		$scope.newItem.category.name=categ;

		$scope.flow.images.upload();
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
	if($scope.auction.subscribed==false){
		$scope.subscribed = false;
	}else{
		$scope.subscribed=true;
	}
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
			$scope.subscribeButton = $scope.subscribed ? 'Unsubscribe' : 'Subscribe';
		}
	};
});

app.controller('ButtonUnsubscribeController', function($scope, $interval,
		auctionService) {
	$scope.subscribe = true;
	$scope.subscribeButton = $scope.subscribe ? 'Unsubscribe' : 'Subscribe';
	$scope.toggleUnsubscribe = function() {
		if ($scope.subscribe == true) {
			auctionService.unsubscribe($scope.auction.itemId).then(function() {
				toggle();
				location.reload();
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
				$scope.reviews = reviews;
			}

			$scope.reviewed = false;
			$scope.reviewButton = $scope.reviewed ? 'Hide Reviews'
					: 'View Reviews';
			$scope.toggleReview = function() {

				if ($scope.reviewed == false) {
					reviewService.getReviews($scope.auction.seller.email).then(
							function(reviews) {
								applyRemoteData(reviews);
							});
				} else {

				}
				$scope.reviewed = !$scope.reviewed;
				$scope.reviewButton = $scope.reviewed ? 'Hide Reviews'
						: 'View Reviews';
			};
		});

app
		.controller('DatepickerDemoCtrl',
				function($scope) {

					$scope.today = function() {
						$scope.dt = new Date();
					};
					$scope.today();

					$scope.clear = function() {
						$scope.dt = null;
					};

					// Disable weekend selection
					$scope.disabled = function(date, mode) {
						return (mode === 'day' && (date.getDay() === 0 || date
								.getDay() === 6));
					};

					$scope.toggleMin = function() {
						$scope.minDate = $scope.minDate ? null : new Date();
					};
					$scope.toggleMin();

					$scope.open = function($event) {
						$event.preventDefault();
						$event.stopPropagation();

						$scope.opened = true;
					};

					$scope.dateOptions = {
						formatYear : 'yy',
						startingDay : 1
					};

					$scope.formats = [ 'dd-MMMM-yyyy', 'yyyy/MM/dd',
							'dd.MM.yyyy', 'shortDate' ];
					$scope.format = $scope.formats[0];

				});

app.controller('TimepickerDemoCtrl', function($scope, $log) {
	$scope.mytime = new Date();

	$scope.hstep = 1;
	$scope.mstep = 15;

	$scope.options = {
		hstep : [ 1, 2, 3 ],
		mstep : [ 1, 5, 10, 15, 25, 30 ]
	};

	$scope.ismeridian = true;
	$scope.toggleMode = function() {
		$scope.ismeridian = !$scope.ismeridian;
	};

	$scope.update = function() {
		var d = new Date();
		d.setHours(14);
		d.setMinutes(0);
		$scope.mytime = d;
	};

	$scope.changed = function() {
		$log.log('Time changed to: ' + $scope.mytime);
	};

	$scope.clear = function() {
		$scope.mytime = null;
	};
});
app.controller('BidController', function($scope, $routeParams, $timeout,
		bidService) {

	function applyRemoteData(bids) {
		$scope.auction.bids = bids;
	}
	(function tick() {

		bidService.getBids($routeParams.itemId).then(function(bids) {
			applyRemoteData(bids);
			$timeout(tick, 1000);
		});
	})();

	$scope.createBid = function() {
		bidService.createBid($routeParams.itemId, $scope.bidValue).then(
				function() {
					$scope.bidValue = "";
				});
	}
});

app.controller('PurchaseController', function($scope, purchaseService) {
	$scope.purchases = [];

	loadRemoteData();

	function applyRemoteData(purchases) {
		$scope.purchases = purchases;
	}

	function loadRemoteData() {
		purchaseService.getPurchases().then(function(purchases) {
			applyRemoteData(purchases);
		});
	}
});

app.controller('PurchaseDetailsController', function($scope, $routeParams,
		purchaseService) {
	$scope.purchase = {};

	loadRemoteData();

	function loadRemoteData() {
		purchaseService.getPurchase($routeParams.itemId).then(
				function(purchase) {
					$scope.purchase = purchase;
				});
	}

});

app.controller('RatingController', function($scope, ratingService) {
	$scope.ratings = ratingService.getRatings();
	$scope.addRating = function() {
		if ($scope.newRating.desc != '') {
			ratingService.createRating($scope.newRating.desc, new Date());
		}
	};

});


