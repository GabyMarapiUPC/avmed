package com.example.avmed.service.impl;

import com.example.avmed.dto.DiagnosisDto;
import com.example.avmed.dto.MedicineDto;
import com.example.avmed.model.attention.*;
import com.example.avmed.repository.DiagnosisRepository;
import com.example.avmed.repository.MedicineRepository;
import com.example.avmed.service.BaseService;
import com.example.avmed.util.GeneratorUtil;
import org.bson.types.ObjectId;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BaseServiceImpl implements BaseService {

    @Autowired
    DiagnosisRepository diagnosisRepository;

    @Autowired
    MedicineRepository medicineRepository;



    @Override
    public List<DiagnosisDto> getDiagnoses() {
        return diagnosisRepository.findAll().stream().map(this::getDiagnosisDto).collect(Collectors.toList());
    }

    @Override
    public List<DiagnosisDto> getDiagnosesByIds(List<String> diagnosesIds) {
        return diagnosisRepository.findByDiagnosesIds(diagnosesIds.stream().map(d->new ObjectId(d)).collect(Collectors.toList()))
                .stream()
                .map(this::getDiagnosisDto).collect(Collectors.toList());
    }

    @Override
    public List<MedicineDto> getMedicines() {
        return medicineRepository.findAll().stream().map(this::getMedicineDto).collect(Collectors.toList());
    }

    @Override
    public MedicineDto getMedicineById(String medicineId) {
        return medicineRepository.findById(medicineId)
                .stream()
                .map(this::getMedicineDto).findFirst().orElse(null);
    }

    @Override
    public String getVerificationCode(String phoneNumber) {
        String otp = GeneratorUtil.generateRandom();
        return otp;
    }

    private DiagnosisDto getDiagnosisDto(Diagnosis diagnosis) {
        DiagnosisDto diagnosisDto = new DiagnosisDto();
        BeanUtils.copyProperties(diagnosis, diagnosisDto);
        return diagnosisDto;
    }

    private MedicineDto getMedicineDto(Medicine medicine) {
        MedicineDto medicineDto = new MedicineDto();
        BeanUtils.copyProperties(medicine, medicineDto);
        return medicineDto;
    }


}
