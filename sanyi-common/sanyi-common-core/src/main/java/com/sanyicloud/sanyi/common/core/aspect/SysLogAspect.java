//package com.sanyicloud.sanyi.common.core.aspect;
//
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.AfterReturning;
//import org.aspectj.lang.annotation.AfterThrowing;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Pointcut;
//import org.aspectj.lang.reflect.MethodSignature;
//import org.springframework.stereotype.Component;
//import org.springframework.web.context.request.RequestAttributes;
//import org.springframework.web.context.request.RequestContextHolder;
//
//import javax.servlet.http.HttpServletRequest;
//import java.lang.reflect.Method;
//
//
///**
// * 系统日志，切面处理类
// *
// * @author Mark sunlightcs@gmail.com
// */
//@Aspect
//@Component
//public class SysLogAspect {
//
//	@Pointcut("@annotation(com.sanyicloud.*.SysLog)")
//	public void logPointCut() {
//
//	}
//
//	@Pointcut("execution(* com.sanyicloud.*.controller..*.*(..))")
//	public void exceptionLogPointCut() {
//
//	}
//
//	@AfterReturning("logPointCut()")
//	public void around(JoinPoint point) throws Throwable {
//		long beginTime = System.currentTimeMillis();
//		//执行时长(毫秒)
//		long time = System.currentTimeMillis() - beginTime;
//		//保存日志
//		saveSysLog(point, time);
//	}
//
//	private void saveSysLog(JoinPoint joinPoint, long time) {
//		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
//		Method method = signature.getMethod();
//
////		SysLogEntity sysLog = new SysLogEntity();
////		SysLog syslog = method.getAnnotation(SysLog.class);
////		if(syslog != null){
////			//注解上的描述
////			sysLog.setOperation(syslog.value());
////		}
////
////		//请求的方法名
////		String className = joinPoint.getTarget().getClass().getName();
////		String methodName = signature.getName();
////		sysLog.setMethod(className + "." + methodName + "()");
////
////		//请求的参数
////		Object[] args = joinPoint.getArgs();
////		try{
////			String params = new Gson().toJson(args);
////			sysLog.setParams(params);
////		}catch (Exception e){
////
////		}
////
////		//获取request
////		HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
////		//设置IP地址
////		sysLog.setIp(IPUtils.getIpAddr(request));
////		Object principal = SecurityUtils.getSubject().getPrincipal();
////		if (null != principal){
////			//用户名
////			String username = ((SysUserEntity) principal).getUsername();
////			sysLog.setUsername(username);
////
////			sysLog.setTime(time);
////			sysLog.setCreateDate(new Date());
////			//保存系统日志
////			sysLogService.save(sysLog);
////		}
//
//	}
//	/**
//	 * 异常返回通知，用于拦截异常日志信息 连接点抛出异常后执行
//	 *
//	 * @param joinPoint 切入点
//	 * @param e         异常信息
//	 */
//	@AfterThrowing(pointcut = "exceptionLogPointCut()", throwing = "e")
//	public void saveExceptionLog(JoinPoint joinPoint, Throwable e) {
//		// 获取RequestAttributes
//		RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
//		// 从获取RequestAttributes中获取HttpServletRequest的信息
//		HttpServletRequest request = (HttpServletRequest) requestAttributes
//				.resolveReference(RequestAttributes.REFERENCE_REQUEST);
//		try {
//			// 从切面织入点处通过反射机制获取织入点处的方法
//			MethodSignature signature = (MethodSignature) joinPoint.getSignature();
//			// 获取切入点所在的方法
//			Method method = signature.getMethod();
//
//
//		} catch (Exception e2) {
//			e2.printStackTrace();
//		}
//
//	}
//}
