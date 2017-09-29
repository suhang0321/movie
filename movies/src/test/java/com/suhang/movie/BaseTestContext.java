package com.suhang.movie;

import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author hang.su
 * @since 2017-04-25 下午8:50
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:/spring/spring-test.xml"})
@Transactional(transactionManager = "transactionManager")
@Rollback
public abstract class BaseTestContext extends MockContext {
    
}

