package com.suhang.movie.exception;

import com.suhang.movie.model.RespCode;

/**
 * @author hang.su
 * @since 2017-04-25 下午8:17
 */
public class ServiceException extends RuntimeException {

    private int code;

    public ServiceException(RespCode respCode, String message) {
        super(message);
        this.code = respCode.code;
    }

    public ServiceException(RespCode respCode) {
        this(respCode, respCode.message);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
