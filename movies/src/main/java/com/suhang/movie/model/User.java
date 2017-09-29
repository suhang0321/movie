package com.suhang.movie.model;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author hang.su
 * @since 2017-04-25 下午7:59
 */
public class User implements Serializable {

    private static final long serialVersionUID = -2728364225017596124L;

    private Long id;
    private String username;
    @JsonIgnore
    private String password;
    @JsonIgnore
    private String salt;
    private Integer status;
    private Date ctime;
    private Date mtime;

    public static Builder builder() {
        return new Builder();
    }

    public boolean identifiable() {
        return id != null || username != null;
    }

    public boolean statusValid() {
        return status == null || BinaryStatus.of(status) != null;
    }

    @JsonIgnore
    public String getCredentialsSalt() {
        return username + salt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
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

    @Override
    public String toString() {
        return "User{" +
            "id=" + id +
            ", username='" + username + '\'' +
            ", password='" + password + '\'' +
            ", salt='" + salt + '\'' +
            ", status=" + status +
            ", ctime=" + ctime +
            ", mtime=" + mtime +
            '}';
    }

    public static class Builder {

        private Long id;
        private String username;
        private String password;
        private String salt;
        private Integer status;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }


        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder salt(String salt) {
            this.salt = salt;
            return this;
        }

        public Builder status(Integer status) {
            this.status = status;
            return this;
        }

        public User build() {
            User user = new User();
            user.setId(id);
            user.setUsername(username);
            user.setPassword(password);
            user.setSalt(salt);
            user.setStatus(status);
            return user;
        }
    }
}

