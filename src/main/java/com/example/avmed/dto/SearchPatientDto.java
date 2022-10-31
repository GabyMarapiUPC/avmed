package com.example.avmed.dto;

import lombok.Data;

@Data
public class SearchPatientDto {
    private String insuredCode;
    private String documentNumber;
}