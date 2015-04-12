var app = angular.module('desiApp', [ 'ngRoute', 'ngCookies', 'flow' ]);
app
		.config(function($routeProvider) {
			$routeProvider.when('/auctions', {
				controller : 'AuctionController',
				templateUrl : 'partials/listAuctions.html'
			}).when('/auctionDetails/:itemId', {
				controller : 'AuctionDetailsController',
				templateUrl : 'partials/auctionDetails.html'
			}).when('/subscriptions', {
				controller : 'SubscribeController',
				templateUrl : 'partials/subscriptions.html'
			}).when('/subscriptionDetails/:itemId', {
				controller : 'AuctionDetailsController',
				templateUrl : 'partials/subscriptionDetails.html'
			}).when('/myItems', {
				controller : 'MyAuctionsController',
				templateUrl : 'partials/listMyItems.html'
			}).when('/myItemDetails/:itemId', {
				controller : 'AuctionDetailsController',
				templateUrl : 'partials/myAuctionDetails.html'
			}).when('/login', {
				controller : 'LoginController',
				templateUrl : 'partials/login.html'
			}).when('/register', {
				controller : 'AccountController',
				templateUrl : 'partials/addUser.html'
			}).when('/addItem', {
				controller : 'AddItemController',
				templateUrl : 'partials/addItem.html'
			}).when('/editItem/:itemId', {
				controller : 'EditItemsController',
				templateUrl : 'partials/editMyItems.html'
			}).otherwise({
				redirectTo : '/auctions'
			})
		})
		.directive('ngConfirmClick', [ function() {
			return {
				link : function(scope, element, attr) {
					var msg = attr.ngConfirmClick || "Are you sure?";
					var clickAction = attr.confirmedClick;
					element.bind('click', function(event) {
						if (window.confirm(msg)) {
							scope.$eval(clickAction)
						}
					});
				}
			};
		} ])
		.run(
				[
						'$rootScope',
						'$location',
						'$cookieStore',
						'$http',
						function($rootScope, $location, $cookieStore, $http) {
							$rootScope.globals = $cookieStore.get('globals')
									|| {};
							if ($rootScope.globals.currentUser) {
								$http.defaults.headers.common['Authorization'] = 'Basic '
										+ $rootScope.globals.currentUser.authdata;
							}

							$rootScope
									.$on(
											'$locationChangeStart',
											function(event, next, current) {
												if ($location.path() !== '/register'
														&& $location.path()
																.substring(0,
																		16) !== '/auctionDetails/'
														&& $location.path() !== '/auctions'
														&& $location.path() !== '/login'
														&& !$rootScope.globals.currentUser) {
													$location.path('/login');
												}
											});
						} ]);
