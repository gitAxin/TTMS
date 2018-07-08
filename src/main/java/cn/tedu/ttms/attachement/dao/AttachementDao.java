package cn.tedu.ttms.attachement.dao;

import java.util.List;

import cn.tedu.ttms.attachement.entity.Attachement;
import cn.tedu.ttms.common.dao.BaseDao;

/**
 * 
 * @author 天大java
 *
 */
public interface AttachementDao extends BaseDao<Attachement>{
	
	List<Attachement> findObjects();
	/**根据摘要信息判定文件是否已经存在*/
	int findObjectByDisGest(String fileDisgest);
	
	Attachement findObjectById(Integer id);
	
}
