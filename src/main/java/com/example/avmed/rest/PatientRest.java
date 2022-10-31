package com.example.avmed.rest;

import com.example.avmed.dto.AssociatePatientDto;
import com.example.avmed.dto.AttentionDto;
import com.example.avmed.dto.PatientDto;
import com.example.avmed.dto.ResponseDto;
import com.example.avmed.service.PatientService;
import com.example.avmed.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@Slf4j
public class PatientRest {

    @Autowired
    PatientService patientService;


    //pattient
    //register patient
    @PostMapping("/register-patient")
    public ResponseEntity registerPatient(@RequestBody PatientDto patientDto) {
        log.info("[POST] START registerPatient");
        ResponseDto responseDto = patientService.registerPatient(patientDto);
        log.info("[POST] END registerPatient");
        return new ResponseEntity<ResponseDto>(responseDto, HttpStatus.OK);

    }

    //get patients
    @GetMapping("/patients")
    public ResponseEntity<List<PatientDto>> getPatients(@RequestParam(value = "userId", required = false) String userId,
                                                        @RequestParam(value = "documentNumber", required = false) String documentNumber,
                                                        @RequestParam(value = "insuredCode", required = false) String insuredCode) {
        log.info("[POST] START getPatients");
        List<PatientDto> patients = patientService.getPatients(userId, documentNumber, insuredCode);
        log.info("[POST] END getPatients");
        return new ResponseEntity<>(patients, HttpStatus.OK);
    }

    //report
    @GetMapping("/patients/total")
    public ResponseEntity<Integer> getTotalPatients() {
        log.info("[POST] START getTotalPatients");
        Integer total = patientService.getTotalPatients();
        log.info("[POST] END getTotalPatients");
        return new ResponseEntity<>(total, HttpStatus.OK);
    }

    //associate patient
    @PostMapping("/associate-patient")
    public ResponseEntity associatePatient(@RequestBody AssociatePatientDto associatePatientDto) {
        log.info("[POST] START associatePatient");
        ResponseDto responseDto = patientService.associateUserToPatient(associatePatientDto);
        log.info("[POST] END associatePatient");
        return new ResponseEntity<ResponseDto>(responseDto, HttpStatus.OK);

    }

}
