package com.schindlerperformance.services;

import java.util.List;

import com.schindlerperformance.model.Sale;

public interface SaleServices  {
	
	public boolean addSale(Sale sale);
	
	public List<Sale> getSale();
	
	public boolean deleteSale(int sale_id);
	
	public List<Sale> check(Sale sale);

	public List<Sale> getSale1();
	
	public List<Sale> searchSale(Sale sale);

	public List<Sale> searchSale1(Sale sale);
	
	public List<Sale> getSaleByName(String loc_description);

	public boolean deleteSale1(int sale_id);

		
}
