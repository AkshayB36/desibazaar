package com.desibazaar.rest.service;

import java.util.List;

import com.desibazaar.rest.vo.Item;
import com.desibazaar.rest.vo.User;

/**
 * @author Varda Laud
 *
 */
public interface IAccountService {
	public void createUser(User user);

	public void updateUser(User user);

	public User getUser(String email);

	public List<Item> getSubscripions(String email);

	public List<Item> getMyItems(String email);

	public List<Item> getMyPurchases(String email);

	public List<Item> getReviews(String email);

}
