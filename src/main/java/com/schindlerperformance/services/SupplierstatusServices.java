package com.schindlerperformance.services;

import java.util.List;

import com.schindlerperformance.model.Supplierstatus;

public interface SupplierstatusServices {
	
	public boolean addSupplierstatus(Supplierstatus supplierstatus);
	
	public List<Supplierstatus> getSupplierstatuss();
	
	public boolean deleteSupplierstatus(int supplierstatus_id);

	public List<Supplierstatus> getSupplierstatusByName(String name);

	public Supplierstatus getSupplierstatuss(String name);
	
	public List<Supplierstatus> searchSupplierstatus1(Supplierstatus supplierstatus);
	
	public List<Supplierstatus>  suppleirStatusUpdateBySuppCodeSuppTypeAndSuppYear(String supplierCode, String supplerType, String supplerYear);

}
