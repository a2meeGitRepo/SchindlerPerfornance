package com.schindlerperformance.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.schindlerperformance.model.Suppliertype;
import com.schindlerperformance.model.Status;
import com.schindlerperformance.services.SuppliertypeServices;

@Controller
@RequestMapping("/suppliertype")
public class SuppliertypeController {
	
	@Autowired
	SuppliertypeServices suppliertypeServices;
	
	
@RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody
	
	Status addSuppliertype(@RequestBody Suppliertype suppliertype) {
		try {
			suppliertypeServices.addSuppliertype(suppliertype);
			System.out.println("First Name:"+suppliertype.getSuppliertype_type());
			return new Status("Suppliertype added Successfully !");
		} catch (Exception e) {
			// e.printStackTrace();
			return new Status(e.toString());
		}

	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public @ResponseBody
	List<Suppliertype> getSuppliertypes() {
		List<Suppliertype> suppliertypeList = null;
		try {
			suppliertypeList = suppliertypeServices.getSuppliertypes();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return suppliertypeList;
	}
	
@RequestMapping(value = "/delete/{suppliertype_id}", method = RequestMethod.GET)
	public @ResponseBody
	Status deleteSuppliertype(@PathVariable("suppliertype_id") int suppliertype_id) {
		try {
			suppliertypeServices.deleteSuppliertype(suppliertype_id);
			return new Status("Suppliertype Deleted Successfully !");
		} catch (Exception e) {
			// e.printStackTrace();
			return new Status(e.toString());
		}

	}

}
