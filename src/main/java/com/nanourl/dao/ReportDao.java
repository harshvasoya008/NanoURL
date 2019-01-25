package com.nanourl.dao;

/*
 * Dao layer for the Report 
 * 
 * Author: Harsh Vasoya
 */

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.nanourl.models.Report;

public interface ReportDao extends JpaRepository<Report, Integer> {

	@Modifying
	@Transactional
	@Query(value = "INSERT INTO nanourl.reports VALUES(null, now(), 0, 0, 0, 0)", nativeQuery=true)
	public void addTodayRecord();
	
	@Query(value = "SELECT * FROM nanourl.reports WHERE created_at >= curdate()", nativeQuery = true)
	public Report findTodayReport();
	
	@Query(value = "SELECT count(*), count(DISTINCT long_url) FROM nanourl.urls WHERE created_at >= curdate()", nativeQuery = true)
	public List<BigInteger> countShortUrlCreatedAndLongUrlAdded();

	@Query(value = "SELECT count(*) FROM nanourl.urls WHERE updated_at >= curdate() AND updated_at <> created_at", nativeQuery = true)
	public BigInteger countUniqueClicks();

	@Modifying
	@Transactional
	@Query(value = "UPDATE nanourl.reports SET num_total_clicks=num_total_clicks+1 WHERE created_at >= curdate()", nativeQuery = true)
	public void updateTotalClicks();

	@Query(value = "SELECT * FROM nanourl.reports WHERE DATE(created_at) = DATE(?1)", nativeQuery = true)
	public Report findReportByDate(Date queryDate);
}
