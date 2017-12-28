package com.fxkj.core.Aspect;

import java.lang.reflect.Method;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fxkj.core.base.BaseEntity;
import com.fxkj.core.base.MethodLog;
import com.fxkj.core.base.NoCheckLogin;
import com.fxkj.core.common.SecurityUtils;
import com.fxkj.core.exception.DaoException;

@Component
@Aspect
public class GlobalAspect {
	//private final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired(required = false)
	HttpSession session;

	/**
	 * 对以Dao结尾的类里的add开头的方法进行切面
	 * 
	 * @param baseEntity
	 * @throws Exception
	 */
	@Before("(execution(public * com.fxkj..*.*Dao.add*(..)) && args(baseEntity,..))||(execution(public * com.fxkj..*.*ServiceImpl.addMethod*(..)) && args(baseEntity,..))")
	public void appendCreateInfo(BaseEntity baseEntity) throws Exception {
		boolean flg = baseEntity.getClass().getAnnotation(NoCheckLogin.class)==null?true:false;
		String name = null;
		if (flg) {
			name = SecurityUtils.getCurrentOperatorName();
			if (name == null || "".equals(name)) {
				throw new DaoException("用户未登录");
			}
		}

		baseEntity.setCreateDate(new Date());
		baseEntity.setCreateUser(name!=null?name:"admin");
		baseEntity.setUpdateDate(new Date());
		baseEntity.setUpdateUser(name!=null?name:"admin");
	}

	/**
	 * 对以Dao结尾的类里的update开头的方法进行切面
	 * 
	 * @param baseEntity
	 * @throws Exception
	 */
	@Before("(execution(public * com.fxkj..*.*Dao.update*(..)) &&  args(baseEntity,..))||(execution(public * com.fxkj..*.*ServiceImpl.updateMethod*(..)) && args(baseEntity,..))")
	public void appendUpdateInfo(BaseEntity baseEntity) throws Exception {
		String name = SecurityUtils.getCurrentOperatorName();
		if (name == null || "".equals(name)) {
			throw new DaoException("用户未登录");
		}
		baseEntity.setUpdateDate(new Date());
		baseEntity.setUpdateUser(name);
	}

	/*@Around("execution(public * com.fxkj..*.*Controller.*(..))")
	public Object controllerAspect(ProceedingJoinPoint jp) throws Throwable {
		try {
			Date startDate = new Date();
			Object retVal = jp.proceed();
			String signature = jp.getSignature().toString();// 获取目标方法签名
			String methodName = signature.substring(
					signature.lastIndexOf(".") + 1, signature.indexOf("("));
			String className = jp.getTarget().getClass().getSimpleName()
					.toString();// 获取目标类名
			String methodRemark = getMthodRemark(jp);// 获取方法备注注
			String loginName = SecurityUtils.getCurrentOperatorName();
			if (methodName != null && !"login".equals(methodName)) {
				if (loginName == null || "".equals(loginName)) {
					throw new DaoException("用户未登录");
				}
			}
			logger.info("user[" + loginName + "]");
			logger.info("class[" + className + "]method[" + methodName + "]remark["
					+ methodRemark + "]date["
					+ DateUtils.longDate(startDate) + "]costDate["
					+ DateUtils.getMistimingDate(startDate, new Date())
					+ "]毫秒");
			return retVal;
		} catch (DaoException d) {
			logger.error(d.getMessage());
			throw new DaoException(d.getMessage());
		} catch (ServiceException s) {
			logger.error(s.getMessage());
			throw new ServiceException(s.getMessage());
		} catch (UndeclaredThrowableException e) {
			logger.error(e.getMessage());
			throw new UndeclaredThrowableException(e,e.getMessage());
		} finally {
		}
	}*/

	// @AfterReturning(pointcut =
	// "execution(public * com.fxkj..*.*Controller.*(..))", returning =
	// "returnValue")
	// public void log(ProceedingJoinPoint point, Object returnValue)
	// throws Throwable {
	// returnValue = null;
	// System.out.println("@AfterReturning：返回值为：" + returnValue);
	// Object[] o = new Object[1];
	// o[0] = returnValue;
	// System.out.println("@AfterReturning：被织入的目标对象为：" + point.getTarget());
	// point.proceed(o);
	// }

	// 获取方法的中文备注____用于记录用户的操作日志描述
	public static String getMthodRemark(ProceedingJoinPoint joinPoint)
			throws Exception {
		String targetName = joinPoint.getTarget().getClass().getName();
		String methodName = joinPoint.getSignature().getName();
		Object[] arguments = joinPoint.getArgs();
		Method[] method = Class.forName(targetName).getMethods();
		String methode = "";
		for (Method m : method) {
			if (m.getName().equals(methodName)) {
				if (m.getParameterTypes().length == arguments.length) {
					MethodLog methodCache = m.getAnnotation(MethodLog.class);
					if (methodCache != null) {
						methode = methodCache.remark();
						break;
					}
				}
			}
		}
		return methode;
	}
}
