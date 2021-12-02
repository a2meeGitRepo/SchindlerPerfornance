package com.schindlerperformance.services;

import java.util.List;

import com.schindlerperformance.model.Otd;

public interface OtdServices   {

	public boolean addOtd(Otd otd);

	public List<Otd> getOtds();

	public boolean deleteOtd(int otd_id);

	public List<Otd> getOtdByName(String name);

	public Otd getOtds(String name);

	public List<Otd> otdUpdateBySuppCodeAndSuppType(String supplierCode, String suplierType, String supplierYear);

	public List<Otd> searchOtd1(Otd otd);

	/*--------------------unique serch data---------------------*/
	public List<Otd> getOtds1();
	/*--------------------unique serch data---------------------*/
	
	public List<Otd> getHighPerForOtd(String year, String otdPero, String supplierCode, String suplierType);
	
	public List<Otd> getLowPerForOtd(String year, String otdPero, String supplierCode, String suplierType);

	public List<Otd> getAvg();
	
	public List<Otd> getAvg3();

	public List<Object> getMonthly();

	public List<Otd> getOtdBySupplierAndYear(String supplierCodde, String otdYear);

	public List<Otd> getLowPer(String monthCol, String backYear);

	public List<Otd> getOtdBySupplierTypeYear(String supplierCode, String supplierType, String otdYear);
	
	
}
