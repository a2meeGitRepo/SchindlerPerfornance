package com.schindlerperformance.services;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.schindlerperformance.dao.EscalationDao;
import com.schindlerperformance.dao.GenericDao;
import com.schindlerperformance.model.Escalation;

public class EscalationServicesImpl  implements EscalationServices{
	
	@Autowired
	EscalationDao escalationDao;
	

	public void addEscalation(Escalation escalation){
		 escalationDao.save(escalation);	
	}
	
	@Override
	public List<Escalation> getEscalations() {
		// TODO Auto-generated method stub
		return escalationDao.getEscalations();
	}

	@Override
	public void deleteEscalation(int escalation_id) {
		// TODO Auto-generated method stub
		Optional<Escalation> optional= escalationDao.findById(escalation_id);
		
		if(optional.isPresent()){
			Escalation  escalation= optional.get();
			escalation.setDeletes(0);
			escalationDao.save(escalation);
		}
		
	}

	

	@Override
	public List<Escalation> getEscalationByName(String name) {
		return escalationDao.getEscalationByName(name);
		
	}

	@Override
	public Escalation getEscalations(String name) {
		
		return escalationDao.getEscalation(name);
	}

	@Override
	public List<Escalation> searchEscalation1(Escalation escalation) {
		// TODO Auto-generated method stub
		return escalationDao.searchEscalation1(escalation.getEscalation_suppliername(),escalation.getEscalation_suppliertype(),escalation.getEscalation_year());
	}

	@Override
	public List<Escalation> getExisting(Escalation escalation) {
		// TODO Auto-generated method stub
		return escalationDao.getExisting(escalation.getEscalation_suppliername(),escalation.getEscalation_suppliertype(),escalation.getEscalation_year(),escalation.getEscalation_suppliercode());
	}



	@Override
	public List<Escalation> getMonthly() {
		// TODO Auto-generated method stub
		Calendar now = Calendar.getInstance();
		int year = now.get(Calendar.YEAR);
		int month = now.get(Calendar.MONTH);

		String yr = Integer.toString(year);
		String m = Integer.toString(month);
		return escalationDao.getMonthly(yr);
	}
}
