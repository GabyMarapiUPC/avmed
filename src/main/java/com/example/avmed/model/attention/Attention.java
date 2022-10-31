package com.example.avmed.model.attention;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Document(collection = "attentions" )
public class Attention {

    @Id
    private String id = new ObjectId().toString();
    private String updateDate;
    private String patientReport;
    private Triage triage;
    private List<ObjectId> diagnosisId;
    private String recommendations;
    private List<Treatment> treatments;
    private String expirationDate;


    private ObjectId patientId;
    private ObjectId healthPersonnelId;
    private ObjectId appointmentId;

}