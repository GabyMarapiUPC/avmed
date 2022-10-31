package com.example.avmed.service;

import com.example.avmed.dto.AssociatePatientDto;
import com.example.avmed.dto.PatientDto;
import com.example.avmed.dto.ResponseDto;
import com.example.avmed.dto.SearchPatientDto;

import java.util.List;

public interface PatientService {
    ResponseDto registerPatient(PatientDto patientDto);
    List<PatientDto> getPatients(String userId, String documentNumber, String insuredCode);
    PatientDto getPatientById(String patientId);
    Integer getTotalPatients();
    ResponseDto associateUserToPatient(AssociatePatientDto associatePatientDto);


}
