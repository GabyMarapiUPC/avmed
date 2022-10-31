package com.example.avmed.rest;

import com.example.avmed.dto.*;
import com.example.avmed.service.UserAdminService;
import com.example.avmed.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@Slf4j
public class UserRest {

    @Autowired
    UserAdminService userAdminService;

    @Autowired
    UserService userService;

    //user admin
    @PostMapping("/user")
    public ResponseDto getUser(@RequestBody UserAdminDto userAdminDto) {
        log.info("[POST] START getUser");
        ResponseDto responseDto = userAdminService.getUser(userAdminDto);
        log.info("[POST] END getUser");
        return responseDto;
    }

    //healthPersonnel
    @GetMapping("/healthPersonnel/{userId}")
    public ResponseEntity<HealthPersonnelDto> getHealthPersonnel(@PathVariable("userId") String userId) {
        log.info("[POST] START getUser");
        HealthPersonnelDto healthPersonnel = userAdminService.getHealthPersonnel(userId);
        log.info("[POST] END getUser");
        return new ResponseEntity<>(healthPersonnel, HttpStatus.OK);
    }

    @GetMapping("/healthPersonnel")
    public ResponseEntity<List<HealthPersonnelDto>> getAllHealthPersonnel() {
        log.info("[POST] START getUser");
        List<HealthPersonnelDto> healthPersonnelDtos = userAdminService.getAllHealthPersonnel();
        log.info("[POST] END getUser");
        return new ResponseEntity<>(healthPersonnelDtos, HttpStatus.OK);
    }


    //user

    @GetMapping("/user/{phoneNumber}")
    public ResponseDto getUserByPhoneNumber(@PathVariable("phoneNumber") String phoneNumber) {
        log.info("[POST] START getUser");
        ResponseDto responseDto = userService.getUser(phoneNumber);
        log.info("[POST] END getUser");
        return responseDto;
    }


    //register user
    @PostMapping("/register-user")
    public ResponseEntity registerUser(@RequestBody UserDto userDto) {
        log.info("[POST] START registerUser");
        UserDto user = userService.saveUser(userDto);
        log.info("[POST] END registerUser");
        return new ResponseEntity<UserDto>(user, HttpStatus.OK);

    }

}
