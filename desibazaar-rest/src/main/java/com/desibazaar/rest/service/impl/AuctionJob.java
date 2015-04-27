package com.desibazaar.rest.service.impl;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.desibazaar.rest.service.IBiddingService;

/**
 * @author Varda Laud
 *
 */
public class AuctionJob implements Job {
	private final static Logger LOGGER = Logger.getLogger(AuctionJob.class);

	@Autowired
	private IBiddingService biddingService;

	@Override
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
		JobDataMap dataMap = context.getJobDetail().getJobDataMap();
		long auctionId = Long.parseLong(dataMap.getString("auctionId"));
		LOGGER.debug("Job Triggered Auction Id : " + auctionId);
		getBiddingService().handleAuctionStartStop(auctionId);
	}

	public IBiddingService getBiddingService() {
		return biddingService;
	}
}
