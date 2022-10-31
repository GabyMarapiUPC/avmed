package com.example.avmed.dto;

import lombok.Data;

import java.util.List;

@Data
public class AttentionDto {

    private String id;
    private String updateDate;

    private String patientReport;
    private TriageDto triage;
    private List<DiagnosisDto> diagnoses;
    private String recommendations;
    private List<TreatmentDto> treatments;
    private String expirationDate;


    private PatientDto patient;
    private String healthPersonnelId;
}