package com.lamine.InventoryManagement.handlers;

import com.lamine.InventoryManagement.exception.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ErrorDto {

    private Integer httpCode;
    private ErrorCode errorCode;
    private String message;
    private List<String> errors;
}
