package com.example.avmed.rest;

import com.example.avmed.dto.AppointmentDto;
import com.example.avmed.dto.AppointmentRqDto;
import com.example.avmed.dto.AppointmentsDto;
import com.example.avmed.service.AppointmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@Slf4j
public class AppointmentRest {

    @Autowired
    AppointmentService appointmentService;



    //appointments
    @PostMapping("/register-appointment")
    public ResponseEntity registerAppointment(@RequestBody AppointmentRqDto appointmentRqDto) {
        log.info("[POST] START getMedicines");
        appointmentService.registerAppointment(appointmentRqDto);
        log.info("[POST] END getMedicines");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/appointments/{healthPersonalId}")
    public ResponseEntity<List<AppointmentDto>> getAppointments(@PathVariable("healthPersonalId") String healthPersonalId) {
        log.info("[POST] START getAppointments");
        List<AppointmentDto> appointments = appointmentService.getAppointments(healthPersonalId);
        log.info("[POST] END getAppointments");
        return new ResponseEntity<>(appointments, HttpStatus.OK);
    }


    @GetMapping("/appointments/user/{userId}")
    public ResponseEntity<AppointmentsDto> getAppointmentsByUserId(@PathVariable("userId") String userId) {
        log.info("[POST] START getAppointments");
        AppointmentsDto appointments = appointmentService.getAppointmentsByUserId(userId);
        log.info("[POST] END getAppointments");
        return new ResponseEntity<>(appointments, HttpStatus.OK);
    }



}
