package com.schindlerperformance.services;

import java.util.List;

import com.schindlerperformance.model.Escalation;

public interface EscalationServices {
	
	public void addEscalation(Escalation escalation);
	
	public List<Escalation> getEscalations();
	
	public void deleteEscalation(int escalation_id);

	public List<Escalation> getEscalationByName(String name);

	public Escalation getEscalations(String name);
	
	public List<Escalation> searchEscalation1(Escalation escalation);

	public List<Escalation> getExisting(Escalation escalation);



	public List<Escalation> getMonthly();

}
