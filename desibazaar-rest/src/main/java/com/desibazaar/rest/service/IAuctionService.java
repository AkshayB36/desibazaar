package com.desibazaar.rest.service;

import java.util.List;

import com.desibazaar.rest.vo.Category;
import com.desibazaar.rest.vo.Item;

/**
 * @author Varda Laud
 *
 */
public interface IAuctionService {

	/* Auctions */
	public void createAuction(Item item);

	public void updateAuction(Item item);

	public void deleteAuction(Long itemId);

	public Item getAuction(Long itemId);

	public List<Item> getAuctions(String email);

	/* Categories */
	public List<Category> getCategories();

}
