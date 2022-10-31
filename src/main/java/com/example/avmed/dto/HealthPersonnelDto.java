package com.example.avmed.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class HealthPersonnelDto {
    private String id;
    private String name;
    private String paternalLastName;
    private String maternalLastName;
    private String documentNumber;
    private String signature;
    private String cmp;
    private String serviceType;
    private String userId;

}
