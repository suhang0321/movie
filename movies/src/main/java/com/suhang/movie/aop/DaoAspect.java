package com.suhang.movie.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author hang.su
 * @since 2017-04-25 下午9:26
 */
@Component
@Aspect
public class DaoAspect {

    private static final Logger log = LoggerFactory.getLogger("aop");

    @Pointcut("execution(* com.suhang.movie.dao..*(..))")
    public void pointCut() {
    }

    @AfterThrowing(pointcut = "pointCut()", throwing = "e")
    public void afterThrowing(JoinPoint joinPoint, Exception e) {
        if (log.isInfoEnabled()) {
            log.info("afterThrowing " + joinPoint + "\t" + e);
        }
    }
}
