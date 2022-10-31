package com.example.avmed.model;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "healthPersonnel")
public class HealthPersonnel {
    @Id
    private String id = new ObjectId().toString();
    private String name;
    private String paternalLastName;
    private String maternalLastName;
    private String documentNumber;
    private String signature;
    private String cmp;
    private String specialty;
    private ObjectId serviceTypeId;
    private ObjectId userId;

}
