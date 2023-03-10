package com.coder.ws.dto;

import lombok.Builder;

@Builder
public class BookResponseDTO {
    private Long code;
    private String message;
    private Object data;
    public BookResponseDTO(Long code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
    public Long getCode() {
        return code;
    }
    public void setCode(Long code) {
        this.code = code;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public Object getData() {
        return data;
    }
    public void setData(Object data) {
        this.data = data;
    }
}
