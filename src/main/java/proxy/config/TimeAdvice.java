package proxy.config;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TimeAdvice implements MethodInterceptor {
    Logger log = LoggerFactory.getLogger(TimeAdvice.class);

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        log.info("TimeAdvice 실행");
        long startTime = System.currentTimeMillis();

        Object result = invocation.proceed();

        long endTime = System.currentTimeMillis();
        log.info("total : {}", endTime - startTime);
        return result;
    }
}
