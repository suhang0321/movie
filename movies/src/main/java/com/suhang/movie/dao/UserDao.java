package com.suhang.movie.dao;

import com.suhang.movie.model.User;

/**
 * @author hang.su
 * @since 2017-04-25 下午7:58
 */
public interface UserDao {

    int create(User user);

    int update(User user);

    int delete(Long uid);

    User findById(Long id);

    User findByUsername(String username);
}
