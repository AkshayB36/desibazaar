var app = angular.module('reviewApp', [ 'ngRoute' ]);

app.config(function($routeProvider) {
	$routeProvider.when('/review', {
		controller : 'ReviewController',
		templateUrl : 'partials/reviews.html'
	}).when('/addReview', {
		controller : 'ReviewController',
		templateUrl : 'partials/addReview.html'
	}).otherwise({
		redirectTo : '/review'
	})
});