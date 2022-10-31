package com.example.avmed.service.impl;

import com.example.avmed.dto.*;
import com.example.avmed.model.Patient;
import com.example.avmed.model.attention.*;
import com.example.avmed.repository.AttentionRepository;
import com.example.avmed.repository.PatientRepository;
import com.example.avmed.service.AppointmentService;
import com.example.avmed.service.AttentionService;
import com.example.avmed.service.BaseService;
import com.example.avmed.service.PatientService;
import com.example.avmed.util.Constant;
import org.bson.types.ObjectId;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class AttentionServiceImpl implements AttentionService {

    @Autowired
    AttentionRepository attentionRepository;

    @Autowired
    PatientRepository patientRepository;


    @Autowired
    BaseService baseService;


    @Autowired
    PatientService patientService;

    @Autowired
    AppointmentService appointmentService;


    @Override
    public void registerAttention(AttentionRqDto attentionDto) {
        attentionRepository.save(getAttentionFromDto(attentionDto));

        if(!Objects.isNull(attentionDto.getAppointmentId())) {
            appointmentService.updateAppointmentState(attentionDto.getAppointmentId(), Constant.AppointmentState.ATTENDED);
        }
    }

    @Override
    public List<AttentionDto> getAttentionsByHealthPersonnelId(String healthPersonnelId) {
        List<Attention> attentions = attentionRepository.findByHealthPersonnelId(new ObjectId(healthPersonnelId));
        return attentions.stream().map(this::getAttentionDto).collect(Collectors.toList());
    }

    @Override
    public List<AttentionDto> getAttentionsByPatientId(String patientId) {
        List<Attention> attentions = attentionRepository.findByPatientId(new ObjectId(patientId));
        return attentions.stream().map(this::getAttentionDto).collect(Collectors.toList());
    }

    @Override
    public List<AttentionDto> getAttentionsByUserId(String userId) {
        List<Patient> patients = patientRepository.findByUserId(new ObjectId(userId));

        List<Attention> attentions = attentionRepository.findByPatientsId(patients.stream().map(patient -> new ObjectId(patient.getId())).collect(Collectors.toList()));
        return attentions.stream().map(this::getAttentionDto).collect(Collectors.toList());
    }

    @Override
    public Integer getTotalAttentions() {
        return Math.toIntExact(this.attentionRepository.count());
    }


    private AttentionDto getAttentionDto(Attention attention) {
        AttentionDto attentionDto = new AttentionDto();
        BeanUtils.copyProperties(attention, attentionDto);
        TriageDto triageDto = new TriageDto();

        BeanUtils.copyProperties(attention.getTriage(), triageDto);

        attentionDto.setTriage(triageDto);
        attentionDto.setDiagnoses(baseService.getDiagnosesByIds(attention.getDiagnosisId().stream().map(ObjectId::toString).collect(Collectors.toList())));
        attentionDto.setHealthPersonnelId(attention.getHealthPersonnelId().toString());
        attentionDto.setPatient(patientService.getPatientById(attention.getPatientId().toString()));

        attentionDto.setTreatments(attention.getTreatments().stream().map(this::getTreatmentDto).collect(Collectors.toList()));
        attention.setUpdateDate(attention.getUpdateDate());

        return attentionDto;
    }

    private TreatmentDto getTreatmentDto(Treatment treatment) {
        TreatmentDto treatmentDto = new TreatmentDto();
        BeanUtils.copyProperties(treatment, treatmentDto);
        treatmentDto.setDiagnosisId(treatment.getDiagnosisId().toString());
        treatmentDto.setMedicine(baseService.getMedicineById(treatment.getMedicineId().toString()));
        return treatmentDto;
    }

    private Attention getAttentionFromDto(AttentionRqDto attentionDto) {
        Attention attention = new Attention();
        BeanUtils.copyProperties(attentionDto, attention);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        attention.setUpdateDate(LocalDateTime.now().format(formatter));

        Triage triage = new Triage();
        BeanUtils.copyProperties(attentionDto.getTriage(), triage);
        attention.setTriage(triage);
        attention.setPatientId(new ObjectId(attentionDto.getPatientId()));
        attention.setHealthPersonnelId(new ObjectId(attentionDto.getHealthPersonnelId()));
        attention.setDiagnosisId(attentionDto.getDiagnosisId().stream().map(id -> new ObjectId(id)).collect(Collectors.toList()));
        attention.setTreatments(attentionDto.getTreatments().stream().map(this::getTreatment).collect(Collectors.toList()));
        attention.setAppointmentId( !Objects.isNull(attentionDto.getAppointmentId()) ? new ObjectId(attentionDto.getAppointmentId()) : null);
        return attention;
    }

    private Treatment getTreatment(TreatmentRqDto treatmentRqDto) {
        Treatment treatment = new Treatment();
        BeanUtils.copyProperties(treatmentRqDto, treatment);
        treatment.setDiagnosisId(new ObjectId(treatmentRqDto.getDiagnosisId()));
        treatment.setMedicineId(new ObjectId(treatmentRqDto.getMedicineId()));
        return treatment;
    }



}
