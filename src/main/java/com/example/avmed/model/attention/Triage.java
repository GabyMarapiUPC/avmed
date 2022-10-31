package com.example.avmed.model.attention;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
public class Triage {

    //triage
    private String bloodPressure;
    private String heartRate;
    private String temperature;
    private String breathingFrequency;
    private String weight;
    private String height;

}