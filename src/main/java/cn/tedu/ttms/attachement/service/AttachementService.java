package cn.tedu.ttms.attachement.service;

import java.io.File;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import cn.tedu.ttms.attachement.entity.Attachement;

public interface AttachementService {
	
		/**
		 * @param title附件标题
		 * @param athType附件归属类型(例如产品附件)
		 * @param belongId 附件归属id
		 * @Param mFile 上传的文件对象
		 */
	void saveObject(String title,
			Integer athType,
			Integer belongId,
			MultipartFile mFile,
			String serverPath);
	
	List<Attachement> findObjects();
	
	File findObjectById(Integer id);
	
	
	
}
