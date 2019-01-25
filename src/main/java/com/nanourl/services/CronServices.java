package com.nanourl.services;

/*
 * CRON services required for automatic operations
 * 
 * Author: Harsh Vasoya
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.nanourl.dao.ReportDao;

@Service
public class CronServices {

	@Autowired
	private UrlService urlService;
	@Autowired
	private ReportService reportService;
	@Autowired
	private ReportDao reportDao;

	/* Scheduled everyday at 00:00:10 to remove expired urls */
	@Scheduled(cron = "10 0 0 * * *")
	public void removeExpiredUrls() {
		urlService.removeOldUrls();
	}

	/* Scheduled everyday at 00:00:00 to add new record into Report table */
	@Scheduled(cron = "0 0 0 * * *")
	public void addTodayRecord() {
		reportDao.addTodayRecord();
	}

	/*
	 * Scheduled everyday at 23:55:00 to update current day's record into Report
	 * table
	 */
	@Scheduled(cron = "0 55 23 * * *")
	public void generateReport() {
		reportService.generateReport();
	}
}
