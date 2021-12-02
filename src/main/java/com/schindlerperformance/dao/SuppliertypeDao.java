package com.schindlerperformance.dao;

import java.util.List;

import com.schindlerperformance.model.Suppliertype;

public interface SuppliertypeDao {
	
	public boolean addSuppliertype(Suppliertype suppliertype);
	
	public List<Suppliertype> getSuppliertypes();
	
	public boolean deleteSuppliertype(int suppliertype_id);

	public List<Suppliertype> getSuppliertypeByName(String name);

	public Suppliertype getSuppliertype(String name);

}
