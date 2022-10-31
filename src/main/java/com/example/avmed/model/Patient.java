package com.example.avmed.model;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "patients" )
public class Patient {
    @Id
    private String id = new ObjectId().toString();
    private String name;
    private String paternalLastName;
    private String maternalLastName;
    private String documentType;
    private String documentNumber;
    private String birthDate;
    private Integer age;
    private String sex;
    //private String department;
    //private String province;
    //private String district;
    private String insuredCode; //codigo asegurado
    private String clinicalHistoryNumber; //numero historia clinica
    private ObjectId userId;

}