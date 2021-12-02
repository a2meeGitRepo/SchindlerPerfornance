package com.schindlerperformance.controller;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.schindlerperformance.config.EmailUtilityEscalation;
import com.schindlerperformance.model.Escalation;
import com.schindlerperformance.model.Status;
import com.schindlerperformance.services.EscalationServices;

@Controller
@RequestMapping("/escalation")
public class EscalationController {
	
	@Autowired
	EscalationServices escalationServices;
	
	
@RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody
	
	Status addEscalation(@RequestBody Escalation escalation) {
		try {
			
			
			List<Escalation> isPresent = escalationServices.getExisting(escalation);
			
			if(isPresent.size()==0){
			
				escalationServices.addEscalation(escalation);
				
			}
			
			else{
				
				for(Escalation e : isPresent){
					
					e.setEscalation_level1(escalation.getEscalation_level1());
					e.setEscalation_level2(escalation.getEscalation_level2());
					e.setEscalation_planreceive1(escalation.getEscalation_planreceive1());
					e.setEscalation_planreceive2(escalation.getEscalation_planreceive2());
					e.setEscalation_planvalidated1(escalation.getEscalation_planvalidated1());
					e.setEscalation_planvalidated2(escalation.getEscalation_planvalidated2());
					e.setEscalation_warning(escalation.getEscalation_warning());
					e.setEscalation_deescalated(escalation.getEscalation_deescalated());
					e.setEscalation_discontinued(escalation.getEscalation_discontinued());
					
					e.setMail_id(escalation.getMail_id());
					e.setMail_id2(escalation.getMail_id2());
					e.setMail_id3(escalation.getMail_id3());
					e.setMail_id4(escalation.getMail_id4());
					e.setMail_id5(escalation.getMail_id5());
					e.setMail_id6(escalation.getMail_id6());
					e.setMail_id7(escalation.getMail_id7());
					e.setMail_id8(escalation.getMail_id8());
					e.setMail_id9(escalation.getMail_id9());
					
					e.setEscalation_warningdate(escalation.getEscalation_warningdate());
					e.setEscalation_planreceive1date(escalation.getEscalation_planreceive1date());
					e.setEscalation_planvalidated1date(escalation.getEscalation_planvalidated1date());
					e.setEscalation_level1date(escalation.getEscalation_level1date());
					e.setEscalation_planreceive2date(escalation.getEscalation_planreceive2date());
					e.setEscalation_planvalidated2date(escalation.getEscalation_planvalidated2date());
					e.setEscalation_deescalateddate(escalation.getEscalation_deescalateddate());
					e.setEscalation_level2date(escalation.getEscalation_level2date());
					e.setEscalation_date(escalation.getEscalation_date());
					
					escalationServices.addEscalation(e);
				}
				
				
			}
			
			
			System.out.println("First Name:"+escalation.getEscalation_level1());
			return new Status("Escalation added Successfully !");
		} catch (Exception e) {
			// e.printStackTrace();
			return new Status(e.toString());
		}

	}


@RequestMapping(value = "/getExistingList", method = RequestMethod.GET)
public @ResponseBody
List<Escalation> getExisting(Escalation escalation) {
	List<Escalation> escalationList = null;
	try {
		escalationList = escalationServices.getExisting(escalation);
	} catch (Exception e) {
		e.printStackTrace();
	}
	return escalationList;
}


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public @ResponseBody
	List<Escalation> getEscalations() {
		List<Escalation> escalationList = null;
		try {
			escalationList = escalationServices.getEscalations();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return escalationList;
	}
	
@RequestMapping(value = "/delete/{escalation_id}", method = RequestMethod.GET)
	public @ResponseBody
	Status deleteEscalation(@PathVariable("escalation_id") int escalation_id) {
		try {
			escalationServices.deleteEscalation(escalation_id);
			return new Status("Escalation Deleted Successfully !");
		} catch (Exception e) {
			// e.printStackTrace();
			return new Status(e.toString());
		}

	}


@RequestMapping(value = "/sendmail", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
public synchronized  @ResponseBody
Status sendMail(@RequestBody Escalation escalation) {
	
	
	String filename =null;
	YearMonth thisMonth    = YearMonth.now();
	YearMonth lastMonth    = thisMonth.minusMonths(1);
	DateTimeFormatter monthYearFormatter = DateTimeFormatter.ofPattern("MMMM yyyy");
	
	if(escalation.getEscalation_warning()!=null){
		
		 filename =  escalation.getEscalation_warning().split("[/]")[2];
		
			
	}
	
	else if(escalation.getEscalation_planreceive1()!=null){
		
		 filename =  escalation.getEscalation_planreceive1().split("[/]")[2];
	}
	
	else if(escalation.getEscalation_planvalidated1()!=null){
		
		 filename =  escalation.getEscalation_planvalidated1().split("[/]")[2];
	}
	
	else if(escalation.getEscalation_level1()!=null){
		
		 filename =  escalation.getEscalation_level1().split("[/]")[2];
	}
	
	else if(escalation.getEscalation_planreceive2()!=null){
		
		 filename =  escalation.getEscalation_planreceive2().split("[/]")[2];
	}
	
	else if(escalation.getEscalation_planvalidated2()!=null){
		
		 filename =  escalation.getEscalation_planvalidated2().split("[/]")[2];
	}
	
	else if(escalation.getEscalation_deescalated()!=null){
		
		 filename =  escalation.getEscalation_deescalated().split("[/]")[2];
	}
	
	else if(escalation.getEscalation_level2()!=null){
		
		 filename =  escalation.getEscalation_level2().split("[/]")[2];
	}
	
	else if(escalation.getEscalation_discontinued()!=null){
		
		 filename =  escalation.getEscalation_discontinued().split("[/]")[2];
	}
	
	else{
		
		filename = null;
	}
	
	try {
		
		//EmailUtility.sendEmail("fghf", "587", "zsfvsdafgsad" , vp.getMail_id(), vp.getMail_id2(), vp.getMail_id3() ,vp.getMail_id4(), vp.getMail_id5(), vp.getMail_id6(), vp.getMail_id7(), vp.getMail_id8(), vp.getMail_id9(), "Performance Report", filename ,  "This Mail is generated from <b> Schindler India Pvt ltd. </b> </br></br> Dear "+vp.getVendor_name()+",</br></br> Your Performance Report for "+lastMonth.format(monthYearFormatter)+" is Attached in Mail.");
		//EmailUtilityEscalation.sendEmail("smtp.outlook.com", "587", "jainnikita995@outlook.com" , escalation.getMail_id(), escalation.getMail_id2(), escalation.getMail_id3() ,escalation.getMail_id4(), escalation.getMail_id5(), escalation.getMail_id6(), escalation.getMail_id7(), escalation.getMail_id8(), escalation.getMail_id9(), String.valueOf("Subject"), filename ,  "Hello Schindler");
		EmailUtilityEscalation.sendEmail("smtp.outlook.com", "587", "jainnikita995@outlook.com" , escalation.getMail_id(), escalation.getMail_id2(), escalation.getMail_id3() ,escalation.getMail_id4(), escalation.getMail_id5(), escalation.getMail_id6(), escalation.getMail_id7(), escalation.getMail_id8(), escalation.getMail_id9(), String.valueOf("Esclation_Report"), filename ,  "This Mail is generated from <b> Schindler India Pvt ltd. </b> </br></br> Dear "+escalation.getEscalation_suppliername()+",</br></br> Your Escalation_Report for "+lastMonth.format(monthYearFormatter)+" is Attached in Mail.");
		return new Status("Mail Send  Successfully !");
	} catch (Exception e) {
		
		System.out.println(e);
		return new Status(e.toString());
	}

}	


@RequestMapping(value = "/search1", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
public @ResponseBody List<Escalation> searchEscalation1(@RequestBody Escalation escalation) {
	List<Escalation> escalationList = null;
	try {
		escalationList = escalationServices.searchEscalation1(escalation);
	} catch (Exception e) {
		e.printStackTrace();
	}
	return escalationList;
}




@RequestMapping(value = "/monthlyreport", method = RequestMethod.GET)
public @ResponseBody List<Escalation> getMonthly() {
	List<Escalation> escalationList = null;
	try {
		escalationList = escalationServices.getMonthly();
	} catch (Exception e) {
		e.printStackTrace();
	}
	return escalationList;
}


}
