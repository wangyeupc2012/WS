package framework.mybatis;

import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * Created by best_liumj on 2017/8/15.
 */
public class DataSourceExchange implements MethodBeforeAdvice,AfterReturningAdvice
{

    public void afterReturning(Object returnValue, Method method,
                               Object[] args, Object target) throws Throwable {
        DataSourceHolder.clearDataSource();
    }

    public void before(Method method, Object[] args, Object target)
            throws Throwable {
        //这里DataSource是自定义的注解，不是java里的DataSource接口
        if (method.isAnnotationPresent(DataSource.class))
        {
            DataSource datasource = method.getAnnotation(DataSource.class);

            DataSourceHolder.setDataSource(datasource.name());
        }
        else
        {
            //target是被织入增强处理的目标对象，通过获取getDataSourceName函数来获取target的数据源名称
            DataSourceHolder.setDataSource(
                    target.getClass().getMethod("getDataSourceName").invoke(target).toString());
        }

    }
}