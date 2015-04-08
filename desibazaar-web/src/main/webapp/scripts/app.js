var app = angular.module('desiApp', [ 'ngRoute' ]);

app.config(function($routeProvider) {
	$routeProvider.when('/review', {
		controller : 'ReviewController',
		templateUrl : 'partials/reviews.html'
	}).when('/addReview', {
		controller : 'ReviewController',
		templateUrl : 'partials/addReview.html'
	}).when('/listAuctions', {
		controller : 'AuctionController',
		templateUrl : 'partials/listAuctions.html'
	}).when('/subscriptions', {
		controller : 'SubscribeController',
		templateUrl : 'partials/subscriptions.html'
	}).when('/subscriptionDetails/:itemId', {
		controller : 'AuctionDetailsController',
		templateUrl : 'partials/subscriptionDetails.html'
	}).when('/auctionDetails/:itemId', {
		controller : 'AuctionDetailsController',
		templateUrl : 'partials/auctionDetails.html'
	}).when('/login', {
		controller : 'AccountController',
		templateUrl : 'partials/login.html'
	}).when('/addUser', {
		controller : 'AccountController',
		templateUrl : 'partials/addUser.html'
	}).when('/addItem', {
		controller : 'AddItemController',
		templateUrl : 'partials/addItem.html'
	}).when('/listMyItems', {
		controller : 'MyAuctionsController',
		templateUrl : 'partials/listMyItems.html'
	}).when('/myAuctionDetails/:itemId', {
		controller : 'AuctionDetailsController',
		templateUrl : 'partials/myAuctionDetails.html'
	}).when('/editMyItems/:itemId', {
		controller : 'EditItemsController',
		templateUrl : 'partials/editMyItems.html'
	}).otherwise({
		redirectTo : '/review'
	})
});
