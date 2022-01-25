package com.prealkemy.disney.exception;

@SuppressWarnings("serial")
public class ParamNotFound extends RuntimeException{
    public ParamNotFound(String msg) {
        super(msg);
    }
}
