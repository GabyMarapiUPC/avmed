package com.example.avmed.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class PatientDto {
    private String id;
    private String name;
    private String paternalLastName;
    private String maternalLastName;
    private String documentNumber;
    private String birthDate;
    private String sex;
    private Integer age;
    private String insuredCode; //codigo asegurado
    private String clinicalHistoryNumber; //numero historia clinica
    private String userId;

}