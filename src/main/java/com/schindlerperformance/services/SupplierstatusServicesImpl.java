package com.schindlerperformance.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.schindlerperformance.dao.GenericDao;
import com.schindlerperformance.dao.SupplierstatusDao;
import com.schindlerperformance.model.Supplierstatus;

public class SupplierstatusServicesImpl 	implements SupplierstatusServices {

	@Autowired
	SupplierstatusDao supplierstatusDao;

	

	public boolean addSupplierstatus(Supplierstatus supplierstatus) {
		return supplierstatusDao.addSupplierstatus(supplierstatus);
	}

	@Override
	public List<Supplierstatus> getSupplierstatuss() {

		return supplierstatusDao.getSupplierstatuss();
	}

	@Override
	public boolean deleteSupplierstatus(int supplierstatus_id) {

		return supplierstatusDao.deleteSupplierstatus(supplierstatus_id);
	}

	
	@Override
	public List<Supplierstatus> getSupplierstatusByName(String name) {
		return supplierstatusDao.getSupplierstatusByName(name);

	}

	@Override
	public Supplierstatus getSupplierstatuss(String name) {

		return supplierstatusDao.getSupplierstatus(name);
	}

	@Override
	public List<Supplierstatus> searchSupplierstatus1(Supplierstatus supplierstatus) {

		return supplierstatusDao.searchSupplierstatus1(supplierstatus);
	}

	@Override
	public List<Supplierstatus> suppleirStatusUpdateBySuppCodeSuppTypeAndSuppYear(String supplierCode,
			String supplerType, String supplerYear) {
		return supplierstatusDao.suppleirStatusUpdateBySuppCodeSuppTypeAndSuppYear(supplierCode, supplerType,
				supplerYear);
	}

}
