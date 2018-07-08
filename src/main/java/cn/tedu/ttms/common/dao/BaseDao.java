package cn.tedu.ttms.common.dao;

import java.util.Map;

/**
 * 类上定义的泛型用于约束类中方法的参数类型,方法的返回值类型或属性类型
 * @author 天大java
 *
 * @param <T>
 */
public interface BaseDao<T>{
	
	int insertObject(T t);
	int updateObject(T t);
	
	
	

}
