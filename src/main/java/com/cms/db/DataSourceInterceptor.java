package com.cms.db;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author  矜持的折返跑
 * @date 2017-11-08
 */
public class DataSourceInterceptor implements MethodInterceptor{
	
	private final static Logger logger = LoggerFactory.getLogger(DataSourceInterceptor.class);
	
	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		DataSourceSelector annotation = invocation.getMethod().getAnnotation(DataSourceSelector.class);
		if(annotation != null) {
			DynamicDataSource.setDataSource(annotation.name());
		}else{
			DynamicDataSource.setDataSource(DataSourceEnum.SLAVE.getName());
		}
		Object returnValue=null;
		try {
			returnValue = invocation.proceed();
		} catch (Exception e) {
			//异常情况必须clear从数据源，防止final ThreadLocal<String> contextHolder 变量不释放
			DynamicDataSource.clearDataSource();
			logger.info("DataSourceInterceptor invoke Exception, methodName is ["+invocation.getMethod().toString()+"],"+e.getMessage());
			throw e;
		}
		// 正常情况也必须 clear 数据源，采用默认的数据源
		DynamicDataSource.clearDataSource();
		return returnValue;
	}

}
