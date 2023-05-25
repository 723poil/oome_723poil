package org.oome.infra.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.oome.core.utils.S;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Aspect
public class TraceLogger {
    private static final String STR_CLASS_METHOD = "{0}.{1}({2})";
    private static final String STR_START_EXECUTE_TIME = "[{}]{} START======Execute Time====== : {}";
    private static final String STR_END_EXECUTE_TIME = "[{}]{} E N D======Execute Time====== : {} - return Value({}) : {}";

    @Around("execution(* org.oome.api.*.controllers.*ApiController.*(..)) || execution(* org.oome.api.*.services.*Service.*(..))")
    public Object doLoggingAround(final ProceedingJoinPoint pjp) throws Throwable {
        Object retVal;

        final String transactionId = UUID.randomUUID().toString();

        final String formatClassMethod = S.f(STR_CLASS_METHOD, pjp.getTarget().getClass().getSimpleName(), pjp.getSignature().getName(), this.getArgumentNames(pjp.getArgs()));

        try {
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();
            log.debug(STR_START_EXECUTE_TIME, transactionId, formatClassMethod, stopWatch);

            retVal = pjp.proceed();

            stopWatch.stop();
            log.debug(STR_END_EXECUTE_TIME, transactionId, formatClassMethod, stopWatch, ((MethodSignature) pjp.getSignature()).getReturnType().getSimpleName(), "");
        } catch (Throwable e) {
            log.warn("{} -\n{}", formatClassMethod, ExceptionUtils.getStackTrace(e));
            throw e;
        }
        return retVal;
    }

    private String getArgumentNames(final Object[] obj) {
        final List<String> list = new ArrayList<>();
        for (Object o : obj) {
            if (o != null) {
                list.add(o.getClass().getSimpleName());
            }
        }
        return StringUtils.join(list, ", ");
    }
}
