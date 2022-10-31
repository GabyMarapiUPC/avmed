package com.example.avmed.model.attention;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "diagnosis" )
public class Diagnosis {
    @Id
    private String id = new ObjectId().toString();
    private String code;
    private String name;
}