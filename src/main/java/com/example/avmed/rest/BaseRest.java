package com.example.avmed.rest;

import com.example.avmed.dto.DiagnosisDto;
import com.example.avmed.dto.HealthPersonnelDto;
import com.example.avmed.dto.MedicineDto;
import com.example.avmed.dto.ReportDto;
import com.example.avmed.service.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@Slf4j
public class BaseRest {

    @Autowired
    BaseService baseService;

    //diagnosis
    @GetMapping("/diagnoses")
    public ResponseEntity<List<DiagnosisDto>> getDiagnoses() {
        log.info("[POST] START getDiagnosis");
        List<DiagnosisDto> diagnosis = baseService.getDiagnoses();
        log.info("[POST] END getDiagnosis");
        return new ResponseEntity<>(diagnosis, HttpStatus.OK);
    }

    //medicines
    @GetMapping("/medicines")
    public ResponseEntity<List<MedicineDto>> getMedicines() {
        log.info("[POST] START getMedicines");
        List<MedicineDto> diagnosis = baseService.getMedicines();
        log.info("[POST] END getMedicines");
        return new ResponseEntity<>(diagnosis, HttpStatus.OK);
    }

    //generateCode
    @GetMapping("/verificationCode/{phoneNumber}")
    public ResponseEntity<String> getHealthPersonnel(@PathVariable("phoneNumber") String phoneNumber) {
        log.info("[POST] START getUser");
        String code = baseService.getVerificationCode(phoneNumber);
        log.info("[POST] END getUser");
        return new ResponseEntity<>(code, HttpStatus.OK);
    }



}
