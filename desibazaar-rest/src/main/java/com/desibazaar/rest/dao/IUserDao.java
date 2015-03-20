package com.desibazaar.rest.dao;

import com.desibazaar.rest.entity.EUser;

/**
 * @author Varda Laud
 *
 */
public interface IUserDao {
	public void createUser(EUser entity);

	public void updateUser(EUser entity);

	public EUser getUser(String email);
}
