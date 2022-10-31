package com.example.avmed.model.appointment;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "appointments" )
public class Appointment {
    @Id
    private String id = new ObjectId().toString();
    private String day;
    private String startHour;
    private String endHour;
    //private String codeQR;
    private String state;
    private ObjectId patientId;
    private ObjectId healthPersonnelId;
}