package com.dfcu.staffRetrieval.models;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString

public class RetrievalResponse {

    private int statusCode;
    private String message;
    private Object data;

//    public RetrievalResponse(int statusCode, String message, Object data) {
//        this.statusCode = statusCode;
//        this.message = message;
//
//    }
}
