package com.api.devtest.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonPropertyOrder(value = {"code", "message"})
public class ApiException extends Exception{

    @JsonProperty("code")
    private String code;

    @JsonProperty("message")
    private String message;

    public ApiException(String code, String message){
        this.code = code;
        this.message = message;
    }
}
