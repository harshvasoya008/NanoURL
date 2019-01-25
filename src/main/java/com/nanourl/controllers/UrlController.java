package com.nanourl.controllers;

/*
 * Controller for Url
 * 
 * Author: Harsh Vasoya
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nanourl.helpers.Constants;
import com.nanourl.models.Url;
import com.nanourl.services.UrlService;

@Controller
public class UrlController {

	@Autowired
	private UrlService urlService;

	/* Create a new shortURL for given longURL */
	@RequestMapping(value = "/url", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Object> createUrl(@RequestBody Url newUrl) {
		return urlService.createUrl(newUrl);
	}

	/* Get longURL for given shortURL */
	@RequestMapping(value = "/url", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Object> getLongUrl(@RequestParam("shorturl") String shortUrl) {
		String longUrl = urlService.getLongUrl(shortUrl);

		if (longUrl != null) {
			return new ResponseEntity<Object>(longUrl, HttpStatus.OK);
		} else {
			return new ResponseEntity<Object>(Constants.MSG_NOT_FOUND, HttpStatus.NOT_FOUND);
		}
	}

	/* Remove all expired URLs from the database */
	@RequestMapping(value = "/url", method = RequestMethod.DELETE)
	@ResponseBody
	public void removeOldUrls() {
		urlService.removeOldUrls();
	}
}
