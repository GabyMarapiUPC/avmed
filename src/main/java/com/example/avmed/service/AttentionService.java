package com.example.avmed.service;

import com.example.avmed.dto.AttentionDto;
import com.example.avmed.dto.AttentionRqDto;

import java.util.List;

public interface AttentionService {

    void registerAttention(AttentionRqDto attentionDto);
    List<AttentionDto> getAttentionsByHealthPersonnelId(String healthPersonnelId);
    List<AttentionDto> getAttentionsByPatientId(String patientId);
    List<AttentionDto> getAttentionsByUserId(String userId);

    Integer getTotalAttentions();
}
