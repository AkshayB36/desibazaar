package com.desibazaar.rest.dao.impl;

import org.springframework.stereotype.Repository;

import com.desibazaar.rest.dao.AbstractDao;
import com.desibazaar.rest.dao.IUserDao;
import com.desibazaar.rest.entity.EUser;

/**
 * @author Varda Laud
 *
 */
@Repository
public class UserDao extends AbstractDao implements IUserDao {
	@Override
	public void createUser(EUser entity) {
		save(entity);
	}

	@Override
	public void updateUser(EUser entity) {
		update(entity);
	}

	@Override
	public EUser getUser(String email) {
		return get(email, EUser.class);
	}

}
