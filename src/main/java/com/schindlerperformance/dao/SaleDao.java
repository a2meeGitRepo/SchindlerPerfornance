package com.schindlerperformance.dao;

import java.util.List;

import com.schindlerperformance.model.Sale;

public interface SaleDao extends GenericDao<Sale, Integer>{
	
	public List<Sale> check(Sale sale);
	
	public boolean addSale(Sale sale);
	
	public List<Sale> getSale();
	
	public boolean deleteSale(int sale_id);

	public List<Sale> getSale1();
	
	public List<Sale> searchSale(Sale sale);

	public List<Sale> searchSale1(Sale sale);
	
	public boolean deleteSale1(int sale_id);


	public List<Sale> getSaleByName(String loc_description);


	
}
