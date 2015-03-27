package com.desibazaar.rest.converter;

import com.desibazaar.rest.entity.ECategory;
import com.desibazaar.rest.entity.EItem;
import com.desibazaar.rest.entity.EUser;
import com.desibazaar.rest.vo.Category;
import com.desibazaar.rest.vo.Item;
import com.desibazaar.rest.vo.User;

/**
 * @author Varda Laud
 *
 */
public class DtoToEntityConverter {

	/* Item Converter */
	public static EItem convertItemToEItem(Item item, EItem eItem) {
		if (item == null)
			return null;
		if (eItem == null)
			eItem = new EItem();
		eItem.setItemId(item.getItemId());
		eItem.setName(item.getName());
		eItem.setDescription(item.getDescription());
		eItem.setBasePrice(item.getBasePrice());
		eItem.setSellingPrice(item.getSellingPrice());
		eItem.setStartsAt(item.getStartsAt());
		eItem.setEndsAt(item.getEndsAt());

		if (item.getCategory() != null) {
			ECategory eCategory = new ECategory();
			eCategory.setName(item.getCategory().getName());
			eItem.setCategory(eCategory);
		}

		if (item.getSeller() != null) {
			EUser seller = new EUser();
			seller.setEmail(item.getSeller().getEmail());
			eItem.setSeller(seller);
		}

		if (item.getBuyer() != null) {
			EUser buyer = new EUser();
			buyer.setEmail(item.getBuyer().getEmail());
			eItem.setBuyer(buyer);
		}

		eItem.setRating(item.getRating());
		eItem.setReview(item.getReview());
		eItem.setStatus(item.getStatus());
		return eItem;
	}

	/* User Converter */
	public static EUser convertUserToEUser(User user, EUser eUser) {
		if (user == null)
			return null;
		if (eUser == null)
			eUser = new EUser();
		eUser.setEmail(user.getEmail());
		eUser.setName(user.getName());
		eUser.setAddress(user.getAddress());
		eUser.setNumber(user.getNumber());
		eUser.setPassword(user.getPassword());
		eUser.setRating(user.getRating());
		return eUser;
	}

	/* Category Converter */
	public static ECategory convertCategoryToECategory(Category category,
			ECategory eCategory) {
		if (category == null)
			return null;
		if (eCategory == null)
			eCategory = new ECategory();
		eCategory.setName(category.getName());
		eCategory.setDescription(category.getDescription());
		return eCategory;
	}

}
