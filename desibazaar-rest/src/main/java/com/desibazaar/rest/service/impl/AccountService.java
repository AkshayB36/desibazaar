package com.desibazaar.rest.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desibazaar.rest.converter.DtoToEntityConverter;
import com.desibazaar.rest.converter.EntityToDtoConverter;
import com.desibazaar.rest.dao.IUserDao;
import com.desibazaar.rest.entity.EUser;
import com.desibazaar.rest.service.IAccountService;
import com.desibazaar.rest.vo.Item;
import com.desibazaar.rest.vo.User;

/**
 * @author Varda Laud
 *
 */
@Service
@Transactional
public class AccountService implements IAccountService {

	@Autowired
	private IUserDao userDao;

	@Override
	public void createUser(User user) {
		user.setRating(0F);
		EUser eUser = DtoToEntityConverter.convertUserToEUser(user, null);
		getDao().createUser(eUser);
	}

	@Override
	public void updateUser(User user) {
		EUser eUser = getDao().getUser(user.getEmail());
		DtoToEntityConverter.convertUserToEUser(user, eUser);
		getDao().updateUser(eUser);
	}

	@Override
	public User getUser(String email) {
		EUser eUser = getDao().getUser(email);
		return EntityToDtoConverter.convertEUserToUser(eUser);
	}

	@Override
	public List<Item> getSubscripions(String email) {
		EUser eUser = getDao().getUser(email);
		return EntityToDtoConverter
				.convertEItemToItem(eUser.getSubscriptions());
	}

	@Override
	public List<Item> getMyItems(String email) {
		EUser eUser = getDao().getUser(email);
		return EntityToDtoConverter.convertEItemToItem(eUser.getMyItems());
	}

	@Override
	public List<Item> getMyPurchases(String email) {
		EUser eUser = getDao().getUser(email);
		return EntityToDtoConverter.convertEItemToItem(eUser.getMyPurchases());
	}

	@Override
	public List<Item> getReviews(String email) {
		EUser eUser = getDao().getUser(email);
		return EntityToDtoConverter.convertEItemToItem(eUser.getMyItems());
	}

	private IUserDao getDao() {
		return userDao;
	}
}
