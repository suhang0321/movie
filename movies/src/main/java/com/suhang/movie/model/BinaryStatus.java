package com.suhang.movie.model;

/**
 * @author hang.su
 * @since 2017-04-25 下午8:02
 */
public enum BinaryStatus {

    OK(0, "ok"),
    DELETED(1, "deleted"),;

    public int code;
    public String desc;

    BinaryStatus(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static BinaryStatus of(Integer code) {
        if (code == null) {
            return null;
        }
        for (BinaryStatus binaryStatus : BinaryStatus.values()) {
            if (binaryStatus.code == code) {
                return binaryStatus;
            }
        }
        return null;
    }
}
