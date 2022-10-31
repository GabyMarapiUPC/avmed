package com.example.avmed.service;

import com.example.avmed.dto.HealthPersonnelDto;
import com.example.avmed.dto.ResponseDto;
import com.example.avmed.dto.UserAdminDto;

import java.util.List;

public interface UserAdminService {
    ResponseDto getUser(UserAdminDto userAdminDto);
    HealthPersonnelDto getHealthPersonnel(String userId);
    List<HealthPersonnelDto> getAllHealthPersonnel();

}
