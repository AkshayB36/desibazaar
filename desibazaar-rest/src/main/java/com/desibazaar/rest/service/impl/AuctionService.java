package com.desibazaar.rest.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desibazaar.rest.converter.DtoToEntityConverter;
import com.desibazaar.rest.converter.EntityToDtoConverter;
import com.desibazaar.rest.dao.ICategoryDao;
import com.desibazaar.rest.dao.IItemDao;
import com.desibazaar.rest.entity.ECategory;
import com.desibazaar.rest.entity.EItem;
import com.desibazaar.rest.entity.EUser;
import com.desibazaar.rest.service.IAuctionService;
import com.desibazaar.rest.vo.Category;
import com.desibazaar.rest.vo.Item;

/**
 * @author Varda Laud
 *
 */
@Service
@Transactional
public class AuctionService implements IAuctionService {

	@Autowired
	private IItemDao itemDao;
	@Autowired
	private ICategoryDao categoryDao;

	@Override
	public void createAuction(Item item) {
		EItem eItem = DtoToEntityConverter.convertItemToEItem(item, null);
		getItemDao().createAuction(eItem);
	}

	@Override
	public void updateAuction(Item item) {
		EItem eItem = getItemDao().getAuction(item.getItemId());
		DtoToEntityConverter.convertItemToEItem(item, eItem);
		getItemDao().updateAuction(eItem);
	}

	@Override
	public void deleteAuction(Long itemId) {
		getItemDao().deleteAuction(itemId);
	}

	@Override
	public Item getAuction(Long itemId) {
		EItem eItem = getItemDao().getAuction(itemId);
		return EntityToDtoConverter.convertEItemToItem(eItem);
	}

	@Override
	public List<Item> getAuctions(String email) {
		// return items for which user is not the seller and status is to start
		// or ongoing
		List<EItem> eItems = getItemDao().getAuctions();
		return EntityToDtoConverter.convertEItemToItem(eItems);
	}

	@Override
	public List<Category> getCategories() {
		List<ECategory> eCategories = getCategoryDao().getCategories();
		return EntityToDtoConverter.convertECategoryToCategory(eCategories);
	}

	@Override
	public void subscribeAuction(Long itemId, String email) {
		EItem eItem = getItemDao().getAuction(itemId);
		EUser eUser = new EUser();
		eUser.setEmail(email);
		eItem.getSubscribers().add(eUser);
		getItemDao().updateAuction(eItem);
	}

	@Override
	public void unsubscribeAuction(Long itemId, String email) {
		EItem eItem = getItemDao().getAuction(itemId);
		EUser eUser = new EUser();
		eUser.setEmail(email);
		eItem.getSubscribers().remove(eUser);
		getItemDao().updateAuction(eItem);
	}

	private IItemDao getItemDao() {
		return itemDao;
	}

	private ICategoryDao getCategoryDao() {
		return categoryDao;
	}

}
