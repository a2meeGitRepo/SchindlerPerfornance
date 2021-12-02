package com.schindlerperformance.services;

import java.util.List;

import javax.transaction.Transactional;

import com.schindlerperformance.dto.FinalReportDto;
import com.schindlerperformance.model.Performance;
import com.schindlerperformance.model.Supplierstatus;

@Transactional
public interface PerformanceServices {
	
	public boolean addPerformance(Performance performance);
	
	public List<Performance> getPerformances();
	
	public boolean deletePerformance(int performance_id);

	public List<Performance> getPerformanceByName(String name);

	public Performance getPerformances(String name);

	public List<Performance>  performanceUpdateBySuppCodeSuppTypeAndSuppYear(String supplierCode, String supplerType, String supplerYear);

	public List<Performance> getPerformances2(String otd_suppliercode, String otd_year, String otd_suppliertype,
			String otdper);
	

	public List<Performance> getPerformances3(String ppm_suppliercode, String ppm_year, String ppm_supplierType,
			String ppmper);

	public List<Performance> getPerformances4(String fpy_suppliercode, String fpy_year, String fpy_suppliertype,
			String fpyper);
	
	
	/*-----------------------------------high performance by year------------------------------------*/
	public List<Performance> getPerformances5(String otd_suppliercode, String otd_year, String otd_suppliertype,
			Double otd_ytd);
	

	public List<Performance> getPerformances6(String ppm_suppliercode, String ppm_year, String ppm_supplierType,
			long ppm_ytd);

	public List<Performance> getPerformances7(String fpy_suppliercode, String fpy_year, String fpy_suppliertype,
			Double fpy_ytd);
	
	public List<FinalReportDto> getReport(String year);
	
}
