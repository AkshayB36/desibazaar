package com.desibazaar.rest.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.desibazaar.rest.service.IAuctionService;
import com.desibazaar.rest.util.Util;
import com.desibazaar.rest.vo.Bid;
import com.desibazaar.rest.vo.Item;
import com.desibazaar.rest.vo.User;

/**
 * @author Varda Laud
 *
 */
@Controller
@RequestMapping("/auctions")
public class AuctionController {
	private final static Logger LOGGER = Logger
			.getLogger(AuctionController.class);

	@Autowired
	private IAuctionService auctionService;

	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody void createAuction(@RequestBody Item item,
			HttpServletRequest request) {
		LOGGER.debug("Received createAuction() request Item Name : "
				+ item.getName() + " User Name : "
				+ Util.getLoggedInUser(request));
		User user = new User();
		user.setEmail(Util.getLoggedInUser(request));
		item.setSeller(user);
		getAuctionService().createAuction(item);
	}

	@RequestMapping(method = RequestMethod.PUT, consumes = "application/json")
	public @ResponseBody void updateAuction(@RequestBody Item item) {
		LOGGER.debug("Received updateAuction() request Item Name : "
				+ item.getName());
		getAuctionService().updateAuction(item);
	}

	@RequestMapping(value = "/{item_id}", method = RequestMethod.DELETE)
	public @ResponseBody void deleteAuction(@PathVariable("item_id") Long itemId) {
		LOGGER.debug("Received deleteAuction() request Item Id : " + itemId);
		getAuctionService().deleteAuction(itemId);
	}

	@RequestMapping(value = "/{item_id}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody Item getAuction(@PathVariable("item_id") Long itemId) {
		LOGGER.debug("Received getAuction() request Item Id : " + itemId);
		return getAuctionService().getAuction(itemId);
	}

	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<Item> getAuctions(HttpServletRequest request) {
		LOGGER.debug("Received getAuctions() request User Name : "
				+ Util.getLoggedInUser(request));
		return getAuctionService().getAuctions(Util.getLoggedInUser(request));
	}

	@RequestMapping(value = "/{item_id}/subscribe", method = RequestMethod.GET)
	public @ResponseBody void subscribeAuction(
			@PathVariable("item_id") Long itemId, HttpServletRequest request) {
		LOGGER.debug("Received subscribeAuction() request Item Id : " + itemId
				+ " User Name : " + Util.getLoggedInUser(request));
		getAuctionService().subscribeAuction(itemId,
				Util.getLoggedInUser(request));
	}

	@RequestMapping(value = "/{item_id}/unsubscribe", method = RequestMethod.GET)
	public @ResponseBody void unsubscribeAuction(
			@PathVariable("item_id") Long itemId, HttpServletRequest request) {
		LOGGER.debug("Received unsubscribeAuction() request Item Id : "
				+ itemId + " User Name : " + Util.getLoggedInUser(request));
		getAuctionService().unsubscribeAuction(itemId,
				Util.getLoggedInUser(request));
	}

	@RequestMapping(value = "/{item_id}/bids", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<Bid> getBids(@PathVariable("item_id") Long itemId) {
		LOGGER.debug("Received getBids() request Item Id : " + itemId);
		return getAuctionService().getBids(itemId);
	}

	@RequestMapping(value = "/{item_id}/bids", method = RequestMethod.POST)
	public @ResponseBody void createBid(@PathVariable("item_id") Long itemId,
			@RequestParam("bid") Float bid, HttpServletRequest request) {
		LOGGER.debug("Received createBid() request Item Id : " + itemId
				+ " User Name : " + Util.getLoggedInUser(request) + " Bid : "
				+ bid);
		getAuctionService().createBid(itemId, Util.getLoggedInUser(request),
				bid);
	}

	private IAuctionService getAuctionService() {
		return auctionService;
	}
}
