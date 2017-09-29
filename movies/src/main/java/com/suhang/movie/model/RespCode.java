package com.suhang.movie.model;

/**
 * @author hang.su
 * @since 2017-04-25 下午8:16
 */
public enum RespCode {

    OK(0, "ok"),

    INCORRECT_ACCOUNT_OR_PASSWORD(-101, "incorrect account or password"),
    LOCKED_ACCOUNT(-102, "locked account"),
    RETRY_LIMITED(-103, "retry limited"),
    //未经过认证
    UNAUTHENTICATED(-104, "unauthenticated"),
    //未经授权的
    UNAUTHORIZED(-105, "unauthorized"),
    USER_ALREADY_LOGIN(-106, "user already login"),
    //未知的认证错误
    UNKNOWN_AUTHENTICATION_ERROR(-107, "unknown authentication error"),

    WRONG_ARGUMENT(-400, "wrong argument"),
    SERVER_ERROR(-500, "server error"),

    FAILED_TO_UPDATE(-600, "failed to update"),

    USERNAME_ALREADY_EXISTS(-1001, "username already exists"),
    USER_NOT_EXISTS(-1002, "user not exists"),

    MOVIE_NOT_EXISTS(-1101, "movie not exists"),
    MOVIE_ALREADY_IN_FAVORITES(-1103, "movie already in favorites"),
    MOVIE_NOT_IN_FAVORITES(-1102, "movie not in favorites");

    public int code;
    public String message;

    RespCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
