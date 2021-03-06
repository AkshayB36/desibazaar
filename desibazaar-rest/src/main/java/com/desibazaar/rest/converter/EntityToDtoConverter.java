package com.desibazaar.rest.converter;

import java.util.ArrayList;
import java.util.List;

import com.desibazaar.rest.entity.EBid;
import com.desibazaar.rest.entity.ECategory;
import com.desibazaar.rest.entity.EItem;
import com.desibazaar.rest.entity.EUser;
import com.desibazaar.rest.vo.Bid;
import com.desibazaar.rest.vo.Category;
import com.desibazaar.rest.vo.Item;
import com.desibazaar.rest.vo.User;

/**
 * @author Varda Laud
 *
 */
public class EntityToDtoConverter {

	/* Item Converter */
	public static List<Item> convertEItemToItem(List<EItem> eItems) {
		if (eItems == null)
			return null;
		List<Item> items = new ArrayList<Item>();
		for (EItem eItem : eItems) {
			items.add(convertEItemToItem(eItem));
		}
		return items;
	}

	public static Item convertEItemToItem(EItem eItem) {
		if (eItem == null)
			return null;
		Item item = new Item();
		item.setItemId(eItem.getItemId());
		item.setName(eItem.getName());
		item.setDescription(eItem.getDescription());
		item.setBasePrice(eItem.getBasePrice());
		item.setSellingPrice(eItem.getSellingPrice());
		item.setStartsAt(eItem.getStartsAt());
		item.setImage(eItem.getImage());
		item.setEndsAt(eItem.getEndsAt());
		item.setCategory(convertECategoryToCategory(eItem.getCategory()));
		item.setSeller(convertEUserToUser(eItem.getSeller()));
		item.setBuyer(convertEUserToUser(eItem.getBuyer()));
		item.setRating(eItem.getRating());
		item.setReview(eItem.getReview());
		item.setBids(convertEBidToBid(eItem.getBids()));
		item.setStatus(eItem.getStatus());
		item.setSubscribed(eItem.isSubscribed());
		return item;
	}

	/* Bid Converter */
	public static List<Bid> convertEBidToBid(List<EBid> eBids) {
		if (eBids == null)
			return null;
		List<Bid> bids = new ArrayList<Bid>();
		for (EBid eBid : eBids) {
			bids.add(convertEBidToBid(eBid));
		}
		return bids;
	}

	public static Bid convertEBidToBid(EBid eBid) {
		if (eBid == null)
			return null;
		Bid bid = new Bid();
		bid.setBid(eBid.getBid());
		bid.setTime(eBid.getTime());
		bid.setUser(convertEUserToUser(eBid.getUser()));
		return bid;
	}

	/* User Converter */
	public static User convertEUserToUser(EUser eUser) {
		if (eUser == null)
			return null;
		User user = new User();
		user.setEmail(eUser.getEmail());
		user.setName(eUser.getName());
		user.setAddress(eUser.getAddress());
		user.setNumber(eUser.getNumber());
		user.setPassword(eUser.getPassword());
		user.setRating(eUser.getRating());
		return user;
	}

	/* Category Converter */
	public static List<Category> convertECategoryToCategory(
			List<ECategory> eCategories) {
		if (eCategories == null)
			return null;
		List<Category> categories = new ArrayList<Category>();
		for (ECategory eCategory : eCategories) {
			categories.add(convertECategoryToCategory(eCategory));
		}
		return categories;
	}

	public static Category convertECategoryToCategory(ECategory eCategory) {
		if (eCategory == null)
			return null;
		Category category = new Category();
		category.setName(eCategory.getName());
		category.setDescription(eCategory.getDescription());
		return category;
	}

}
