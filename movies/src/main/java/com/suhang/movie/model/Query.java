package com.suhang.movie.model;

import java.io.Serializable;

/**
 * @author hang.su
 * @since 2017-04-26 上午9:49
 */
public class Query implements Serializable {

    public static final int MAX_LIMIT = 20;
    
    private static final long serialVersionUID = -1806605805704730149L;
    private Long lastId;
    private Integer limit;

    public void ensureNotExceedMaxLimit() {
        if (exceedMaxLimit()) {
            setToMaxLimit();
        }
    }

    public boolean exceedMaxLimit() {
        return limit > MAX_LIMIT;
    }

    public void setToMaxLimit() {
        limit = MAX_LIMIT;
    }

    public boolean lastIdValid() {
        return lastId != null && lastId >= 0L;
    }

    public boolean limitValid() {
        return limit != null && limit > 0;
    }

    public Long getLastId() {
        return lastId;
    }

    public void setLastId(Long lastId) {
        this.lastId = lastId;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }
}
