package com.virtualpairprogrammers.avalon.advice;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

public class SimpleLoggingAdvice implements MethodBeforeAdvice {
    @Override
    public void before(Method method, Object[] arguments, Object targetObject) throws Throwable {
        System.out.println("Now about to call " + method.getName() + " method");
    }
}
