package com.example.avmed.dto;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
public class MedicineDto {
    private String id;
    private String code;
    private String name;
    private String format;
    private String expirationDate;
}