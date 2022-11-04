package com.saludtoolsfullstackchallenge.saludtoolsfullstackchallenge.exceptions;

import lombok.Data;

@Data
public class BasicExceptionDto {

    private Integer code;
    private String menssage;

    public BasicExceptionDto() {
    }

    public BasicExceptionDto(Integer code, String menssage) {
        this.code = code;
        this.menssage = menssage;
    }
}
