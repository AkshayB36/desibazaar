package com.desibazaar.rest.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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

	@ManyToMany
	@JoinTable(name = "User_Subscription", joinColumns = { @JoinColumn(name = "email") }, inverseJoinColumns = { @JoinColumn(name = "item_id") })
	private List<EItem> subscriptions;

	@Column(name = "rating")
	private Float rating;

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

	@Override
	public boolean equals(Object obj) {
		if (((EUser) obj).getEmail().equals(this.getEmail()))
			return true;
		return false;
	}
}
