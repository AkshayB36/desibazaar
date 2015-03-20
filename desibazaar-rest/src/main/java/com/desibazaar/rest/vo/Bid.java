package com.desibazaar.rest.vo;

import java.util.Date;

/**
 * @author Varda Laud
 *
 */
public class Bid {
	private User user;
	private Float bid;
	private Date time;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Float getBid() {
		return bid;
	}

	public void setBid(Float bid) {
		this.bid = bid;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

}
