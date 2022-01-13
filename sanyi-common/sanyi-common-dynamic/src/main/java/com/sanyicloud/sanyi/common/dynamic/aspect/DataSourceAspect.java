package com.sanyicloud.sanyi.common.dynamic.aspect;

import com.sanyicloud.sanyi.common.dynamic.annotation.Read;
import com.sanyicloud.sanyi.common.dynamic.annotation.Write;
import com.sanyicloud.sanyi.common.dynamic.datasource.DataSourceHolder;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DataSourceAspect {

    @Autowired
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

    @Before("writePointcut() && @annotation(master) ")
    public void setWriteDataSource(Write master) {
        dataSourceHolder.setMaster(master.value());
    }

    @Before("readPointcut() && @annotation(slave) ")
    public void setReadDataSource(Read slave) {
        dataSourceHolder.setSlave(slave.value());
    }

    /**
     * 读操作完成后切回主库，避免没有使用注解的方法执行出错
     */
    @After("readPointcut()")
    public void setToWriteDataSource() {
        dataSourceHolder.setMaster("");
    }
}
