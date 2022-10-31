package com.example.avmed.dto;

import com.example.avmed.model.appointment.Appointment;
import lombok.Data;

import java.util.List;

@Data
public class AppointmentsDto {
    private List<AppointmentDto> pending;
    private List<AppointmentDto> old;
}