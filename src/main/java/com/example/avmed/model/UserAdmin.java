package com.example.avmed.model;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "usersAdmin" )
public class UserAdmin {
    @Id
    private String id = new ObjectId().toString();
    private String username;
    private String password;
}

