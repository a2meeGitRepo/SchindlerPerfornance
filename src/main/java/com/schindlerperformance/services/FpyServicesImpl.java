package com.schindlerperformance.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.schindlerperformance.dao.FpyDao;
import com.schindlerperformance.dao.GenericDao;
import com.schindlerperformance.dao.SupplierstatusDao;
import com.schindlerperformance.model.Fpy;
import com.schindlerperformance.model.Supplierstatus;

public class FpyServicesImpl  implements FpyServices {

	@Autowired
	FpyDao fpyDao;
	@Autowired
	SupplierstatusDao supplierstatusDao;
	

	public boolean addFpy(Fpy fpy) {
		boolean flag =fpyDao.addFpy(fpy);
		System.out.println("In Add FPY Service ");
		if(flag) {
			String supplierCode = fpy.getFpy_suppliercode();
			String supplierName = fpy.getFpy_suppliername();
			String supplierType = fpy.getFpy_suppliertype();
			String year = fpy.getFpy_year();
			List <Supplierstatus> list = supplierstatusDao.getSupplierstatusByNameTypeYear(supplierCode,supplierType,year);
			System.out.println("List Size "+list.size());
			if(list.size()==0) {
				System.out.println("IN NEW ENTRY ====");
				Supplierstatus supplierstatus = new Supplierstatus();
				supplierstatus.setSupplierstatus_suppliercode(supplierCode);
				supplierstatus.setSupplierstatus_suppliername(supplierName);
				supplierstatus.setSupplierstatus_suppliertype(supplierType);
				supplierstatus.setSupplierstatus_year(year);
				supplierstatus.setDeletes(1);
				supplierstatus.setSupplierstatus_fpy(Double.toString(fpy.getFpy_ytd()));
				//supplierstatus.setSupplierstatus_ppm(Double.toString(ppm.getPpm_ytd()));
				
				supplierstatusDao.addSupplierstatus(supplierstatus);
				
			}else {
				System.out.println("IN Edir ENTRY ====");
				Supplierstatus supplierstatus = list.get(0);
				supplierstatus.setSupplierstatus_fpy(Double.toString(fpy.getFpy_ytd()));
				supplierstatus.setDeletes(1);
				supplierstatusDao.addSupplierstatus(supplierstatus);
				
			}
		}
		return true;
	}

	@Override
	public List<Fpy> getFpys() {

		return fpyDao.getFpys();
	}

	@Override
	public boolean deleteFpy(int fpy_id) {

		return fpyDao.deleteFpy(fpy_id);
	}

	

	@Override
	public List<Fpy> getFpyByName(String name) {
		return fpyDao.getFpyByName(name);

	}

	@Override
	public Fpy getFpys(String name) {

		return fpyDao.getFpy(name);
	}

	@Override
	public List<Fpy> fpyUpdateBySuppCodeAndSuppType(String supplierCode, String suplierType, String supplierYear) {

		return fpyDao.fpyUpdateBySuppCodeAndSuppType(supplierCode, suplierType, supplierYear);
	}

	@Override
	public List<Fpy> searchFpy1(Fpy fpy) {

		return fpyDao.searchFpy1(fpy);
	}

	/*--------------------unique serch data---------------------*/
	@Override
	public List<Fpy> getFpys1() {

		return fpyDao.getFpys1();
	}
	/*--------------------unique serch data---------------------*/

	@Override
	public List<Fpy> getHighPerForFpy(String year, String fpyPF, String supplierCode, String suplierType) {

		return fpyDao.getHighPerForFpy(year, fpyPF, supplierCode, suplierType);
	}

	@Override
	public List<Fpy> getLowPerForFpy(String year, String fpyPF, String supplierCode, String suplierType) {

		return fpyDao.getLowPerForFpy(year, fpyPF, supplierCode, suplierType);
	}

	@Override
	public List<Fpy> getAvg() {
		// TODO Auto-generated method stub
		return fpyDao.getAvg();
	}
	

	@Override
	public List<Fpy> getAvg4() {
		// TODO Auto-generated method stub
		return fpyDao.getAvg4();
	}
	
	

	@Override
	public List<Object> getMonthly() {
		// TODO Auto-generated method stub
		return fpyDao.getMonthly();
	}

	
}
