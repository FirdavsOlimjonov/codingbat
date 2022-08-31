package ai.ecma.codingbat.config.log;//package ai.ecma.codingbat.config.log;
//
//import lombok.extern.slf4j.Slf4j;
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.AfterThrowing;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Pointcut;
//
///**
// * Aspect for logging execution of service and repository Spring components.
// */
//@Aspect
//@Slf4j
//public class LoggingAspect {
//
//    @Pointcut("within(ai.ecma.codingbat.controller..*)")
//    public void loggingPointcut() {
//    }
//
//    @AfterThrowing(pointcut = "loggingPointcut()", throwing = "e")
//    public void logAfterThrowing(JoinPoint joinPoint, Exception e) {
//        log.error("Exception in {}.{}() with cause = {}, exception = {}",
//                joinPoint.getSignature().getDeclaringTypeName(),
//                joinPoint.getSignature().getName(),
//                (e.getCause() != null ? e.getCause() : "NULL"),
//                e);
//    }
//}
