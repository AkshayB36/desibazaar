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
		controller : 'AuctionController',
		templateUrl : 'partials/addItem.html'
	}).otherwise({
		redirectTo : '/review'
	})
});


