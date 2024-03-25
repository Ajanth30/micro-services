package com.microservice.bookinventoryservice.dto;

import com.microservice.bookinventoryservice.common.ResponseEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response {
    private ResponseEnum response;
    private Object data;


    public static Response  success(Object data){
        return new Response(ResponseEnum.SUCCESS,data);
    }

    public static Response failure(Object data){
        return new Response(ResponseEnum.FAILED,data);
    }
}
