package com.desibazaar.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.desibazaar.rest.service.IAuctionService;
import com.desibazaar.rest.vo.Item;

/**
 * @author Varda Laud
 *
 */
@Controller
@RequestMapping("/auctions")
public class AuctionController {

	@Autowired
	private IAuctionService auctionService;

	@RequestMapping(value = "/", method = RequestMethod.POST, consumes = "application/json")
	public void createAuction(Item item) {
		getAuctionService().createAuction(item);
	}

	@RequestMapping(value = "/", method = RequestMethod.PUT, consumes = "application/json")
	public void updateAuction(Item item) {
		getAuctionService().updateAuction(item);
	}

	@RequestMapping(value = "/{item_id}", method = RequestMethod.DELETE)
	public void deleteAuction(@PathVariable("item_id") Long itemId) {
		getAuctionService().deleteAuction(itemId);
	}

	@RequestMapping(value = "/{item_id}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody Item getAuction(@PathVariable("item_id") Long itemId) {
		return getAuctionService().getAuction(itemId);
	}

	@RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<Item> getAuctions(
			@RequestParam("logged_in_user_email") String email) {
		return getAuctionService().getAuctions(email);
	}

	private IAuctionService getAuctionService() {
		return auctionService;
	}
}
