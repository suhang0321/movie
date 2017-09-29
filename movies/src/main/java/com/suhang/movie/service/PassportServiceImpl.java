package com.suhang.movie.service;

import static com.suhang.movie.util.CheckUtil.checkState;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.suhang.movie.exception.ServiceException;
import com.suhang.movie.model.RespCode;

/**
 * @author hang.su
 * @since 2017-04-25 下午8:19
 */
@Service("passportService")
public class PassportServiceImpl implements PassportService {

    private static final Logger log = LoggerFactory.getLogger(PassportServiceImpl.class);

    @Override
    public void login(String username, String password, boolean rememberMe) {
        Subject subject = SecurityUtils.getSubject();
        checkState(!subject.isAuthenticated(), RespCode.USER_ALREADY_LOGIN);
        UsernamePasswordToken token = new UsernamePasswordToken(username,
            password);
        token.setRememberMe(rememberMe);
        try {
            subject.login(token);
        } catch (UnknownAccountException | IncorrectCredentialsException e) {
            throw new ServiceException(RespCode.INCORRECT_ACCOUNT_OR_PASSWORD);
        } catch (LockedAccountException e) {
            throw new ServiceException(RespCode.LOCKED_ACCOUNT);
        } catch (ExcessiveAttemptsException e) {
            throw new ServiceException(RespCode.RETRY_LIMITED, e.getMessage());
        } catch (AuthenticationException e) {
            if (log.isTraceEnabled()) {
                log.trace("unknown authentication error", e);
            }
            throw new ServiceException(RespCode.UNKNOWN_AUTHENTICATION_ERROR);
        }
    }
}
