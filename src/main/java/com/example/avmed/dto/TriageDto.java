package com.example.avmed.dto;

import lombok.Data;

@Data
public class TriageDto {

    //triage
    private String bloodPressure;
    private String heartRate;
    private String breathingFrequency;
    private String temperature;
    private String weight;
    private String height;

}