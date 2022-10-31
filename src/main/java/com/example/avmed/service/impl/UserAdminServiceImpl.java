package com.example.avmed.service.impl;

import com.example.avmed.dto.HealthPersonnelDto;
import com.example.avmed.dto.ResponseDto;
import com.example.avmed.dto.UserAdminDto;
import com.example.avmed.dto.UserAdminRpDto;
import com.example.avmed.model.HealthPersonnel;
import com.example.avmed.model.UserAdmin;
import com.example.avmed.repository.HealthPersonnelRepository;
import com.example.avmed.repository.UserAdminRepository;
import com.example.avmed.repository.UserRepository;
import com.example.avmed.service.UserAdminService;
import org.bson.types.ObjectId;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Service
public class UserAdminServiceImpl implements UserAdminService {

    @Value("${success.code}")
    private String successCode;

    @Value("${success.message}")
    private String successMessage;

    @Value("${user.not.exists.code}")
    private String userNotExitsCode;

    @Value("${user.not.exists.message}")
    private String userNotExitsMessage;


    @Autowired
    UserAdminRepository userAdminRepository;

    @Autowired
    HealthPersonnelRepository healthPersonnelRepository;


    @Override
    public ResponseDto getUser(UserAdminDto userAdminDto) {
        UserAdmin user = userAdminRepository.findByUsernameAndPassword(userAdminDto.getUsername(), userAdminDto.getPassword());
        UserAdminRpDto userAdminRpDto = null;
        if(!Objects.isNull(user)) {
            userAdminRpDto = new UserAdminRpDto();
            userAdminRpDto.setUserId(user.getId());
            HealthPersonnel healthPersonnel = healthPersonnelRepository.findByUserId(new ObjectId(user.getId()));
            if(!Objects.isNull(healthPersonnel)) {
                userAdminRpDto.setHealthPersonnelId(healthPersonnel.getId());
                userAdminRpDto.setHealthPersonnelName(healthPersonnel.getName()+ ' '+ healthPersonnel.getPaternalLastName());
                userAdminRpDto.setHealthPersonnelSpecialty(healthPersonnel.getSpecialty());
            }
        }

        return  !Objects.isNull(user)
                ? new ResponseDto(successCode, successMessage, userAdminRpDto)
                : new ResponseDto(userNotExitsCode, userNotExitsMessage);

    }

    @Override
    public HealthPersonnelDto getHealthPersonnel(String userId) {
        HealthPersonnel healthPersonnel = healthPersonnelRepository.findByUserId(new ObjectId(userId));
        return getHealthPersonnelDto(healthPersonnel);
    }

    @Override
    public List<HealthPersonnelDto> getAllHealthPersonnel() {
       return healthPersonnelRepository.findAll().stream().map(this::getHealthPersonnelDto).collect(Collectors.toList());
    }

    private HealthPersonnelDto getHealthPersonnelDto(HealthPersonnel healthPersonnel) {
        HealthPersonnelDto healthPersonnelDto = new HealthPersonnelDto();
        BeanUtils.copyProperties(healthPersonnel, healthPersonnelDto);
        return healthPersonnelDto;
    }

}
