package com.example.avmed.model.appointment;

import lombok.Data;

@Data
public class SegmentHour {
    private String date;
    private String startTime;
    private String endTime;
    private Boolean enabled;
}