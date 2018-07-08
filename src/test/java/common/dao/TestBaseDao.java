package common.dao;

import org.junit.After;
import org.junit.Before;
import org.springframework.context.support.ClassPathXmlApplicationContext;
/**
 * 当前类为测试类的父类
 * @author 天大java
 *
 */
public class TestBaseDao{

	protected ClassPathXmlApplicationContext ctx;	
	
	/**
	 * 初始化
	 */
	@Before
	public void init(){
		ctx =new ClassPathXmlApplicationContext(
						"spring-mvc.xml","spring-pool.xml","spring-mybatis.xml");
	}	
	
	
	
	/**销毁*/
	@After
	public void destory(){
		ctx.close();
	}

	
}
