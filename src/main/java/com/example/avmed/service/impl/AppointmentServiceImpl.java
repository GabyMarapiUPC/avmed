package com.example.avmed.service.impl;

import com.example.avmed.dto.*;
import com.example.avmed.model.HealthPersonnel;
import com.example.avmed.model.Patient;
import com.example.avmed.model.appointment.Appointment;
import com.example.avmed.repository.AppointmentRepository;
import com.example.avmed.repository.HealthPersonnelRepository;
import com.example.avmed.repository.PatientRepository;
import com.example.avmed.service.AppointmentService;
import com.example.avmed.service.AttentionService;
import com.example.avmed.util.Constant;
import com.example.avmed.util.DateUtil;
import org.bson.types.ObjectId;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    AppointmentRepository appointmentRepository;

    @Autowired
    HealthPersonnelRepository healthPersonnelRepository;

    @Autowired
    PatientRepository patientRepository;


    @Override
    public List<AppointmentDto> getAppointments(String healthPersonalId) {
        List<Appointment> appointments =  appointmentRepository.findByHealthPersonnelId(new ObjectId(healthPersonalId));
        List<AppointmentDto> appointmentDtos = appointments.stream()
                .filter(appointment -> appointment.getState().equals(Constant.AppointmentState.PENDING))
                .map(this::getAppointmentDto).collect(Collectors.toList());
        return appointmentDtos;
    }

    @Override
    public AppointmentsDto getAppointmentsByUserId(String userId) {
        AppointmentsDto appointmentsDto = new AppointmentsDto();

        List<Patient> patients = patientRepository.findByUserId(new ObjectId(userId));
        List<Appointment> appointments =  appointmentRepository.findByPatientIds(patients.stream().map(patient -> new ObjectId(patient.getId())).collect(Collectors.toList()));
        List<AppointmentDto> appointmentPendingDtos = appointments.stream()
                .filter(appointment -> appointment.getState().equals(Constant.AppointmentState.PENDING))
                .map(this::getAppointmentDto).collect(Collectors.toList());

        List<AppointmentDto> appointmentPastDtos = appointments.stream()
                .filter(appointment -> !appointment.getState().equals(Constant.AppointmentState.PENDING))
                .map(this::getAppointmentDto).collect(Collectors.toList());

        appointmentsDto.setPending(appointmentPendingDtos);
        appointmentsDto.setOld(appointmentPastDtos);
        return appointmentsDto;
    }


    @Override
    public void registerAppointment(AppointmentRqDto appointmentRqDto) {
        Appointment appointment = new Appointment();
        BeanUtils.copyProperties(appointmentRqDto, appointment);
        appointment.setPatientId(new ObjectId(appointmentRqDto.getPatientId()));
        appointment.setHealthPersonnelId(new ObjectId(appointmentRqDto.getHealthPersonnelId()));
        appointmentRepository.save(appointment);
    }

    @Override
    public void updateAppointmentState(String appointmentId, String state) {
        Appointment appointment = appointmentRepository.findById(appointmentId).stream().findFirst().orElse(null);
        appointment.setState(state);
        appointmentRepository.save(appointment);
    }

    public AppointmentDto getAppointmentDto(Appointment appointment) {
        AppointmentDto appointmentDto = new AppointmentDto();
        BeanUtils.copyProperties(appointment, appointmentDto);
        Patient patient = patientRepository.findById(appointment.getPatientId().toString()).stream().findFirst().orElse(null);
        HealthPersonnel healthPersonnel = healthPersonnelRepository.findById(appointment.getHealthPersonnelId().toString()).stream().findFirst().orElse(null);
        appointmentDto.setPatient(getPatientDtoFromEntity(patient));
        appointmentDto.setHealthPersonnel(getHealthPersonnelDtoFromEntity(healthPersonnel));
        return appointmentDto;
    }

    private HealthPersonnelDto getHealthPersonnelDtoFromEntity(HealthPersonnel healthPersonnel) {
        HealthPersonnelDto healthPersonnelDto = new HealthPersonnelDto();

        healthPersonnelDto.setId(healthPersonnel.getId());
        BeanUtils.copyProperties(healthPersonnel, healthPersonnelDto);
        healthPersonnelDto.setServiceType(Constant.SERVICE_TYPE);
        healthPersonnelDto.setSignature(null);
        healthPersonnelDto.setCmp(null);
        healthPersonnelDto.setDocumentNumber(null);
        return healthPersonnelDto;
    }
    private PatientDto getPatientDtoFromEntity(Patient patient) {
        PatientDto patientDto = new PatientDto();

        patientDto.setId(patient.getId());
        patientDto.setName(patient.getName());
        patientDto.setPaternalLastName(patient.getPaternalLastName());
        patientDto.setMaternalLastName(patient.getMaternalLastName());
        patientDto.setDocumentNumber(patient.getDocumentNumber());
        patientDto.setAge(DateUtil.calculateAge(LocalDate.parse(patient.getBirthDate())));
        patientDto.setInsuredCode(patient.getInsuredCode());
        return patientDto;
    }
}
