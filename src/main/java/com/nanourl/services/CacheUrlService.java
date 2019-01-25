package com.nanourl.services;

/* 
 * Cached service for faster retrieval of longURLs for a given shortURL
 * 
 * Author: Harsh Vasoya
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.nanourl.dao.UrlDao;
import com.nanourl.models.Url;

@Service
public class CacheUrlService {

	@Autowired
	private UrlDao urlDao;

	@Cacheable("shortToLong")
	public String findLongUrl(String shortUrl) {
		Url url = urlDao.findByShortUrl(shortUrl);
		if (url != null) {
			urlDao.recordUpdateTime(url.getId());
		}

		return url.getLongUrl();
	}
}
