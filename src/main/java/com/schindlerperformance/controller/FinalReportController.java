package com.schindlerperformance.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.schindlerperformance.services.FinalReportSevice;



//@Controller
//@RequestMapping("/finalReport")
//@ComponentScan("com.net.services")
public class FinalReportController {
	
//	@Autowired
	FinalReportSevice finalReportService;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public @ResponseBody List getList(@PathVariable("year") String year ){
		
		List finalReport= finalReportService.getReport(year);
		
		
		return finalReport;
	}
}
