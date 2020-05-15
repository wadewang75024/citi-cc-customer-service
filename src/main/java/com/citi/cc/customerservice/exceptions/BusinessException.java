package com.citi.cc.customerservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.INTERNAL_SERVER_ERROR)
@SuppressWarnings(value="serial")
public class BusinessException extends RuntimeException {

}
