package com.nanourl.services;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.nanourl.dao.ReportDao;
import com.nanourl.models.Report;

@Service
public class ReportService {

	@Autowired
	private ReportDao reportDao;

	/* Service which updates the total-click-count for today */
	@Async
	public void updateClickCount() {
		reportDao.updateTotalClicks();
	}

	/*
	 * Service which generates the report of today. Report includes number of
	 * shortUrl created, longUrl added and unique clicks along with the count of
	 * total clicks
	 */
	public Report generateReport() {
		
		Report report = reportDao.findTodayReport();
		List<BigInteger> queryResult = reportDao.countShortUrlCreatedAndLongUrlAdded();
		
		report.setNumShortUrlCreated(queryResult.get(0).longValue());
		report.setNumLongUrlAdded(queryResult.get(1).longValue());
		report.setNumUniqueClicks(reportDao.countUniqueClicks().longValue());

		report = reportDao.save(report);

		return report;
	}

}
