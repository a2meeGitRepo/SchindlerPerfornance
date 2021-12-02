package com.schindlerperformance.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.schindlerperformance.dao.SuppliertypeDao;
import com.schindlerperformance.dao.GenericDao;
import com.schindlerperformance.model.Suppliertype;

public class SuppliertypeServicesImpl  implements SuppliertypeServices{
	
	@Autowired
	SuppliertypeDao suppliertypeDao;
	
	
	public boolean addSuppliertype(Suppliertype suppliertype){
		return suppliertypeDao.addSuppliertype(suppliertype);	
	}
	
	@Override
	public List<Suppliertype> getSuppliertypes() {
		// TODO Auto-generated method stub
		return suppliertypeDao.getSuppliertypes();
	}

	@Override
	public boolean deleteSuppliertype(int suppliertype_id) {
		// TODO Auto-generated method stub
		return suppliertypeDao.deleteSuppliertype(suppliertype_id);
	}

	
	@Override
	public List<Suppliertype> getSuppliertypeByName(String name) {
		return suppliertypeDao.getSuppliertypeByName(name);
		
	}

	@Override
	public Suppliertype getSuppliertypes(String name) {
		
		return suppliertypeDao.getSuppliertype(name);
	}

}
