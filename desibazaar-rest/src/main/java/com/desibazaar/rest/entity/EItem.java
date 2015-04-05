package com.desibazaar.rest.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.desibazaar.rest.enums.Status;

/**
 * @author Varda Laud
 *
 */
@Entity
@Table(name = "Item")
public class EItem {
	@Id
	@GeneratedValue
	@Column(name = "item_id")
	private Long itemId;

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	@Column(name = "base_price")
	private Float basePrice;

	@Column(name = "selling_price")
	private Float sellingPrice;

	@Column(name = "image")
	private String image;

	@Column(name = "starts_at")
	private Date startsAt;

	@Column(name = "ends_at")
	private Date endsAt;

	@ManyToOne
	@JoinColumn(name = "category")
	private ECategory category;

	@ManyToOne
	@JoinColumn(name = "seller")
	private EUser seller;

	@ManyToOne
	@JoinColumn(name = "buyer")
	private EUser buyer;

	@Column(name = "rating")
	private Integer rating;

	@Column(name = "review")
	private String review;

	@OneToMany(mappedBy = "item")
	private List<EBid> bids;

	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private Status status;

	@ManyToMany
	@JoinTable(name = "User_Subscription", joinColumns = { @JoinColumn(name = "item_id") }, inverseJoinColumns = { @JoinColumn(name = "email") })
	private List<EUser> subscribers;

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Float getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(Float basePrice) {
		this.basePrice = basePrice;
	}

	public Float getSellingPrice() {
		return sellingPrice;
	}

	public void setSellingPrice(Float sellingPrice) {
		this.sellingPrice = sellingPrice;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Date getStartsAt() {
		return startsAt;
	}

	public void setStartsAt(Date startsAt) {
		this.startsAt = startsAt;
	}

	public Date getEndsAt() {
		return endsAt;
	}

	public void setEndsAt(Date endsAt) {
		this.endsAt = endsAt;
	}

	public ECategory getCategory() {
		return category;
	}

	public void setCategory(ECategory category) {
		this.category = category;
	}

	public EUser getSeller() {
		return seller;
	}

	public void setSeller(EUser seller) {
		this.seller = seller;
	}

	public EUser getBuyer() {
		return buyer;
	}

	public void setBuyer(EUser buyer) {
		this.buyer = buyer;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public List<EBid> getBids() {
		return bids;
	}

	public void setBids(List<EBid> bids) {
		this.bids = bids;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public List<EUser> getSubscribers() {
		return subscribers;
	}

	public void setSubscribers(List<EUser> subscribers) {
		this.subscribers = subscribers;
	}

}
