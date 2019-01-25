package com.nanourl.controllers;

/*
 * Controller for Report
 * 
 * Author: Harsh Vasoya
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nanourl.models.Report;
import com.nanourl.services.ReportService;

@Controller
public class ReportController {

	@Autowired
	private ReportService reportService;

	/* Generate today's report */
	@RequestMapping(value = "/report", method = RequestMethod.GET)
	@ResponseBody
	public Report getTodayReport() {
		return reportService.generateReport();
	}
}
