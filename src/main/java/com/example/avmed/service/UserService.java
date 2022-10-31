package com.example.avmed.service;

import com.example.avmed.dto.PatientDto;
import com.example.avmed.dto.ResponseDto;
import com.example.avmed.dto.UserDto;

import java.util.List;

public interface UserService {


    UserDto saveUser(UserDto userDto);
    ResponseDto getUser(String phoneNumber);
}
