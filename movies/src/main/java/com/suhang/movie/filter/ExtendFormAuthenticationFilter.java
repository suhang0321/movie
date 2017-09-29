package com.suhang.movie.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author hang.su
 * @since 2017-04-25 下午8:38
 */
public class ExtendFormAuthenticationFilter extends FormAuthenticationFilter {

    private static final Logger log = LoggerFactory.getLogger(ExtendFormAuthenticationFilter.class);
    private String unauthenticatedUrl;

    public String getUnauthenticatedUrl() {
        return unauthenticatedUrl;
    }

    public void setUnauthenticatedUrl(String unauthenticatedUrl) {
        this.unauthenticatedUrl = unauthenticatedUrl;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        if (isLoginRequest(request, response)) {
            return true;
        } else {
            WebUtils.issueRedirect(request, response, unauthenticatedUrl);
            return false;
        }
    }
}
