package com.suhang.movie.service;

import static com.suhang.movie.util.CheckUtil.checkArgument;
import static com.suhang.movie.util.CheckUtil.checkState;
import static com.suhang.movie.util.Validator.checkPassword;
import static com.suhang.movie.util.Validator.checkUserId;
import static com.suhang.movie.util.Validator.checkUsername;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.suhang.movie.dao.UserDao;
import com.suhang.movie.model.BinaryStatus;
import com.suhang.movie.model.RespCode;
import com.suhang.movie.model.User;
import com.suhang.movie.util.PasswordUtil;

/**
 * @author hang.su
 * @since 2017-04-25 下午8:54
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Override
    public void create(User user) {
        checkUsername(user);
        checkPassword(user);
        checkState(findByUsername(user.getUsername()) == null, RespCode.USERNAME_ALREADY_EXISTS);
        PasswordUtil.encryptPassword(user);
        int res = userDao.create(user);
        checkState(res > 0, RespCode.FAILED_TO_UPDATE);
    }

    @Override
    public void update(User user) {
        checkArgument(user.identifiable(), "user id or username cannot be both null");
        User u;
        if (user.getId() != null) {
            checkUserId(user.getId());
            u = findById(user.getId());
        } else {
            checkUsername(user);
            u = findByUsername(user.getUsername());
        }
        checkArgument(user.getPassword() != null || user.getStatus() != null, "nothing to update, now supports status and password update");
        checkArgument(user.statusValid(), "invalid user status");
        if (BinaryStatus.OK != BinaryStatus.of(user.getStatus())) {
            checkState(u != null, RespCode.USER_NOT_EXISTS);
        }
        if (user.getPassword() != null) {
            checkPassword(user);
            PasswordUtil.encryptPassword(user);
        }
        int res = userDao.update(user);
        checkState(res > 0, RespCode.FAILED_TO_UPDATE);
    }

    @Override
    public void delete(Long id) {
        checkUserId(id);
        checkState(findById(id) != null, RespCode.USER_NOT_EXISTS);
        int res = userDao.delete(id);
        checkState(res > 0, RespCode.FAILED_TO_UPDATE);
    }

    @Override
    public void delete(String username) {
        checkUsername(username);
        User user = findByUsername(username);
        checkState(user != null, RespCode.USER_NOT_EXISTS);
        int res = userDao.delete(user.getId());
        checkState(res > 0, RespCode.FAILED_TO_UPDATE);
    }

    @Override
    public User findById(Long id) {
        checkUserId(id);
        return userDao.findById(id);
    }

    @Override
    public User findByUsername(String username) {
        checkUsername(username);
        return userDao.findByUsername(username);
    }
}
