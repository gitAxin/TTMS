package cn.tedu.ttms.attachement.service.impl;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import cn.tedu.ttms.attachement.dao.AttachementDao;
import cn.tedu.ttms.attachement.entity.Attachement;
import cn.tedu.ttms.attachement.service.AttachementService;

@Service
public class AttachementServiceImpl implements AttachementService {

	@Resource
	private AttachementDao attachementDao;

	public void saveObject(String title, Integer athType, Integer belongId, MultipartFile mFile,String serverPath) {
		String oFileName = mFile.getOriginalFilename();
		System.out.println("真实的文件名:"+oFileName);
		
		byte[] fileBytes;
		File dest;
		String fileDigest;
		try {
			//1.2获得文件字节
			fileBytes = mFile.getBytes();
			System.out.println("文件字节:"+fileBytes);
			fileDigest=DigestUtils.md5Hex(fileBytes);
			System.out.println("文件摘要"+fileDigest);
			/**根据文件摘要判定文件是否已经有了*/
			int count=attachementDao.findObjectByDisGest(fileDigest);
			if(count>0){
				throw new RuntimeException("文件已经存在");
			}
			//1.3执行上传动作
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
			String dateStr = sdf.format(new Date());
			//UUID.randomUUID()随机生成字符串
			//FilenameUtils.getExtension()获取文件的后缀名,不包括点
			String newFileName =UUID.randomUUID().toString()+"."+FilenameUtils.getExtension(oFileName);
			//serverPath为tomcat的路径
			String realPath=serverPath+"/uploads/"+dateStr+"/"+newFileName;
			dest=new File(realPath);//上传后文件保存的位置
			
			File parent = dest.getParentFile();//获取指定保存位置的路径d:/uploads/
			if(!parent.exists()){//如果不存在则创建
				parent.mkdirs();
			}
			mFile.transferTo(dest);//上传
			
			
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("upload error");
		}
		
		
		//2.保存文件信息到数据库
		Attachement t = new Attachement();
		t.setTitle(title);
		t.setFileName(oFileName);
		t.setFilePath(dest.getPath());
		t.setAthType(athType);
		t.setBelongId(belongId);
		t.setContentType(mFile.getContentType());
		t.setFileDisgest(fileDigest);
		t.setCreatedUser("admin");
		t.setModifiedUser("admin");
		attachementDao.insertObject(t);
	}

	@Override
	public List<Attachement> findObjects() {
		return attachementDao.findObjects();
	}
	
	//1.根据id查找记录
	@Override
	public File findObjectById(Integer id) {
		
				
				
				
		//如果id为空
		if(id==null){
			throw new RuntimeException("id can not be null");
		}
		//根据id查询对象信息
		Attachement a = attachementDao.findObjectById(id);
		//如果对象信息为null
		if(a==null){
			throw new RuntimeException("数据库中没有对应的记录信息");
		}
		//2.获得文件的真实路径
		//3.构建文件对象关联真实路径
		File file =new File(a.getFilePath());
		//4.检测文件是否存在,存在则下载
		if(!file.exists()){
			throw new RuntimeException("文件已经不存在");
		}
		return file;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

}
