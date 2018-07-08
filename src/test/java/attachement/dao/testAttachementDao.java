package attachement.dao;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Assert;
import org.junit.Test;

import cn.tedu.ttms.attachement.dao.AttachementDao;
import cn.tedu.ttms.attachement.entity.Attachement;
import common.dao.TestBaseDao;

public class testAttachementDao extends TestBaseDao {
	
	@Test
	public void testInsertObject(){
		AttachementDao dao = ctx.getBean("attachementDao",AttachementDao.class);
		Attachement t = new Attachement();
		t.setTitle("title-B");
		t.setFileName("b.png");
		t.setFilePath("/upload/2017/07/21/ABCD.png");
		t.setContentType("images/png");
		String fileContent="helloworld";//假设这是文件内容
		String digest = DigestUtils.md5Hex(fileContent.getBytes());
		t.setFileDisgest(digest);
		t.setCreatedUser("admin");
		t.setModifiedUser("admin");
		int rows = dao.insertObject(t);
		System.out.println(rows);
		Assert.assertEquals(1, rows);
		
	}
	
	

}
