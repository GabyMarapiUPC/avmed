package com.example.avmed.dto;

import lombok.Data;

@Data
public class TreatmentRqDto {
    private String medicineId;
    private String diagnosisId;

    private Integer frequencyNumber;
    private String frequencyType;
    private Integer durationNumber;
    private String durationType;
    private Integer dose;
    private String doseUn;
    private Integer quantity;
    private String routeAdministration;
}