package com.schindlerperformance.controller;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.mail.internet.InternetAddress;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.schindlerperformance.config.EmailUtility;
import com.schindlerperformance.config.EmailUtility5;
import com.schindlerperformance.model.Escalation;
import com.schindlerperformance.model.Fpy;
import com.schindlerperformance.model.Otd;
import com.schindlerperformance.model.Ppm;
import com.schindlerperformance.model.Status;
import com.schindlerperformance.model.VPResponse;
import com.schindlerperformance.services.FpyServices;
import com.schindlerperformance.services.OtdServices;
import com.schindlerperformance.services.PpmServices;

@Controller
@RequestMapping("/vendorPerformance")
public class VendorPerformanceController {


	@Autowired
	FpyServices fpyServices;

	@Autowired
	OtdServices otdServices;

	@Autowired
	PpmServices ppmServices;

	@RequestMapping(value = "/venderData", method = RequestMethod.POST)
	public @ResponseBody VPResponse search(@RequestParam("supplierCode") String supplierCode,
			@RequestParam("supplierType") String supplierType, @RequestParam("supplierYear") String supplierYear) {
		
		//logger.info(supplierYear);
		//logger.info(supplierType);
		//logger.info(supplierCode);
		List<Fpy> fpyList = null;
		List<Otd> otdList = null;
		List<Ppm> ppmList = null;
		try {
			fpyList = fpyServices.fpyUpdateBySuppCodeAndSuppType(supplierCode, supplierType, supplierYear);
			otdList = otdServices.otdUpdateBySuppCodeAndSuppType(supplierCode, supplierType, supplierYear);
			ppmList = ppmServices.ppmUpdateBySuppCodeAndSuppType(supplierCode, supplierType, supplierYear);
			return new VPResponse(1, "data found", fpyList, otdList, ppmList);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	@RequestMapping(value = "/sendmail", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public synchronized  @ResponseBody
	Status sendMail(@RequestBody VPResponse vp) {
		
		
		String filename = vp.getFile_name();
		YearMonth thisMonth    = YearMonth.now();
		YearMonth lastMonth    = thisMonth.minusMonths(1);
		DateTimeFormatter monthYearFormatter = DateTimeFormatter.ofPattern("MMMM yyyy");
		try {
			//EmailUtility.sendEmail("fghf", "587", "zsfvsdafgsad" , vp.getMail_id(), vp.getMail_id2(), vp.getMail_id3() ,vp.getMail_id4(), vp.getMail_id5(), vp.getMail_id6(), vp.getMail_id7(), vp.getMail_id8(), vp.getMail_id9(), String.valueOf("Subject"), filename ,  "DrHello Schindler");
			//String subject="Performance Report"+lastMonth.format(monthYearFormatter);
			EmailUtility.sendEmail("fghf", "587", "zsfvsdafgsad" , vp.getMail_id(), vp.getMail_id2(), vp.getMail_id3() ,vp.getMail_id4(), vp.getMail_id5(), vp.getMail_id6(), vp.getMail_id7(), vp.getMail_id8(), vp.getMail_id9(), "Performance Report for "+lastMonth.format(monthYearFormatter), filename ,  "This Mail is generated from <b> Schindler India Pvt ltd. </b> </br></br> Dear "+vp.getVendor_name()+",</br></br> Your Performance Report for "+lastMonth.format(monthYearFormatter)+" is Attached in Mail.");
			return new Status("Mail Send  Successfully !");
		} catch (Exception e) {
			
			System.out.println(e);
			return new Status(e.toString());
		}

	}



}
