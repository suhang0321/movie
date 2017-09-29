package com.suhang.movie.service;

import com.suhang.movie.model.User;

/**
 * @author hang.su
 * @since 2017-04-25 下午8:13
 */
public interface UserService {

    void create(User user);

    void update(User user);

    void delete(Long uid);

    void delete(String username);

    User findById(Long uid);

    User findByUsername(String username);
}
