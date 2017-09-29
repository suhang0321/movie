package com.suhang.movie.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author hang.su
 * @since 2017-04-25 下午9:46
 */
public class Favorite implements Serializable {

    private static final long serialVersionUID = -1024537387690046065L;

    private Long userId;
    private Long movieId;
    private Integer status;
    private Date ctime;
    private Date mtime;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public Date getMtime() {
        return mtime;
    }

    public void setMtime(Date mtime) {
        this.mtime = mtime;
    }
}
