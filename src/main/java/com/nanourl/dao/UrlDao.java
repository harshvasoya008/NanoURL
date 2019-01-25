package com.nanourl.dao;

/*
 * Dao layer for the Url
 * 
 * Author: Harsh Vasoya
 */

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.nanourl.models.Url;

public interface UrlDao extends JpaRepository<Url, String> {

	public Url findByShortUrl(String shortUrl);

	@Modifying
	@Transactional
	@Query(value = "UPDATE nanourl.urls SET updated_at = now() WHERE id = ?1", nativeQuery = true)
	public void recordUpdateTime(Long id);

	@Modifying
	@Transactional
	@Query(value = "DELETE FROM nanourl.urls WHERE updated_at <= ?1", nativeQuery = true)
	public void deleteExpiredUrls(Date expiryDate);
}
