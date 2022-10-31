package com.example.avmed.service;

import com.example.avmed.dto.AppointmentDto;
import com.example.avmed.dto.AppointmentRqDto;
import com.example.avmed.dto.AppointmentsDto;
import com.example.avmed.dto.PatientDto;

import java.util.List;

public interface AppointmentService {
    List<AppointmentDto> getAppointments(String healthPersonalId);
    void registerAppointment(AppointmentRqDto appointmentRqDto);
    void updateAppointmentState(String appointmentId, String state);
    AppointmentsDto getAppointmentsByUserId(String userId);

}
