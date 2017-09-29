package com.suhang.movie.util;

import com.suhang.movie.exception.ServiceException;
import com.suhang.movie.model.RespCode;

/**
 * @author hang.su
 * @since 2017-04-25 下午8:20
 */
public final class CheckUtil {

    public static void checkArgument(boolean condition, String message) {
        if (!condition) {
            throw new ServiceException(RespCode.WRONG_ARGUMENT, message == null ? RespCode.WRONG_ARGUMENT.message : message);
        }
    }

    public static void checkState(boolean condition, RespCode respCode) {
        if (!condition) {
            throw new ServiceException(respCode);
        }
    }

    public static void checkState(boolean condition, RespCode respCode, String message) {
        if (!condition) {
            throw new ServiceException(respCode, message);
        }
    }
}
