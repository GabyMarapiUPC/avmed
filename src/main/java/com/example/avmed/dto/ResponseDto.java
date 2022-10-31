package com.example.avmed.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseDto<T> {

    private String code;
    private String description;
    private T data;

    public ResponseDto(String code, String description) {
        this.code = code;
        this.description = description;
    }
    public ResponseDto(String code, String description, T data) {
        this.code = code;
        this.description = description;
        this.data = data;
    }
}
