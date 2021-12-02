package com.schindlerperformance.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

import com.schindlerperformance.model.Fpy;
import com.schindlerperformance.model.Otd;
import com.schindlerperformance.model.Ppm;
@Transactional
public class FinalReportDaoImpl implements FinalReportDao
{
	@Autowired
	HibernateTemplate hibernateTemplate;
	
//	@Autowired
//	Ppm ppm;
//	
//	@Autowired
//	Otd otd;
//	
//	@Autowired
//	Fpy fpy;

	@Override
	public List getReport(String year) {
		Ppm ppm=new Ppm();
		Otd otd=new Otd();
		Fpy fpy=new Fpy();
		System.out.println("year "+year);

		Query q = hibernateTemplate.getSessionFactory().getCurrentSession().createQuery("SELECT ppm.ppm_suppliercode, ppm.ppm_suppliername," + 
				"ppm.ppm_jan, ppm.ppm_feb,ppm.ppm_mar,ppm.ppm_apr,ppm.ppm_may,ppm.ppm_june,ppm.ppm_july,ppm.ppm_aug,ppm.ppm_sep,ppm.ppm_oct,ppm.ppm_nov,ppm.ppm_dec,ppm.ppm_ytd," + 
				"otd.otd_jan,otd.otd_feb,otd.otd_mar,otd.otd_apr,otd.otd_may,otd.otd_june,otd.otd_july,otd.otd_aug,otd.otd_sep,otd.otd_oct,otd.otd_nov,otd.otd_dec,otd.otd_ytd," + 
				"fpy.fpy_jan,fpy.fpy_feb,fpy.fpy_mar,fpy.fpy_apr,fpy.fpy_may,fpy.fpy_june,fpy.fpy_july,fpy.fpy_aug,fpy.fpy_sep,fpy.fpy_oct,fpy.fpy_nov,fpy.fpy_dec,fpy.fpy_ytd" + 
				"from  ppm " + 
				"inner join otd  on ppm.ppm_suppliercode = otd.otd_suppliercode" + 
				"inner join fpy  on fpy.fpy_suppliercode = ppm.ppm_suppliercode" + 
				"where ppm.ppm_year = "+year+" and otd.otd_year = "+year+" and fpy.fpy_year ="+year+"");
		List finalReport=q.list();
		
		return finalReport;
	}

}
