package proxy.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import proxy.service.ServiceInterface;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class LoggingAdvice implements InvocationHandler {

    Logger log = LoggerFactory.getLogger(LoggingAdvice.class);

    private final ServiceInterface target;

    public LoggingAdvice(ServiceInterface target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if("save".equals(method.getName())) {
            log.info("invoke {} method :" , method.getName());
        }

        return method.invoke(target, args);
    }
}
