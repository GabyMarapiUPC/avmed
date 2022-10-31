package com.example.avmed.model.attention;

import lombok.Data;
import org.bson.types.ObjectId;

@Data
public class Treatment {
    private ObjectId medicineId;
    private ObjectId diagnosisId;

    private Integer frequencyNumber;
    private String frequencyType;
    private Integer durationNumber;
    private String durationType;
    private Integer dose;
    private String doseUn;
    private Integer quantity;
    private String routeAdministration;
}