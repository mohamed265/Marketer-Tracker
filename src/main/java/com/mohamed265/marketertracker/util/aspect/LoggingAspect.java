/**
 * @author mohamed265
 * Created On : Mar 25, 2017 3:37:33 PM
 */
package com.mohamed265.marketertracker.util.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 * @author mohamed265
 *
 */
@Aspect
public class LoggingAspect {

	final private static Logger logger = Logger.getLogger(LoggingAspect.class);

	@Before("execution(* com.mohamed265.marketertracker..*(..))*)")
	public void logBefore(JoinPoint joinPoint) {
		String methodName = joinPoint.getSignature().getName();
		logger.info(methodName + " called");
		String params = "";

		for (Object object : joinPoint.getArgs()) {
			params += object + "|";
		}

		logger.debug("calling: " + methodName + " with Args: " + params);

	}

}
