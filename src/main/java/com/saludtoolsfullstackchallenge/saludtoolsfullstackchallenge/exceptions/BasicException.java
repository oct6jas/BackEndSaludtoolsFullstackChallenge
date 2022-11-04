package com.saludtoolsfullstackchallenge.saludtoolsfullstackchallenge.exceptions;

public class BasicException extends Exception{

    private Integer code;

    public BasicException(Integer code, String errorMessage) {
        super(errorMessage);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }


}
