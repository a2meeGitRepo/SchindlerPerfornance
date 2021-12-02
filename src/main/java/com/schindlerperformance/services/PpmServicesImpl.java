package com.schindlerperformance.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.schindlerperformance.dao.PpmDao;
import com.schindlerperformance.dao.SupplierstatusDao;
import com.schindlerperformance.dao.GenericDao;
import com.schindlerperformance.model.Ppm;
import com.schindlerperformance.model.Ppm2;
import com.schindlerperformance.model.Supplierstatus;

public class PpmServicesImpl implements PpmServices {

	@Autowired
	PpmDao ppmDao;
	@Autowired
	SupplierstatusDao supplierstatusDao;
	

	public boolean addPpm(Ppm ppm) {
		System.out.println("In SERVICE YTD "+ppm.getPpm_ytd());
		boolean flag = ppmDao.addPpm(ppm);
		
		if(flag) {
			String supplierCode = ppm.getPpm_suppliercode();
			String supplierName = ppm.getPpm_suppliername();
			String supplierType = ppm.getPpm_supplierType();
			String year = ppm.getPpm_year();
			List <Supplierstatus> list = supplierstatusDao.getSupplierstatusByNameTypeYear(supplierCode,supplierType,year);
			
			if(list.size()==0) {
				
				Supplierstatus supplierstatus = new Supplierstatus();
				supplierstatus.setSupplierstatus_suppliercode(supplierCode);
				supplierstatus.setSupplierstatus_suppliername(supplierName);
				supplierstatus.setSupplierstatus_suppliertype(supplierType);
				supplierstatus.setSupplierstatus_year(year);
				supplierstatus.setDeletes(1);
				supplierstatus.setSupplierstatus_ppm(Double.toString(ppm.getPpm_ytd()));
				
				supplierstatusDao.addSupplierstatus(supplierstatus);
				
			}else {
				
				Supplierstatus supplierstatus = list.get(0);
				supplierstatus.setSupplierstatus_ppm(Double.toString(ppm.getPpm_ytd()));
				supplierstatus.setDeletes(1);
				supplierstatusDao.addSupplierstatus(supplierstatus);
				
			}
		}	
		return true;
	}

	@Override
	public List<Ppm> getPpms() {
		// TODO Auto-generated method stub
		return ppmDao.getPpms();
	}

	@Override
	public boolean deletePpm(int ppm_id) {
		// TODO Auto-generated method stub
		return ppmDao.deletePpm(ppm_id);
	}

	

	@Override
	public List<Ppm> getPpmByName(String name) {
		return ppmDao.getPpmByName(name);

	}

	@Override
	public Ppm getPpms(String name) {

		return ppmDao.getPpm(name);
	}

	@Override
	public List<Ppm> ppmUpdateBySuppCodeAndSuppType(String supplierCode, String suplierType, String supplierYear) {
		return ppmDao.ppmUpdateBySuppCodeAndSuppType(supplierCode, suplierType, supplierYear);
	}

	
	@Override
	public List<Ppm> searchPpm1(Ppm ppm) {
		// TODO Auto-generated method stub
		return ppmDao.searchPpm1(ppm);
	}

	/*--------------------unique serch data---------------------*/
	@Override
	public List<Ppm> getPpms1() {
		// TODO Auto-generated method stub
		return ppmDao.getPpms1();
	}
	/*--------------------unique serch data---------------------*/

	@Override
	public List<Ppm> getHighPerForPpm(String year, String ppmFP, String ppmSuppCode, String ppmSuppType) {
		// TODO Auto-generated method stub
		return ppmDao.getHighPerForPpm(year, ppmFP, ppmSuppCode, ppmSuppType);
	}
	
	@Override
	public List<Ppm> getLowPerForPpm(String year, String ppmFP, String ppmSuppCode, String ppmSuppType) {
		// TODO Auto-generated method stub
		return ppmDao.getLowPerForPpm(year, ppmFP, ppmSuppCode, ppmSuppType);
	}



	@Override
	public List<Object> getMonthly() {
		// TODO Auto-generated method stub
		return ppmDao.getMonthly();
	}

	@Override
	public boolean addPpm2(Ppm2 ppm2) {
		// TODO Auto-generated method stub
		return ppmDao.addPpm2(ppm2);
	}

	@Override
	public List<Ppm2> getPpm2s() {
		// TODO Auto-generated method stub
		return ppmDao.getPpm2s();
	}

	@Override
	public float getmaxPpm(String supplierCode, String supplierType, String year) {
		// TODO Auto-generated method stub
		return ppmDao.getmaxPpm(supplierCode,supplierType,year);
	}


}
