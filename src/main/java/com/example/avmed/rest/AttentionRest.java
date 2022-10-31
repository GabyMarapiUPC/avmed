package com.example.avmed.rest;

import com.example.avmed.dto.*;
import com.example.avmed.service.AttentionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@Slf4j
public class AttentionRest {

    @Autowired
    AttentionService attentionService;



    //attention
    @PostMapping("/register-attention")
    public ResponseEntity registerAttention(@RequestBody AttentionRqDto attentionDto) {
        log.info("[POST] START registerAttention");
        attentionService.registerAttention(attentionDto);
        log.info("[POST] END registerAttention");
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping("/attentions/healthPersonnel/{healthPersonnelId}")
    public ResponseEntity<List<AttentionDto>> getAttentionsByHealthPersonnelId(@PathVariable("healthPersonnelId") String healthPersonnelId) {
        log.info("[POST] START getAttentions");
        List<AttentionDto> attentions = attentionService.getAttentionsByHealthPersonnelId(healthPersonnelId);
        log.info("[POST] END getAttentions");
        return new ResponseEntity<>(attentions, HttpStatus.OK);
    }

    @GetMapping("/attentions/patient/{patientId}")
    public ResponseEntity<List<AttentionDto>> getAttentionsByPatientId(@PathVariable("patientId") String patientId) {
        log.info("[POST] START getAttentions");
        List<AttentionDto> attentions = attentionService.getAttentionsByPatientId(patientId);
        log.info("[POST] END getAttentions");
        return new ResponseEntity<>(attentions, HttpStatus.OK);
    }

    @GetMapping("/attentions/user/{userId}")
    public ResponseEntity<List<AttentionDto>> getAttentionsByUserId(@PathVariable("userId") String patientId) {
        log.info("[POST] START getAttentions");
        List<AttentionDto> attentions = attentionService.getAttentionsByPatientId(patientId);
        log.info("[POST] END getAttentions");
        return new ResponseEntity<>(attentions, HttpStatus.OK);
    }


    //report
    @GetMapping("/attentions/total")
    public ResponseEntity<Integer> getTotalAttentions() {
        log.info("[POST] START getTotalAttentions");
        Integer total = attentionService.getTotalAttentions();
        log.info("[POST] END getTotalAttentions");
        return new ResponseEntity<>(total, HttpStatus.OK);
    }
}
