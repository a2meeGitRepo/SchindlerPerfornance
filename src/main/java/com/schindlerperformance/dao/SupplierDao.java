package com.schindlerperformance.dao;

import java.util.List;

import com.schindlerperformance.model.Supplier;

public interface SupplierDao {
	
	public boolean addSupplier(Supplier supplier);
	
	public List<Supplier> getSuppliers();
	
	/*--------------------unique serch data---------------------*/
	public List<Supplier> getSuppliers1();
	/*--------------------unique serch data---------------------*/
	
	public boolean deleteSupplier(int supplier_id);

	public List<Supplier> getSupplierByName(String name);

	public Supplier getSupplier(String name);
	
	public List<Supplier> searchSupplier1(Supplier supplier);
	
	public List<Supplier> getSuppNameSuppTypeSuppCode(String supplierCode, String supplierName, String supplierType);

	public List<Supplier> getSuppilerBySuppCodeSuppType(String supplierCode, String supplierType);
}
