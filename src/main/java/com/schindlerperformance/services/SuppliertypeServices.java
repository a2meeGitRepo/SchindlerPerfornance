package com.schindlerperformance.services;

import java.util.List;

import com.schindlerperformance.model.Suppliertype;

public interface SuppliertypeServices {
	
	public boolean addSuppliertype(Suppliertype suppliertype);
	
	public List<Suppliertype> getSuppliertypes();
	
	public boolean deleteSuppliertype(int suppliertype_id);

	public List<Suppliertype> getSuppliertypeByName(String name);

	public Suppliertype getSuppliertypes(String name);

}
