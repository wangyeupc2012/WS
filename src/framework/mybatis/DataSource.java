package framework.mybatis;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataSource {

	String name() default DataSource.syjdDataSource;
	public static String syjdDataSource = "syjdDataSource";
	public static String erpDataSource = "erpDataSource";
	public static String twDataSource = "twDataSource";
	 
}
