package com.schindlerperformance.services;

import java.util.List;

import com.schindlerperformance.model.Supplier;

public interface SupplierServices {
	
	public boolean addSupplier(Supplier supplier);
	
	public List<Supplier> getSuppliers();
	
	public boolean deleteSupplier(int supplier_id);

	public List<Supplier> getSupplierByName(String name);

	public Supplier getSuppliers(String name);

	public List<Supplier> searchSupplier1(Supplier supplier);
	/*--------------------unique serch data---------------------*/
	public List<Supplier> getSuppliers1();
	/*--------------------unique serch data---------------------*/
	public List<Supplier> getSuppNameSuppTypeSuppCode(String supplierCode, String supplierName, String supplierType);
	
	public List<Supplier> getSuppilerBySuppCodeSuppType(String supplierCode, String supplierType);
}
