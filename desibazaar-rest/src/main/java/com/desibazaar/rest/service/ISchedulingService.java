package com.desibazaar.rest.service;

import java.util.Date;

/**
 * @author Varda Laud
 *
 */
public interface ISchedulingService {
	public void initScheduler();

	public void destroyScheduler();

	void addAuctionJob(Long auctionId, Date startDate, Date endDate);
}
