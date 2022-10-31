package com.example.avmed.service.impl;

import com.example.avmed.dto.*;
import com.example.avmed.model.Patient;
import com.example.avmed.model.User;
import com.example.avmed.repository.PatientRepository;
import com.example.avmed.service.PatientService;
import com.example.avmed.util.Constant;
import com.example.avmed.util.DateUtil;
import org.bson.types.ObjectId;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class PatientServiceImpl implements PatientService {
    @Value("${success.code}")
    private String successCode;

    @Value("${success.message}")
    private String successMessage;

    @Value("${patient.error.code}")
    private String patientErrorCode;

    @Value("${patient.error.not.exit.message}")
    private String patientErrorNotExitMessage;

    @Value("${patient.error.documentNumber.message}")
    private String patientErrorDocumentNumberMessage;

    @Value("${patient.error.insuredCode.message}")
    private String patientErrorInsuredCodeMessage;

    @Value("${patient.success.message}")
    private String patientSuccessMessage;

    @Value("${patient.error.clinicalHistoryNumber.message}")
    private String patientErrorClinicalHistoryNumberMessage;

    @Autowired
    PatientRepository patientRepository;

    @Override
    public ResponseDto registerPatient(PatientDto patientDto) {
        Patient patient;
        //register
        if(Objects.isNull(patientDto.getId())){
            patient = patientRepository.findByDocumentNumber(patientDto.getDocumentNumber());

            if(!Objects.isNull(patient)) {
                return new ResponseDto(patientErrorCode, patientErrorDocumentNumberMessage);
            }

            patient = patientRepository.findByInsuredCode(patientDto.getInsuredCode());

            if(!Objects.isNull(patient)) {
                return new ResponseDto(patientErrorCode, patientErrorInsuredCodeMessage);
            }

            patient = patientRepository.findByClinicalHistoryNumber(patientDto.getDocumentNumber());

            if(!Objects.isNull(patient)) {
                return new ResponseDto(patientErrorCode, patientErrorClinicalHistoryNumberMessage);
            }
            patientRepository.save(getPatientEntity(patientDto, new Patient()));
            return new ResponseDto(successCode, successMessage);

        } else {
            patient = patientRepository.findById(patientDto.getId()).stream().findFirst().orElse(null);
            if( !Objects.isNull(patient)) {
                patientRepository.save(getPatientEntity(patientDto, patient));
                return new ResponseDto(successCode, successMessage);
            }

        }

        return null;
    }

    @Override
    public List<PatientDto> getPatients(String userId, String documentNumber, String insuredCode) {
        List<Patient> patients = new ArrayList<>();
        if (!Objects.isNull(userId)) {
            patients = patientRepository.findByUserId(new ObjectId(userId));
        } else if (Objects.isNull(documentNumber) && Objects.isNull(insuredCode)) {
            patients = patientRepository.findAll();
        } else if (!Objects.isNull(documentNumber) || !Objects.isNull(insuredCode) ) {
            patients = patientRepository.getPatientsByDocumentNumberOrInsuredCode(documentNumber, insuredCode);
        }

        return  patients.stream().map(this::getPatientDto).collect(Collectors.toList());
    }

    @Override
    public PatientDto getPatientById(String patientId) {
        return getPatientDto(patientRepository.findById(patientId).stream().findFirst().orElse(null));
    }


    @Override
    public Integer getTotalPatients() {
        return Math.toIntExact(this.patientRepository.count());
    }

    @Override
    public ResponseDto associateUserToPatient(AssociatePatientDto associatePatientDto) {

        Patient patient = patientRepository.findByDocumentNumberAndInsuredCode(associatePatientDto.getDocumentNumber(), associatePatientDto.getInsuredCode());
        if (!Objects.isNull(patient)) {
            patient.setUserId(new ObjectId(associatePatientDto.getUserId()));
            patientRepository.save(patient);
            return new ResponseDto(successCode, patientSuccessMessage);
        } else {
            return new ResponseDto(patientErrorCode, patientErrorNotExitMessage);
        }
    }
    private PatientDto getPatientDto(Patient patient) {
        PatientDto patientDto = new PatientDto();
        BeanUtils.copyProperties(patient, patientDto);

        patientDto.setUserId(!Objects.isNull(patient.getUserId()) ? patient.getUserId().toString() : null);
        return patientDto;
    }

    private Patient getPatientEntity(PatientDto patientDto,
                                     Patient patient) {

        patient.setName(patientDto.getName());
        patient.setPaternalLastName(patientDto.getPaternalLastName());
        patient.setMaternalLastName(patientDto.getMaternalLastName());
        patient.setDocumentNumber(patientDto.getDocumentNumber());
        patient.setSex(patientDto.getSex());
        patient.setBirthDate(patientDto.getBirthDate());
        patient.setAge(DateUtil.calculateAge(LocalDate.parse(patientDto.getBirthDate())));
        patient.setDocumentType(Constant.DEF_DOCUMENT_TYPE);

        if(!Objects.isNull(patientDto.getClinicalHistoryNumber())
                && !Objects.isNull(patientDto.getInsuredCode()) ) {
            patient.setClinicalHistoryNumber(patientDto.getClinicalHistoryNumber());
            patient.setInsuredCode(patientDto.getInsuredCode());
        }

        if(!Objects.isNull(patientDto.getUserId())) {
            patient.setUserId(new ObjectId(patientDto.getUserId()));
        }


        return patient;
    }
}
