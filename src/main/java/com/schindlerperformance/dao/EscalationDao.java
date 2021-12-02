package com.schindlerperformance.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.schindlerperformance.model.Escalation;

public interface EscalationDao extends JpaRepository<Escalation, Integer>{
	
	@Query("from Escalation e where e.deletes=1")
	public List<Escalation> getEscalations();
	
	@Query("from Escalation e where e.deletes=1 and e.escalation_suppliername=?1")
	public List<Escalation> getEscalationByName(String name);
	@Query("from Escalation e where e.deletes=1 and e.escalation_suppliername=?1")
	public Escalation getEscalation(String name);

	@Query("from Escalation e where e.deletes=1 and e.escalation_suppliername=?1 and e.escalation_suppliertype=?2 and e.escalation_year=?3")
	public List<Escalation> searchEscalation1(String escalation_suppliername,String escalation_suppliertype,String escalation_year);
	@Query("from Escalation e where e.deletes=1 and e.escalation_suppliername=?1 and e.escalation_suppliertype=?2 and e.escalation_year=?3 and e.escalation_suppliercode=?4")
	public List<Escalation> getExisting(String escalation_suppliername,String escalation_suppliertype,String escalation_year,String escalation_suppliercode);

	@Query("from Escalation e where (e.escalation_suppliertype='kw' OR e.escalation_suppliertype='kw')and e.deletes=1  and e.escalation_year=?1")
	public List<Escalation> getMonthly(String year);
}
