package com.virtualpairprogrammers.advice;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;

@Component
public class PerformanceTimingAdvice {
    private static final int NANOSECONDS_IN_A_MILLISECOND = 1000000;

    public Object recordTiming(ProceedingJoinPoint jp) throws Throwable
    {
        double timeNow = System.nanoTime();

        try
        {
            Object returnValue = jp.proceed();
            return returnValue;
        }
        finally
        {
            double timeAfter = System.nanoTime();

            double timeTaken = timeAfter - timeNow;

            double timeInMilliseconds = timeTaken / NANOSECONDS_IN_A_MILLISECOND;

            System.out.println("Time taken for the method: " + jp.getSignature().getName() + " from the class " +
                    jp.getTarget().getClass().getSimpleName() + " took "+ timeInMilliseconds + "ms");
        }
    }

    public void beforeAdviceTesting(JoinPoint jp)
    {
        System.out.println("Now entering a method...." + jp.getSignature().getName());
    }
}
