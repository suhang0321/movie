package com.suhang.movie.util;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.suhang.movie.model.User;

/**
 * @author hang.su
 * @since 2017-04-26 上午10:16
 */
public final class LoginUtil {

    private static final Logger log = LoggerFactory.getLogger(LoginUtil.class);

    public static Long getLoginUserId() {
        User user = getLoginUser();
        return user == null ? null : user.getId();
    }

    public static String getLoginUsername() {
        User user = getLoginUser();
        return user == null ? null : user.getUsername();
    }

    public static User getLoginUser() {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            return (User) subject.getPrincipal();
        }
        return null;
    }

    public static boolean login(HttpServletRequest request) {
        if ("GET".equalsIgnoreCase(request.getMethod())) {
            return false;
        }
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        // TODO check username and password validation
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            if (log.isTraceEnabled()) {
                log.trace("failed to login", e);
            }
        }
        return true;
    }
}
