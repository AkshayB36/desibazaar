app.service('ratingService', function() {
	this.getRatings = function() {
		return ratings;
	}
	this.createReview = function(desc, time) {
		reviews.push({
			desc : desc,
			time : time
		});
	}
});