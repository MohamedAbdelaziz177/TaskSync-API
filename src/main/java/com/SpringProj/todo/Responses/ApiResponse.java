package com.SpringProj.todo.Responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class ApiResponse <T>{

    private boolean success;
    private String message = null;
    private T data;


}
