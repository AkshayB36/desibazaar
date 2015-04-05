package com.desibazaar.rest.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author Varda Laud
 *
 */
@Entity
@Table(name = "User")
public class EUser {
	@Id
	@Column(name = "email")
	private String email;

	@Column(name = "name")
	private String name;

	@Column(name = "password")
	private String password;

	@Column(name = "number")
	private String number;

	@Column(name = "address")
	private String address;

	@ManyToMany(mappedBy = "subscribers")
	private List<EItem> subscriptions;

	@Column(name = "rating")
	private Float rating;

	@OneToMany(mappedBy = "seller")
	private List<EItem> myItems;

	@OneToMany(mappedBy = "buyer")
	private List<EItem> myPurchases;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<EItem> getSubscriptions() {
		return subscriptions;
	}

	public void setSubscriptions(List<EItem> subscriptions) {
		this.subscriptions = subscriptions;
	}

	public Float getRating() {
		return rating;
	}

	public void setRating(Float rating) {
		this.rating = rating;
	}

	public List<EItem> getMyItems() {
		return myItems;
	}

	public void setMyItems(List<EItem> myItems) {
		this.myItems = myItems;
	}

	public List<EItem> getMyPurchases() {
		return myPurchases;
	}

	public void setMyPurchases(List<EItem> myPurchases) {
		this.myPurchases = myPurchases;
	}

	@Override
	public boolean equals(Object obj) {
		if (((EUser) obj).getEmail().equals(this.getEmail()))
			return true;
		return false;
	}
}
