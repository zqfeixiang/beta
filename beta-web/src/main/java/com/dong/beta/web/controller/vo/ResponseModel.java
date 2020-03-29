package com.dong.beta.web.controller.vo;

import constants.ResultCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Data
public class ResponseModel<T> {
    private Integer code;
    private String message;
    private T data;

    public ResponseModel(ResultCodeEnum responseCode){
        setResponse(responseCode);
    }

    public ResponseModel(ResultCodeEnum responseCode, T data){
        setResponse(responseCode, data);
    }

    public static <T>ResponseModel successResponse(T data){
        return new ResponseModel(ResultCodeEnum.SUCCESS, data);
    }

    public void setResponse(ResultCodeEnum responseCode){
        this.code = responseCode.getCode();
        this.message = responseCode.getMessage();
    }

    public void setResponse(ResultCodeEnum responseCode, T data){
        this.code = responseCode.getCode();
        this.message = responseCode.getMessage();
        this.data = data;
    }
}
