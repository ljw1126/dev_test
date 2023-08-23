package proxy;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.AopUtils;
import org.springframework.cglib.beans.BeanGenerator;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.FixedValue;
import org.springframework.cglib.proxy.MethodInterceptor;
import proxy.config.LoggingAdvice;
import proxy.config.TimeAdvice;
import proxy.handler.DynamicInvocationHandler;
import proxy.handler.TimingDynamicInvocationHandler;
import proxy.service.PersonService;
import proxy.service.ServiceImpl;
import proxy.service.ServiceInterface;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

public class ProxyFactoryTest {

    @DisplayName("interface 기반으로 둔 JDK Dynamic Proxy 생성하여 확인한다")
    @Test
    void proxyByInterfaceBased() {
        ServiceImpl target = new ServiceImpl();

        // 프록시 팩토리 생성시, 프록시의 호출 대상을 인자로 넘긴다.
        ProxyFactory proxyFactory = new ProxyFactory(target);

        // 정의한 Advice 주입
        proxyFactory.addAdvice(new TimeAdvice());
        ServiceImpl proxy = (ServiceImpl) proxyFactory.getProxy();

        //실행
        proxy.save();

        assertThat(AopUtils.isAopProxy(proxy)).isTrue();
        assertThat(AopUtils.isJdkDynamicProxy(proxy)).isTrue();
        assertThat(AopUtils.isCglibProxy(proxy)).isFalse();
        System.out.println(proxy.getClass());
    }

    @DisplayName("setProxyTargetClass를 true 설정하면 CGLIB으로 proxy를 생성한다")
    @Test
    void proxyByClassBased() {
        //given
        ServiceImpl target = new ServiceImpl();

        ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.setProxyTargetClass(true); // 구체 클래스 사용하기 = CGLIB 사용
        proxyFactory.addAdvice(new TimeAdvice());

        //when
        ServiceImpl proxy = (ServiceImpl) proxyFactory.getProxy();
        proxy.save();

        //then
        assertThat(AopUtils.isAopProxy(proxy)).isTrue();
        assertThat(AopUtils.isJdkDynamicProxy(proxy)).isFalse();
        assertThat(AopUtils.isCglibProxy(proxy)).isTrue();
        System.out.println(proxy.getClass());
    }

    @DisplayName("reflection api 사용한 proxy는 aop, jdk, cglib proxy가 아니다")
    @Test
    void dynamicProxy() {
        // java.lang.reflection
        ServiceInterface proxy = (ServiceInterface) Proxy.newProxyInstance(
                ProxyFactoryTest.class.getClassLoader(), // 프록시 로딩에 사용할 클래스 로더
                new Class[]{ServiceInterface.class}, // target의 인터페이스
                new LoggingAdvice(new ServiceImpl())
        );

        proxy.save();
        System.out.println(proxy.getClass());

        assertThat(AopUtils.isAopProxy(proxy)).isFalse();
        assertThat(AopUtils.isJdkDynamicProxy(proxy)).isFalse();
        assertThat(AopUtils.isCglibProxy(proxy)).isFalse();
    }

    @DisplayName("")
    @Test
    void dynamicProxy2() {
        // java.lang.reflection
        Map proxyInstance = (Map) Proxy.newProxyInstance(
            ProxyFactoryTest.class.getClassLoader(), // 프록시 클래스를 만들 클래스로더
            new Class[]{ Map.class }, // 프록시 클래스가 구현할 인터페이스 배열
            new DynamicInvocationHandler() // 메서드가 호출되었을 때 실행될 핸들러
        );

        proxyInstance.put("hello", "world");
    }

    @DisplayName("")
    @Test
    void dynamicProxyByLambdaExpression() {
        // java.lang.reflection
        Map proxyInstance = (Map) Proxy.newProxyInstance(
                ProxyFactoryTest.class.getClassLoader(), // 프록시 클래스를 만들 클래스로더
                new Class[]{ Map.class }, // 프록시 클래스가 구현할 인터페이스 배열
                (proxy, method, methodArgs) -> {
                    if("get".equals(method.getName())) {
                        return 42;
                    } else {
                        throw new UnsupportedOperationException("Unsupported method : " + method.getName());
                    }
                }
        );

        assertThat(proxyInstance.get("hello")).isEqualTo(42); // 임의 key 호출
        assertThatThrownBy(() -> proxyInstance.put("hello", "world")) // 임의 key, value 호출
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @DisplayName("map proxy")
    @Test
    void dynamicProxy4() {
        // java.lang.reflection
        Map mapProxyInstance = (Map) Proxy.newProxyInstance(
                ProxyFactoryTest.class.getClassLoader(), // 프록시 클래스를 만들 클래스로더
                new Class[]{ Map.class }, // 프록시 클래스가 구현할 인터페이스 배열
                new TimingDynamicInvocationHandler(new HashMap<>()) // 메서드가 호출되었을 때 실행될 핸들러
        );

        mapProxyInstance.put("hello", "world");
    }

    @DisplayName("char sequence(String) proxy")
    @Test
    void dynamicProxy5() {
        // java.lang.reflection
        CharSequence charSeqProxyInstance = (CharSequence) Proxy.newProxyInstance(
                ProxyFactoryTest.class.getClassLoader(), // 프록시 클래스를 만들 클래스로더
                new Class[]{ CharSequence.class }, // 프록시 클래스가 구현할 인터페이스 배열
                new TimingDynamicInvocationHandler("Hello world")
        );

        charSeqProxyInstance.length();
    }

    @DisplayName("동일한 값을 리턴하는 프록시 객체를 정의한다")
    @Test
    void cglibSimply() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(PersonService.class);
        enhancer.setCallback((FixedValue)() -> "Hello Tom!"); // FixedValue : Object 그대로 반환하는 콜백 메서드 가진 인터페이스 (CallBack 상속)

        PersonService proxy = (PersonService) enhancer.create();

        String result = proxy.sayHello(null);

        assertThat(result).isEqualTo("Hello Tom!");
    }

    @DisplayName("")
    @Test
    void cglibDependOnMethodSignature() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(PersonService.class);
        enhancer.setCallback((MethodInterceptor)(obj, method, args, proxy) -> {
            // MethodInterceptor 인터페이스는 Callback 상속함
            if(method.getDeclaringClass() != Object.class && method.getReturnType() == String.class) {
                return "Hello Tom!";
            } else {
                return proxy.invokeSuper(obj, args);
            }
        });

        PersonService proxy = (PersonService) enhancer.create();

        assertThat(proxy.sayHello(null)).isEqualTo("Hello Tom!");
        assertThat(proxy.lengthOfName("Mary")).isEqualTo(4);
    }

    @DisplayName("Bean Creator 동적으로 객체를 생성하고 필드, 메서드를 함께 생성 가능하다")
    @Test
    void cglibBeanCreator() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        BeanGenerator beanGenerator = new BeanGenerator();

        beanGenerator.addProperty("name", String.class);
        Object myBean = beanGenerator.create();
        Method setter = myBean.getClass().getMethod("setName", String.class);
        setter.invoke(myBean, "some string value set by a cglib");

        Method getter = myBean.getClass().getMethod("getName");
        assertThat(getter.invoke(myBean)).isEqualTo("some string value set by a cglib");
    }
}
