package common.dao;

import javax.sql.DataSource;

import org.junit.Assert;
import org.junit.Test;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestDruid {

	public void method(int... n) {
	};// 可变参数三个点

	@Test
	public void testDruid() {
		String config = "spring-pool.xml";
		ApplicationContext ac = new ClassPathXmlApplicationContext(config);
		// 获得DataSource对象
		DataSource ds = ac.getBean("dataSource", DataSource.class);
		System.out.println(ds);
		// 测试数据源对象是否为空
		Assert.assertNotEquals(null, ds);
	}

	@Test
	public void testSessionFactory() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-mvc.xml", "spring-pool.xml",
				"spring-mybatis.xml");
		Object sessionFactory = ctx.getBean("sqlSessionFactory");
		System.out.println(sessionFactory);
		Assert.assertNotEquals(null, sessionFactory);
	}
	
	
}
