package proxy.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class TimingDynamicInvocationHandler implements InvocationHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(TimingDynamicInvocationHandler.class);

    private final Map<String, Method> methods = new HashMap<>();

    private Object target;

    public TimingDynamicInvocationHandler(Object target) {
        this.target = target;

        // target 클래스가 가지는 모든 메서드를 Map에 등록
        for(Method method : target.getClass().getMethods()) {
            this.methods.put(method.getName(), method);
        }
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        long start = System.nanoTime();
        Object result = methods.get(method.getName()).invoke(target, args);
        long end = System.nanoTime();

        LOGGER.info("Executing {} finished in {} ns", method.getName(), end - start);

        return result;
    }
}
