package com.suhang.movie.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.session.SessionException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author hang.su
 * @since 2017-04-25 下午8:39
 */
public class ExtendLogoutFilter extends LogoutFilter {

    private static final Logger log = LoggerFactory.getLogger(ExtendLogoutFilter.class);

    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        Subject subject = getSubject(request, response);
        try {
            subject.logout();
        } catch (SessionException e) {
            if (log.isTraceEnabled()) {
                log.trace("failed to logout", e);
            }
        }
        String redirectUrl = getRedirectUrl(request, response, subject);
        issueRedirect(request, response, redirectUrl);
        return false;
    }
}
