package com.schindlerperformance.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.schindlerperformance.dao.SaleDao;
import com.schindlerperformance.dao.GenericDao;
import com.schindlerperformance.model.Sale;

public class SaleServicesImpl  implements SaleServices{
	
	@Autowired
	SaleDao saleDao;


	
	public boolean addSale(Sale sale){
		
		return saleDao.addSale(sale);
	}
	
	

	
	public List<Sale> getSale(){
		return saleDao.getSale();
	
	}
	
	
	public List<Sale> getSale1(){
		return saleDao.getSale1();
	}
	
	public boolean deleteSale(int sale_id){
		
		return saleDao.deleteSale(sale_id);
		
	}

	
public boolean deleteSale1(int sale_id){
		
		return saleDao.deleteSale1(sale_id);
		
	}

	
	
	@Override
	public List<Sale> check(Sale sale) {
		// TODO Auto-generated method stub
		return saleDao.check(sale);
	}

	

	@Override
	public List<Sale> searchSale(Sale sale) {
		// TODO Auto-generated method stub
		return saleDao.searchSale(sale);
	}
	
	
	@Override
	public List<Sale> searchSale1(Sale sale) {
		// TODO Auto-generated method stub
		return saleDao.searchSale1(sale);
	}




	@Override
	public List<Sale> getSaleByName(String loc_description) {
		// TODO Auto-generated method stub
		return null;
	}


}
