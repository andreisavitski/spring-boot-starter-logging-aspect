package by.pvt.loggingaspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
@Slf4j
public class LoggingAspect {
    @Pointcut("@annotation(MethodLogging)")
    public void processingMethods() {
    }

    @Before("processingMethods()")
    public void logMethodCall(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        log.info("Method {} started", methodName);
    }

    @AfterReturning(pointcut = "processingMethods()", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().getName();
        String resultOfMethod = result.toString();
        log.info("Method {} completed, result is = {}", methodName, resultOfMethod);
    }
}
