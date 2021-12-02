package com.schindlerperformance.services;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.Spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.schindlerperformance.dao.PerformanceDao;
import com.schindlerperformance.dto.FinalReportDto;
import com.schindlerperformance.dao.FinalReportDao;
import com.schindlerperformance.dao.GenericDao;
import com.schindlerperformance.model.Performance;
import com.schindlerperformance.model.Supplierstatus;

public class PerformanceServicesImpl  implements PerformanceServices {

	@Autowired
	PerformanceDao performanceDao;

	
	public boolean addPerformance(Performance performance) {
		return performanceDao.addPerformance(performance);
	}

	@Override
	public List<Performance> getPerformances() {
		// TODO Auto-generated method stub
		return performanceDao.getPerformances();
	}

	@Override
	public boolean deletePerformance(int performance_id) {
		// TODO Auto-generated method stub
		return performanceDao.deletePerformance(performance_id);
	}

	
	@Override
	public List<Performance> getPerformanceByName(String name) {
		return performanceDao.getPerformanceByName(name);

	}

	@Override
	public Performance getPerformances(String name) {

		return performanceDao.getPerformance(name);
	}

	@Override
	public List<Performance> performanceUpdateBySuppCodeSuppTypeAndSuppYear(String supplierCode, String supplerType,
			String supplerYear) {
		return performanceDao.performanceUpdateBySuppCodeSuppTypeAndSuppYear(supplierCode, supplerType, supplerYear);
	}

	@Override
	public List<Performance> getPerformances2(String otd_suppliercode, String otd_year, String otd_suppliertype,
			String otdper) {
		// TODO Auto-generated method stub
		return performanceDao.getPerformances2(otd_suppliercode, otd_year, otd_suppliertype, otdper);
	}

	@Override
	public List<Performance> getPerformances3(String ppm_suppliercode, String ppm_year, String ppm_supplierType,
			String ppmper) {
		// TODO Auto-generated method stub
		return performanceDao.getPerformances3(ppm_suppliercode, ppm_year, ppm_supplierType, ppmper);
	}

	@Override
	public List<Performance> getPerformances4(String fpy_suppliercode, String fpy_year, String fpy_suppliertype,
			String fpyper) {
		// TODO Auto-generated method stub
		return performanceDao.getPerformances4(fpy_suppliercode, fpy_year, fpy_suppliertype, fpyper);
	}

	@Override
	public List<Performance> getPerformances5(String otd_suppliercode, String otd_year, String otd_suppliertype,
			Double otd_ytd) {
		// TODO Auto-generated method stub
		return performanceDao.getPerformances5(otd_suppliercode, otd_year, otd_suppliertype, otd_ytd);
	}

	@Override
	public List<Performance> getPerformances6(String ppm_suppliercode, String ppm_year, String ppm_supplierType,
			long ppm_ytd) {
		// TODO Auto-generated method stub
		return performanceDao.getPerformances6(ppm_suppliercode, ppm_year, ppm_supplierType, ppm_ytd);
	}

	@Override
	public List<Performance> getPerformances7(String fpy_suppliercode, String fpy_year, String fpy_suppliertype,
			Double fpy_ytd) {
		// TODO Auto-generated method stub
		return performanceDao.getPerformances7(fpy_suppliercode, fpy_year, fpy_suppliertype, fpy_ytd);
	}

	@Override
	public List<FinalReportDto> getReport(String year) {
		// TODO Auto-generated method stub
		List<FinalReportDto> dtos=new ArrayList<>();
		
		List<Object[]> objects=performanceDao.getReport(year);
		
		
		System.out.println("SIZE OF LIST ===============----=================++++++++++++"+objects.size());
		for (Object[] objects2 : objects) {
			FinalReportDto dto=new FinalReportDto();
			dto.setPpm_suppliercode((String)objects2[0]);
			dto.setPpm_suppliername((String)objects2[1]);
			dto.setPpm_suppliertype((String)objects2[2]);
			dto.setPpm_jan((String)objects2[3]);
			dto.setPpm_feb((String)objects2[4]);
			dto.setPpm_mar((String)objects2[5]);
			dto.setPpm_apr((String)objects2[6]);
			dto.setPpm_may((String)objects2[7]);
			dto.setPpm_june((String)objects2[8]);
			dto.setPpm_july((String)objects2[9]);
			dto.setPpm_aug((String)objects2[10]);
			dto.setPpm_sep((String)objects2[11]);
			dto.setPpm_oct((String)objects2[12]);
			dto.setPpm_nov((String)objects2[13]);
			dto.setPpm_dec((String)objects2[14]);
			BigInteger integer=(BigInteger) objects2[15];
		//	dto.set((String)objects2[13]);
			long l=integer.longValue();			
			dto.setPpm_ytd(l);
			
			dto.setOtd_jan((String)objects2[16]);
			dto.setOtd_feb((String)objects2[17]);
			dto.setOtd_mar((String)objects2[18]);
			dto.setOtd_apr((String)objects2[19]);
			dto.setOtd_may((String)objects2[20]);
			dto.setOtd_june((String)objects2[21]);
			dto.setOtd_july((String)objects2[22]);
			dto.setOtd_aug((String)objects2[23]);
			dto.setOtd_sep((String)objects2[24]);
			dto.setOtd_oct((String)objects2[25]);
			dto.setOtd_nov((String)objects2[26]);
			dto.setOtd_dec((String)objects2[27]);
			BigDecimal bigDecimal=(BigDecimal) objects2[28];
			double d=bigDecimal.doubleValue();
			dto.setOtd_ytd(d);
			
			
			dto.setFpy_jan((String)objects2[29]);
			dto.setFpy_feb((String)objects2[30]);
			dto.setFpy_mar((String)objects2[31]);
			dto.setFpy_apr((String)objects2[32]);
			dto.setFpy_may((String)objects2[33]);
			dto.setFpy_june((String)objects2[34]);
			dto.setFpy_july((String)objects2[35]);
			dto.setFpy_aug((String)objects2[36]);
			dto.setFpy_sep((String)objects2[37]);
			dto.setFpy_oct((String)objects2[38]);
			dto.setFpy_nov((String)objects2[39]);
			dto.setFpy_dec((String)objects2[40]);
			BigDecimal bigDecimal1=(BigDecimal) objects2[41];
			double d1=bigDecimal.doubleValue();
			dto.setFpy_ytd(d1);	
			dtos.add(dto);
		}
		
		return dtos;
//		return performanceDao.getReport(year);
	}

}
