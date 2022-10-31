package com.example.avmed.model;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "users" )
public class User {
    @Id
    private String id = new ObjectId().toString();
    private String phoneNumber;
    private String fullName;
    private String documentNumber;
}

