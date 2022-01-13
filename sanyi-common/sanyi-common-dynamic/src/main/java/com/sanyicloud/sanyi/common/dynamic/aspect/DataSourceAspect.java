package com.sanyicloud.sanyi.common.dynamic.aspect;

import com.sanyicloud.sanyi.common.dynamic.datasource.DataSourceHolder;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Aspect
@Component
public class DataSourceAspect {

    @Resource
    private DataSourceHolder dataSourceHolder;

    /**
     * 写切入点
     */
    @Pointcut("@annotation(com.sanyicloud.sanyi.common.dynamic.annotation.Write) " +
            "|| !@annotation(org.springframework.web.bind.annotation.GetMapping) ")
    public void writePointcut() {
    }

    /**
     * 读切入点
     */
    @Pointcut("@annotation(com.sanyicloud.sanyi.common.dynamic.annotation.Read)" +
            "|| @annotation(org.springframework.web.bind.annotation.GetMapping)")
    public void readPointcut() {
    }

    @Before("writePointcut()")
    public void setWriteDataSource() {
        dataSourceHolder.setMaster();
    }

    @Before("readPointcut()")
    public void setReadDataSource() {
        dataSourceHolder.setSlave();
    }

    /**
     * 读操作完成后切回主库，避免没有使用注解的方法执行出错
     */
    @After("readPointcut()")
    public void setToWriteDataSource() {
        dataSourceHolder.setMaster();
    }
}
