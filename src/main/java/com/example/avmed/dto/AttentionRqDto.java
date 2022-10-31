package com.example.avmed.dto;

import lombok.Data;

import java.util.List;

@Data
public class AttentionRqDto {

    private String id;

    private String patientReport;
    private TriageDto triage;
    private List<String> diagnosisId;
    private String recommendations;
    private List<TreatmentRqDto> treatments;
    private String expirationDate;


    private String patientId;
    private String healthPersonnelId;
    private String appointmentId;
}