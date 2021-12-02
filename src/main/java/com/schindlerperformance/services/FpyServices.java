package com.schindlerperformance.services;

import java.util.List;

import com.schindlerperformance.model.Fpy;

public interface FpyServices  {

	public boolean addFpy(Fpy fpy);

	public List<Fpy> getFpys();

	public boolean deleteFpy(int fpy_id);

	public List<Fpy> getFpyByName(String name);

	public Fpy getFpys(String name);

	public List<Fpy> fpyUpdateBySuppCodeAndSuppType(String supplierCode, String suplierType, String supplierYear);

	public List<Fpy> searchFpy1(Fpy fpy);

	/*--------------------unique serch data---------------------*/
	public List<Fpy> getFpys1();
	/*--------------------unique serch data---------------------*/
	
	public List<Fpy> getHighPerForFpy(String year, String fpyPF, String supplierCode, String suplierType);

	public List<Fpy> getLowPerForFpy(String year, String fpyPF, String supplierCode, String suplierType);

	public List<Fpy> getAvg();

	public List<Fpy> getAvg4();

	public List<Object> getMonthly();
	
	
}
