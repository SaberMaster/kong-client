package com.i2bgod.kong.exception;

import lombok.Data;

/**
 * @author: Lyn
 * @date: 04/08/2020
 */
@Data
public class KongClientException extends RuntimeException {

    private Integer code;

    private Object error;

    public KongClientException(String message) {
        super(message);
    }

    public KongClientException(String message, Integer code, Object error) {
        super(message);
        this.code = code;
        this.error = error;
    }
}
