package com.desibazaar.rest.service;

import com.desibazaar.rest.vo.User;

/**
 * @author Varda Laud
 *
 */
public interface IAccountService {
	public void createUser(User user);

	public void updateUser(User user);

	public User getUser(String email);

}
