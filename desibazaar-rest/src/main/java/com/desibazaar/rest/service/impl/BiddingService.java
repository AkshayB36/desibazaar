package com.desibazaar.rest.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desibazaar.rest.enums.Status;
import com.desibazaar.rest.service.IAuctionService;
import com.desibazaar.rest.service.IBiddingService;
import com.desibazaar.rest.vo.Bid;
import com.desibazaar.rest.vo.Item;

/**
 * @author Varda Laud
 *
 */
@Service
public class BiddingService implements IBiddingService {
	private static Map<Long, List<Bid>> auctionBids = new HashMap<Long, List<Bid>>();

	@Autowired
	private IAuctionService auctionService;

	@Override
	public void handleAuctionStartStop(Long auctionId) {
		Item item = getAuctionService().getAuction(auctionId);
		if (auctionBids.containsKey(auctionId)) {
			List<Bid> bids = auctionBids.get(auctionId);
			if (bids.size() == 0) {
				item.setStatus(Status.UnSold);
			} else {
				item.setStatus(Status.Sold);
				item.setBuyer(bids.get(bids.size() - 1).getUser());
				item.setSellingPrice(bids.get(bids.size() - 1).getBid());
			}
			getAuctionService().updateAuction(item);
			auctionBids.remove(auctionId);
		} else {
			auctionBids.put(auctionId, new ArrayList<Bid>());
			item.setStatus(Status.Active);
			getAuctionService().updateAuction(item);
		}
	}

	@Override
	public boolean addBid(Long auctionId, Bid bid) {
		List<Bid> bids = auctionBids.get(auctionId);
		if (bids.size() == 0
				|| (bids.get(bids.size() - 1).getBid() < bid.getBid() && bids
						.get(bids.size() - 1).getTime().before(bid.getTime()))) {
			bids.add(bid);
			return true;
		}
		return false;
	}

	@Override
	public List<Bid> getBids(Long auctionId) {
		return auctionBids.get(auctionId);
	}

	public IAuctionService getAuctionService() {
		return auctionService;
	}
}
