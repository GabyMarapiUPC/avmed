package com.example.avmed.dto;

import lombok.Data;


@Data
public class AssociatePatientDto {
    private String userId;
    private String documentNumber;
    private String insuredCode;
}