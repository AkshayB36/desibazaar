var app = angular.module('desiApp', [ 'ngRoute' ]);

app.config(function($routeProvider) {
	$routeProvider.when('/review', {
		controller : 'ReviewController',
		templateUrl : 'partials/reviews.html'
	}).when('/addReview', {
		controller : 'ReviewController',
		templateUrl : 'partials/addReview.html'
	}).when('/listAuctions',{
		controller : 'AuctionController',
		templateUrl : 'partials/listAuctions.html'
	}).when('/auctionDetails/:itemId',
            {
        controller: 'AuctionDetailsController',
        templateUrl: 'partials/auctionDetails.html'
    }).otherwise({
		redirectTo : '/review'
	})
});