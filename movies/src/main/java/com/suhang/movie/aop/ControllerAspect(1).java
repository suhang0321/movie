package com.suhang.movie.aop;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.suhang.movie.exception.ServiceException;
import com.suhang.movie.model.Resp;

/**
 * @author hang.su
 * @since 2017-04-25 下午8:41
 */
@Component
@Aspect
public class ControllerAspect {

    private static final Logger log = LoggerFactory.getLogger("aop");

    private static String getRequestMethod() {
        HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return req.getMethod();
    }

    private static String getRequestUrl() {
        HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        StringBuffer url = req.getRequestURL();
        if (req.getQueryString() != null) {
            url.append("?").append(req.getQueryString());
        }
        return url.toString();
    }

    @Pointcut("execution(* com.suhang.movie.http..*(..))")
    public void pointCut() {
    }

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint pjp) {
        long start = System.currentTimeMillis();
        Object obj;
        long end;
        try {
            obj = pjp.proceed();
            end = System.currentTimeMillis();
            if (log.isInfoEnabled()) {
                log.info("around " + pjp + "\tUse time : " + (end - start) + " ms!");
            }
        } catch (ServiceException e) {
            end = System.currentTimeMillis();
            if (log.isInfoEnabled()) {
                log.info("around " + pjp + "\tUse time : " + (end - start) + " ms!");
            }
            obj = Resp.builder().code(e.getCode())
                .message(e.getMessage()).build();
        } catch (Throwable e) {
            end = System.currentTimeMillis();
            if (log.isInfoEnabled()) {
                log.info("around " + pjp + "\tUse time : " + (end - start) + " ms with exception: " + e);
            }
            obj = Resp.SERVER_ERROR;
        }
        log.info("api: " + getRequestMethod() + " " + getRequestUrl() + ", resp: " + obj);
        return obj;
    }
}
