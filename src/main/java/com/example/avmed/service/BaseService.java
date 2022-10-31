package com.example.avmed.service;

import com.example.avmed.dto.DiagnosisDto;
import com.example.avmed.dto.MedicineDto;
import com.example.avmed.dto.ReportDto;
import org.bson.types.ObjectId;

import java.util.List;

public interface BaseService {
    List<DiagnosisDto> getDiagnoses();
    List<MedicineDto> getMedicines();
    List<DiagnosisDto> getDiagnosesByIds(List<String> diagnosesIds);
    MedicineDto getMedicineById(String medicineId);

    String getVerificationCode(String phoneNumber);
}
