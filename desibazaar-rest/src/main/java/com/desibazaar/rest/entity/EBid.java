package com.desibazaar.rest.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Varda Laud
 *
 */
@Entity
@Table(name = "Bid")
public class EBid {
	@Id
	@GeneratedValue
	@Column(name = "bid_id")
	private Long bidId;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private EUser user;

	@ManyToOne
	@JoinColumn(name = "item_id")
	private EItem item;

	@Column(name = "bid")
	private Float bid;

	@Column(name = "time")
	private Date time;

	public Long getBidId() {
		return bidId;
	}

	public void setBidId(Long bidId) {
		this.bidId = bidId;
	}

	public EUser getUser() {
		return user;
	}

	public void setUser(EUser user) {
		this.user = user;
	}

	public EItem getItem() {
		return item;
	}

	public void setItem(EItem item) {
		this.item = item;
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
