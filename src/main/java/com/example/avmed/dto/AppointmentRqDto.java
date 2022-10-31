package com.example.avmed.dto;

import lombok.Data;

@Data
public class AppointmentRqDto {
    private String id;
    private String day;
    private String startHour;
    private String endHour;
    private String state;
    private String patientId;
    private String healthPersonnelId;
}