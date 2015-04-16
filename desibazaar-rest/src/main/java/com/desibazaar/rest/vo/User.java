package com.desibazaar.rest.vo;

import java.util.List;

/**
 * @author Varda Laud
 *
 */
public class User {
	private String name;
	private String email;
	private String password;
	private String number;
	private String address;
	private List<Item> subscriptions;
	private Float rating;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public List<Item> getSubscriptions() {
		return subscriptions;
	}

	public void setSubscriptions(List<Item> subscriptions) {
		this.subscriptions = subscriptions;
	}

	public Float getRating() {
		return rating;
	}

	public void setRating(Float rating) {
		this.rating = rating;
	}

}
