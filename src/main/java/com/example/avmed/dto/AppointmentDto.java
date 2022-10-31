package com.example.avmed.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)

@Data
public class AppointmentDto {
    private String id;
    private String day;
    private String startHour;
    private String endHour;
    private String state;
    private PatientDto patient;
    private String healthPersonnelId;
    private HealthPersonnelDto healthPersonnel;
}