package com.schindlerperformance.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.schindlerperformance.dao.OtdDao;
import com.schindlerperformance.dao.SupplierstatusDao;
import com.schindlerperformance.dao.GenericDao;
import com.schindlerperformance.model.Otd;
import com.schindlerperformance.model.Supplierstatus;

public class OtdServicesImpl   implements OtdServices {

	@Autowired
	OtdDao otdDao;
	@Autowired
	SupplierstatusDao supplierstatusDao;
	
	public boolean addOtd(Otd otd) {
		boolean  flag = otdDao.addOtd(otd);
		if(flag) {
			String supplierCode = otd.getOtd_suppliercode();
			String supplierName = otd.getOtd_suppliername();
			String supplierType = otd.getOtd_suppliertype();
			String year = otd.getOtd_year();
			List <Supplierstatus> list = supplierstatusDao.getSupplierstatusByNameTypeYear(supplierCode,supplierType,year);
			System.out.println("SIZE ==="+list.size());
		
			if(list.size()==0) {
				System.out.println("IN NEW ENTRY ====");
				Supplierstatus supplierstatus = new Supplierstatus();
				supplierstatus.setSupplierstatus_suppliercode(supplierCode);
				supplierstatus.setSupplierstatus_suppliername(supplierName);
				supplierstatus.setSupplierstatus_suppliertype(supplierType);
				supplierstatus.setSupplierstatus_year(year);
				supplierstatus.setSupplierstatus_otd(Double.toString(otd.getOtd_ytd()));
				supplierstatus.setDeletes(1);
				supplierstatusDao.addSupplierstatus(supplierstatus);
				
			}else {
				System.out.println("IN Edit ENTRY ====");
				Supplierstatus supplierstatus = list.get(0);
				supplierstatus.setSupplierstatus_otd(Double.toString(otd.getOtd_ytd()));
				supplierstatus.setDeletes(1);
				supplierstatusDao.addSupplierstatus(supplierstatus);
				
			}
		}
		
		
		return true;
		
		
	}

	@Override
	public List<Otd> getOtds() {
		// TODO Auto-generated method stub
		return otdDao.getOtds();
	}

	@Override
	public boolean deleteOtd(int otd_id) {
		// TODO Auto-generated method stub
		return otdDao.deleteOtd(otd_id);
	}

	
	@Override
	public List<Otd> getOtdByName(String name) {
		return otdDao.getOtdByName(name);

	}

	@Override
	public Otd getOtds(String name) {

		return otdDao.getOtd(name);
	}

	@Override
	public List<Otd> otdUpdateBySuppCodeAndSuppType(String supplierCode, String suplierType, String supplierYear) {
		// TODO Auto-generated method stub
		return otdDao.otdUpdateBySuppCodeAndSuppType(supplierCode, suplierType, supplierYear);
	}

	@Override
	public List<Otd> searchOtd1(Otd otd) {
		// TODO Auto-generated method stub
		return otdDao.searchOtd1(otd);
	}

	/*--------------------unique serch data---------------------*/
	@Override
	public List<Otd> getOtds1() {
		// TODO Auto-generated method stub
		return otdDao.getOtds1();
	}
	/*--------------------unique serch data---------------------*/

	
	
	@Override
	public List<Otd> getLowPerForOtd(String year, String otdPero, String supplierCode, String suplierType) {
		// TODO Auto-generated method stub
		return otdDao.getLowPerForOtd(year, otdPero, supplierCode, suplierType);
	}

	@Override
	public List<Otd> getHighPerForOtd(String year, String otdPero, String supplierCode, String suplierType) {
		// TODO Auto-generated method stub
		return otdDao.getHighPerForOtd(year, otdPero, supplierCode, suplierType);
	}

	@Override
	public List<Otd> getAvg() {
		// TODO Auto-generated method stub
		return otdDao.getAvg();
	}
	
	@Override
	public List<Otd> getAvg3() {
		// TODO Auto-generated method stub
		return otdDao.getAvg3();
	}

	@Override
	public List<Object> getMonthly() {
		// TODO Auto-generated method stub
		return otdDao.getMonthly();
	}

	@Override
	public List<Otd> getOtdBySupplierAndYear(String supplierCodde, String otdYear) {
		// TODO Auto-generated method stub
		return otdDao.getOtdBySupplierAndYear(supplierCodde,otdYear);
	}

	@Override
	public List<Otd> getLowPer(String monthCol, String backYear) {
		// TODO Auto-generated method stub
		return otdDao.getLowPer(monthCol,backYear);
	}

	@Override
	public List<Otd> getOtdBySupplierTypeYear(String supplierCode, String supplierType, String otdYear) {
		// TODO Auto-generated method stub
		return otdDao.getOtdBySupplierTypeYear(supplierCode,supplierType,otdYear);
	}

	
}
