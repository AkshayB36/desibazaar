app.service('reviewService', function() {
	this.getReviews = function() {
		return reviews;
	}
	this.createReview = function(desc, time) {
		reviews.push({
			desc : desc,
			time : time
		});
	}
	var reviews = [ {
		desc : 'hello1',
		time : '3/6/2015'
	}, {
		desc : 'hello2',
		time : '2/6/2015'
	}, {
		desc : 'hello3',
		time : '3/5/2015'
	} ];
});