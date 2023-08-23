package proxy.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class DynamicInvocationHandler implements InvocationHandler {
    private static Logger LOGGER = LoggerFactory.getLogger(DynamicInvocationHandler.class);

    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {

        LOGGER.info("Invoked method : {}", method.getName());
        return 42;
    }
}
