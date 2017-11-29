package com.elong.nbopen.api.util.dao.db;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

public class GetDataSourceKeyAspect {

	public void before(JoinPoint point) throws Exception {
		// 拦截的实体类
		Object target = point.getTarget();
		// 拦截的方法名称
		String methodName = point.getSignature().getName();
		// 拦截的放参数类型
		Class<?>[] parameterTypes = ((MethodSignature) point.getSignature()).getMethod().getParameterTypes();
		Class<?>[] clazzs = target.getClass().getInterfaces();
		Class<?> clazz = null;
		if (clazzs != null && clazzs.length > 0) {
			clazz = clazzs[0];
		}

		if (clazz == null) {
			// 这个应该不可能出现
			throw new RuntimeException("not found Class.");
		}

		// 拿方法中参数级别的注解，如果参数中有数据源，则固定为方法第一个参数为数据源
		Annotation[][] annotationss = ((MethodSignature) point.getSignature()).getMethod().getParameterAnnotations();
		if (annotationss != null && annotationss.length > 0) {
			boolean isExistParamDataSource = false;
			for (Annotation[] annotations : annotationss) {
				if (annotations == null || annotations.length == 0)
					continue;
				for (Annotation annotation : annotations) {
					if (annotation.annotationType().equals(DataSource.class)) {
						isExistParamDataSource = true;
						break;
					}
				}
			}
			if (isExistParamDataSource) {
				Object[] args = point.getArgs();
				String dataSource = (String) args[0];
				DataSourceKeyHolder.setDataSourceKey(dataSource);
				return;
			}
		}

		// 拿接口中的方法级别的注解
		Method m = clazz.getMethod(methodName, parameterTypes);
		if (m != null && m.isAnnotationPresent(DataSource.class)) {
			DataSource ca = m.getAnnotation(DataSource.class);
			if (StringUtils.isNotBlank(ca.value())) {
				DataSourceKeyHolder.setDataSourceKey(ca.value());
				return;
			}
		}
		// 拿到类的方法级别注解
		m = target.getClass().getMethod(methodName, parameterTypes);
		if (m != null && m.isAnnotationPresent(DataSource.class)) {
			DataSource ca = m.getAnnotation(DataSource.class);
			if (StringUtils.isNotBlank(ca.value())) {
				DataSourceKeyHolder.setDataSourceKey(ca.value());
				return;
			}
		}

		// 拿到类级别的注解
		DataSource can = (DataSource) clazz.getAnnotation(DataSource.class);
		if (can != null && StringUtils.isNotBlank(can.value())) {
			DataSourceKeyHolder.setDataSourceKey(can.value());
			return;
		}

	}

}
