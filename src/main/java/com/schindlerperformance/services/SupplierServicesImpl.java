package com.schindlerperformance.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.schindlerperformance.dao.SupplierDao;
import com.schindlerperformance.dao.GenericDao;
import com.schindlerperformance.model.Supplier;

public class SupplierServicesImpl  implements SupplierServices {

	@Autowired
	SupplierDao supplierDao;

	
	public boolean addSupplier(Supplier supplier) {
		return supplierDao.addSupplier(supplier);
	}

	@Override
	public List<Supplier> getSuppliers() {

		return supplierDao.getSuppliers();
	}

	/*--------------------unique serch data---------------------*/
	@Override
	public List<Supplier> getSuppliers1() {
		// TODO Auto-generated method stub
		return supplierDao.getSuppliers1();
	}
	/*--------------------unique serch data---------------------*/

	@Override
	public boolean deleteSupplier(int supplier_id) {

		return supplierDao.deleteSupplier(supplier_id);
	}

	

	@Override
	public List<Supplier> getSupplierByName(String name) {
		return supplierDao.getSupplierByName(name);

	}

	@Override
	public Supplier getSuppliers(String name) {

		return supplierDao.getSupplier(name);
	}

	@Override
	public List<Supplier> searchSupplier1(Supplier supplier) {

		return supplierDao.searchSupplier1(supplier);
	}

	@Override
	public List<Supplier> getSuppNameSuppTypeSuppCode(String supplierCode, String supplierName, String supplierType) {

		return supplierDao.getSuppNameSuppTypeSuppCode(supplierCode, supplierName, supplierType);
	}

	@Override
	public List<Supplier> getSuppilerBySuppCodeSuppType(String supplierCode, String supplierType) {
		return supplierDao.getSuppilerBySuppCodeSuppType(supplierCode, supplierType);
	}

}