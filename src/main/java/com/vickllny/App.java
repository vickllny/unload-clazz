package com.vickllny;

import com.vickllny.cl.DynamicClassLoader;
import com.vickllny.domain.User;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.agent.ByteBuddyAgent;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.dynamic.loading.ClassReloadingStrategy;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.matcher.ElementMatchers;

import java.lang.instrument.Instrumentation;
import java.util.ArrayList;
import java.util.List;

public class App {

    static final List<DynamicClassLoader> LOADER_LIST = new ArrayList<>();
    static final List<User> USER_LIST = new ArrayList<>();

    public static void main(String[] args) {
        // 使用 Byte Buddy 创建 User 类的子类
//        Class<? extends User> dynamicUserSubclass = new ByteBuddy()
//                .subclass(User.class)
//                .name("com.example.DynamicUser")
//                .method(ElementMatchers.named("getUserName"))
//                .intercept(FixedValue.value("DynamicUserName"))
//                .make()
//                .load(App.class.getClassLoader(), ClassLoadingStrategy.Default.INJECTION)
//                .getLoaded();
        System.out.println("hello world");

        ByteBuddyAgent.install();
        // 删除 User 类的 password 字段
        new ByteBuddy()
                .redefine(User.class)
                .name(User.class.getName())
                .field(ElementMatchers.named("password"))
                .value(null)
                .make()
                .load(User.class.getClassLoader(), ClassReloadingStrategy.fromInstalledAgent());

        User user = new User();
        System.out.printf("user =>" + user);
    }
}
