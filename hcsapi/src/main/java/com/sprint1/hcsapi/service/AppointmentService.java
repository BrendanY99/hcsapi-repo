package com.sprint1.hcsapi.service;

import com.sprint1.hcsapi.domain.Appointment;

public interface AppointmentService {

	public Appointment saveOrUpdate(Appointment appointment);
	
	public Appointment viewAppointmentById(long id);
	
	public Iterable<Appointment> viewAllAppointments();
	
	public void removeAppointmentById(long id);
}
