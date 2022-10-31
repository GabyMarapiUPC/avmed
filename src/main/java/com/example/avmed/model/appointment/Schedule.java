package com.example.avmed.model.appointment;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "schedules" )
public class Schedule {
    @Id
    private ObjectId _id;
    private String healthPersonnelId;
    private List<Schedule> scheduleList;
}