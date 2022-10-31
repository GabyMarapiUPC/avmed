package com.example.avmed.dto;

import lombok.Data;


@Data
public class UserAdminRpDto {
    private String userId;
    private String healthPersonnelId;
    private String healthPersonnelName;
    private String healthPersonnelSpecialty;
}