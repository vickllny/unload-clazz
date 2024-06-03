package com.vickllny;

import com.vickllny.cl.DynamicClassLoader;
import com.vickllny.domain.User;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.agent.ByteBuddyAgent;
import net.bytebuddy.description.modifier.Visibility;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.implementation.bind.annotation.*;
import net.bytebuddy.matcher.ElementMatchers;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class App {

    static final List<DynamicClassLoader> LOADER_LIST = new ArrayList<>();
    static final List<User> USER_LIST = new ArrayList<>();

    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        ByteBuddyAgent.install();
        // 创建 User 类的子类并拦截所有方法调用
        Class<? extends User> dynamicUserClass = new ByteBuddy()
                .subclass(User.class)
                .defineProperty("userId", String.class)
                .method(ElementMatchers.isGetter().or(ElementMatchers.isSetter())) // 拦截所有方法
                .intercept(MethodDelegation.to(MethodInterceptor.class))
                .make()
                .load(User.class.getClassLoader())
                .getLoaded();

        // 实例化动态生成的子类
        User user = dynamicUserClass.newInstance();
        user.setsUserName("TestUser");
        user.setsPassword("TestPassword");
        ReflectionUtils.
    }
    public static class MethodInterceptor {
        @RuntimeType
        public static Object intercept(@This Object instance,
                                       @SuperCall Callable<?> superMethod,
                                       @AllArguments Object[] args,
                                       @Origin Method method) throws Exception {
            System.out.println("Method intercepted!");

            // 获取当前实例
            System.out.println("Current instance: " + instance);

            // 调用原方法
            Object result = superMethod.call();

            // 可以在这里对返回值进行处理
            if (result instanceof String) {
                return "Intercepted: " + result;
            }

            return result;
        }
    }
}
