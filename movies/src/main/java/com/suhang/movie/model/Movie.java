package com.suhang.movie.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author hang.su
 * @since 2017-04-25 下午9:38
 */
public class Movie implements Serializable {

    private static final long serialVersionUID = -6467288255890539355L;

    private Long id;
    private String name;
    private String description;
    private Integer status;
    private Date ctime;
    private Date mtime;

    public static Builder builder() {
        return new Builder();
    }

    public boolean statusValid() {
        return status == null || BinaryStatus.of(status) != null;
    }

    @Override
    public String toString() {
        return "Movie{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", description='" + description + '\'' +
            ", status=" + status +
            ", ctime=" + ctime +
            ", mtime=" + mtime +
            '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public static class Builder {

        private Long id;
        private String name;
        private String description;
        private Integer status;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }


        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder status(Integer status) {
            this.status = status;
            return this;
        }

        public Movie build() {
            Movie movie = new Movie();
            movie.setId(id);
            movie.setName(name);
            movie.setDescription(description);
            movie.setStatus(status);
            return movie;
        }
    }
}
