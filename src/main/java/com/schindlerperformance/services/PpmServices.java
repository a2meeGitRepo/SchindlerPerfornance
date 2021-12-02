package com.schindlerperformance.services;

import java.util.List;

import com.schindlerperformance.model.Ppm;
import com.schindlerperformance.model.Ppm2;

public interface PpmServices {
	
	public boolean addPpm(Ppm ppm);
	
	public List<Ppm> getPpms();
	
	public boolean deletePpm(int ppm_id);

	public List<Ppm> getPpmByName(String name);

	public Ppm getPpms(String name);
	
	public List<Ppm> ppmUpdateBySuppCodeAndSuppType(String supplierCode, String suplierType, String supplierYear);

	public List<Ppm> searchPpm1(Ppm ppm);

	/*--------------------unique serch data---------------------*/
	public List<Ppm> getPpms1();
	/*--------------------unique serch data---------------------*/
	
	public List<Ppm> getHighPerForPpm(String year, String ppmFP, String ppmSuppCode, String ppmSuppType);
	
	public List<Ppm> getLowPerForPpm(String year, String ppmFP, String ppmSuppCode, String ppmSuppType);


	public List<Object> getMonthly();

	public boolean addPpm2(Ppm2 ppm2);

	public List<Ppm2> getPpm2s();

	public float getmaxPpm(String supplierCode, String supplierType, String year);
	
}
