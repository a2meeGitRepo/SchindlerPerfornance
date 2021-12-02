package com.schindlerperformance.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.schindlerperformance.dao.FinalReportDao;



public class FinalReportServiceImpl implements FinalReportSevice
{

	@Autowired
	FinalReportDao finalReportDao;
	
	@Override
	public List getReport(String year) {
		// TODO Auto-generated method stub
		return finalReportDao.getReport(year);
	}

}
