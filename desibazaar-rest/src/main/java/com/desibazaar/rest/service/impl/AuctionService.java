package com.desibazaar.rest.service.impl;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desibazaar.rest.converter.DtoToEntityConverter;
import com.desibazaar.rest.converter.EntityToDtoConverter;
import com.desibazaar.rest.dao.ICategoryDao;
import com.desibazaar.rest.dao.IItemDao;
import com.desibazaar.rest.dao.IUserDao;
import com.desibazaar.rest.entity.EBid;
import com.desibazaar.rest.entity.ECategory;
import com.desibazaar.rest.entity.EItem;
import com.desibazaar.rest.entity.EUser;
import com.desibazaar.rest.enums.Status;
import com.desibazaar.rest.service.IAuctionService;
import com.desibazaar.rest.service.IBiddingService;
import com.desibazaar.rest.service.ISchedulingService;
import com.desibazaar.rest.vo.Bid;
import com.desibazaar.rest.vo.Category;
import com.desibazaar.rest.vo.Item;

/**
 * @author Varda Laud
 *
 */
@Service
@Transactional
public class AuctionService implements IAuctionService {
	private final static Logger LOGGER = Logger.getLogger(AuctionService.class);

	@Autowired
	private ISchedulingService schedulingService;
	@Autowired
	private IBiddingService biddingService;
	@Autowired
	private IItemDao itemDao;
	@Autowired
	private ICategoryDao categoryDao;
	@Autowired
	private IUserDao userDao;

	@Override
	public void createAuction(Item item) {
		EItem eItem = DtoToEntityConverter.convertItemToEItem(item, null);
		eItem.setStatus(Status.ToStart);
		eItem.setRating(-1);
		eItem.setSellingPrice(0F);
		getItemDao().createAuction(eItem);
		LOGGER.debug("Item created : " + item.getName());
		getSchedulingService().addAuctionJob(eItem.getItemId(),
				eItem.getStartsAt(), eItem.getEndsAt());
		LOGGER.debug("Auction scheduled : " + item.getName() + " Start Time : "
				+ eItem.getStartsAt() + " End Time : " + eItem.getEndsAt());
	}

	@Override
	public void updateAuction(Item item) {
		boolean updateRequired = false;
		EItem eItem = getItemDao().getAuction(item.getItemId());
		if (eItem.getRating().intValue() != item.getRating().intValue()) {
			updateRequired = true;
		}
		DtoToEntityConverter.convertItemToEItem(item, eItem);
		getItemDao().updateAuction(eItem);
		if (updateRequired) {
			int sum = 0;
			int n = 0;
			EUser eUser = getUserDao().getUser(item.getSeller().getEmail());
			for (EItem myItem : eUser.getMyItems()) {
				if (myItem.getRating().intValue() != -1) {
					sum = sum + myItem.getRating();
					n++;
				}
			}
			eUser.setRating((float) sum / n);
			getUserDao().updateUser(eUser);
		}
	}

	@Override
	public void deleteAuction(Long itemId) {
		getItemDao().deleteAuction(itemId);
		LOGGER.debug("Item deleted : " + itemId);
	}

	@Override
	public Item getAuction(Long itemId) {
		EItem eItem = getItemDao().getAuction(itemId);
		return EntityToDtoConverter.convertEItemToItem(eItem);
	}

	@Override
	public List<Item> getAuctions(String email) {
		List<EItem> eItems = getItemDao().getAuctions(email);
		if (email != null) {
			EUser eUser = getUserDao().getUser(email);
			for (EItem eItem : eItems) {
				if (eUser.getSubscriptions().contains(eItem)) {
					eItem.setSubscribed(true);
				} else {
					eItem.setSubscribed(false);
				}
			}
		}
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
		LOGGER.debug("Auction subscribed Item Id : " + itemId + " User Name : "
				+ email);
	}

	@Override
	public void unsubscribeAuction(Long itemId, String email) {
		EItem eItem = getItemDao().getAuction(itemId);
		EUser eUser = new EUser();
		eUser.setEmail(email);
		eItem.getSubscribers().remove(eUser);
		getItemDao().updateAuction(eItem);
		LOGGER.debug("Auction unsubscribed Item Id : " + itemId + " User Name : "
				+ email);
	}

	@Override
	public synchronized void createBid(Long itemId, String email,
			Float bidAmount) {
		EBid eBid = new EBid();
		eBid.setTime(new Date());
		eBid.setBid(bidAmount);

		EUser user = new EUser();
		user.setEmail(email);
		eBid.setUser(user);

		EItem eItem = getItemDao().getAuction(itemId);
		if (eBid.getBid() > eItem.getBasePrice()
				&& getBiddingService().addBid(itemId,
						EntityToDtoConverter.convertEBidToBid(eBid))) {
			eBid.setItem(eItem);
			getItemDao().createBid(eBid);
		}
	}

	@Override
	public List<Bid> getBids(Long itemId) {
		return getBiddingService().getBids(itemId);
	}

	private IItemDao getItemDao() {
		return itemDao;
	}

	private ICategoryDao getCategoryDao() {
		return categoryDao;
	}

	private IUserDao getUserDao() {
		return userDao;
	}

	private ISchedulingService getSchedulingService() {
		return schedulingService;
	}

	private IBiddingService getBiddingService() {
		return biddingService;
	}

}
