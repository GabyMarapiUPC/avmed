package com.example.avmed.model.master;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "serviceTypes" )
public class ServicesType {
    @Id
    private ObjectId _id;
    private String serviceType;
}