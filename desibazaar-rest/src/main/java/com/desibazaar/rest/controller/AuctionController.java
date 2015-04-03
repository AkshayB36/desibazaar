package com.desibazaar.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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

	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody void createAuction(@RequestBody Item item) {
		getAuctionService().createAuction(item);
	}

	@RequestMapping(method = RequestMethod.PUT, consumes = "application/json")
	public @ResponseBody void updateAuction(@RequestBody Item item) {
		getAuctionService().updateAuction(item);
	}

	@RequestMapping(value = "/{item_id}", method = RequestMethod.DELETE)
	public @ResponseBody void deleteAuction(@PathVariable("item_id") Long itemId) {
		getAuctionService().deleteAuction(itemId);
	}

	@RequestMapping(value = "/{item_id}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody Item getAuction(@PathVariable("item_id") Long itemId) {
		return getAuctionService().getAuction(itemId);
	}

	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<Item> getAuctions(
			@RequestParam("logged_in_user_email") String email) {
		return getAuctionService().getAuctions(email);
	}

	@RequestMapping(value = "/{item_id}/subscribe", method = RequestMethod.GET)
	public @ResponseBody void subscribeAuction(@PathVariable("item_id") Long itemId,
			@RequestParam("logged_in_user_email") String email) {
		getAuctionService().subscribeAuction(itemId, email);
	}

	@RequestMapping(value = "/{item_id}/unsubscribe", method = RequestMethod.GET)
	public @ResponseBody void unsubscribeAuction(@PathVariable("item_id") Long itemId,
			@RequestParam("logged_in_user_email") String email) {
		getAuctionService().unsubscribeAuction(itemId, email);
	}

	private IAuctionService getAuctionService() {
		return auctionService;
	}
}
