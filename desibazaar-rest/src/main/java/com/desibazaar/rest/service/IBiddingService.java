package com.desibazaar.rest.service;

import java.util.List;

import com.desibazaar.rest.vo.Bid;

/**
 * @author Varda Laud
 *
 */
public interface IBiddingService {
	public List<Bid> getBids(Long auctionId);

	public boolean addBid(Long auctionId, Bid bid);

	public void handleAuctionStartStop(Long auctionId);
}
