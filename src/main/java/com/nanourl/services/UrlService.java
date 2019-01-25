package com.nanourl.services;

/*
 * Microservices related to Url
 * 
 * Author: Harsh Vasoya
 */
import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.nanourl.dao.UrlDao;
import com.nanourl.helpers.Constants;
import com.nanourl.helpers.UniqueKeyGenerator;
import com.nanourl.models.Url;

@Service
public class UrlService {

	private UniqueKeyGenerator keyGenerator = new UniqueKeyGenerator();
	@Autowired
	private UrlDao urlDao;
	@Autowired
	private ReportService reportService;
	@Autowired
	private CacheUrlService cacheUrlService;

	/* Service for creating shortUrl from longUrl */
	public ResponseEntity<Object> createUrl(Url newUrl) {

		// Check if length of longUrl is long enough to get shorten
		if (newUrl.getLongUrl().length() <= Constants.SHORT_URL_LENGTH)
			return new ResponseEntity<Object>(Constants.MSG_URL_TOO_SHORT, HttpStatus.NOT_ACCEPTABLE);

		long uniqueKey = keyGenerator.generateKey();
		String uniqueHash = keyGenerator.convertToBase62(uniqueKey);
		int index = (new Random()).nextInt(3);

		String shortUrl = Constants.DOMAIN_NAMES[index] + uniqueHash;
		newUrl.setShortUrl(shortUrl);
		Url url = urlDao.save(newUrl);

		// Check other two domains if current shortUrl is already present in DB and
		// if all the other domains are occupied, report error.
		int cnt = 1;
		while (url == null && cnt <= 2) {
			index = (index + 1) % 3;
			cnt++;
			shortUrl = Constants.DOMAIN_NAMES[index] + uniqueHash;
			newUrl.setShortUrl(shortUrl);
			url = urlDao.save(newUrl);
		}

		if (url != null) {
			return new ResponseEntity<Object>(url, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<Object>(Constants.MSG_SAVE_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/* Service to retrieve longUrl from shortUrl */
	public String getLongUrl(String shortUrl) {
		String longUrl = cacheUrlService.findLongUrl(shortUrl);

		// Count click only if shortUrl exist.
		if (longUrl != null)
			reportService.updateClickCount();

		return longUrl;
	}

	/* Service to remove expired Urls from the database */
	public void removeOldUrls() {
		Date expiryDate = new Date(System.currentTimeMillis() - (long) Constants.URL_EXPIRY_TIME_HOURS * 3600 * 1000);
		urlDao.deleteExpiredUrls(expiryDate);
	}
}
