package com.desibazaar.rest.service.impl;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.stereotype.Service;

import com.desibazaar.rest.service.ISchedulingService;

/**
 * @author Varda Laud
 *
 */
@Service
public class SchedulingService implements ISchedulingService {
	private Scheduler sched;

	@Override
	@PostConstruct
	public void initScheduler() {
		try {
			SchedulerFactory schedFact = new StdSchedulerFactory();
			sched = schedFact.getScheduler();
			sched.start();
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}

	@Override
	@PreDestroy
	public void destroyScheduler() {
		try {
			sched.shutdown();
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void addAuctionJob(Long auctionId, Date startDate, Date endDate) {
		String sAuctionId = Long.toString(auctionId);
		JobDetail job = newJob(AuctionJob.class)
				.withIdentity(sAuctionId, "auctions")
				.usingJobData("auctionId", sAuctionId).build();
		Trigger startTrigger = newTrigger()
				.withIdentity(sAuctionId + startDate, "auctions")
				.startAt(startDate).build();
		Trigger endTrigger = newTrigger()
				.withIdentity(sAuctionId + endDate, "auctions")
				.startAt(endDate).build();
		Set<Trigger> triggers = new HashSet<Trigger>();
		triggers.add(startTrigger);
		triggers.add(endTrigger);
		try {
			sched.scheduleJob(job, triggers, false);
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}
}
