package com.example.avmed.service.impl;

import com.example.avmed.dto.*;
import com.example.avmed.model.HealthPersonnel;
import com.example.avmed.model.Patient;
import com.example.avmed.model.User;
import com.example.avmed.model.UserAdmin;
import com.example.avmed.repository.UserRepository;
import com.example.avmed.service.UserService;
import com.example.avmed.util.Constant;
import com.example.avmed.util.DateUtil;
import org.bson.types.ObjectId;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;


@Service
public class UserServiceImpl implements UserService {
    @Value("${success.code}")
    private String successCode;

    @Value("${success.message}")
    private String successMessage;

    @Value("${user.not.exists1.code}")
    private String userNotExitsCode;

    @Value("${user.not.exists.message}")
    private String userNotExitsMessage;
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDto saveUser(UserDto userDto) {
        User user;
        if(!Objects.isNull(userDto.getId())) {
            user = userRepository.findByPhoneNumber(userDto.getPhoneNumber());
            user = userRepository.save(getUserEntity(userDto, user));
        } else {
            user = userRepository.save(getUserEntity(userDto, new User()));

        }
        BeanUtils.copyProperties(user, userDto);

        return userDto;
    }

    @Override
    public ResponseDto getUser(String phoneNumber) {
        User user = userRepository.findByPhoneNumber(phoneNumber);
        UserDto userDto = null;
        if(!Objects.isNull(user)) {
            userDto = new UserDto();
            userDto.setId(user.getId());
            userDto.setFullName(user.getFullName());
            userDto.setDocumentNumber(user.getDocumentNumber());
        }

        return  !Objects.isNull(user)
                ? new ResponseDto(successCode, successMessage, userDto)
                : new ResponseDto(userNotExitsCode, userNotExitsMessage);

    }

    private User getUserEntity(UserDto userDto, User user) {

        user.setFullName(userDto.getFullName());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setDocumentNumber(userDto.getDocumentNumber());


        return user;
    }



}
