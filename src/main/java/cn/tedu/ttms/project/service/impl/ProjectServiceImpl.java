package cn.tedu.ttms.project.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import cn.tedu.ttms.common.exception.SaveRuntimeException;
import cn.tedu.ttms.common.exception.UpdateRuntimeException;
import cn.tedu.ttms.common.web.PageObject;
import cn.tedu.ttms.project.dao.ProjectDao;
import cn.tedu.ttms.project.entity.Project;
import cn.tedu.ttms.project.service.ProjectService;
/**
 * 项目中所有与业务相关的事情一般都放在service中,例如
 * 1)判定参数是否符合业务要求
 * 2)判定dao返回的数据是否是我们需要的结果
 * 3)执行一些日志的记录
 * 4)执行一些事务的处理
 * 5)...
 * @author 天大java
 *
 */
/**
 * 事务方法调事务方法:
 * 事务方法遇到什么异常回滚Transactional()
 * @author 天大java
 *
 */


/*编程式
声明式:XML式和注解式
*/
@Transactional//使用声明式事务控制事务,底层使用AOP原理实现
@Service//告诉容器当前类由容器来管理
public class ProjectServiceImpl implements ProjectService {
	
	@Resource
	private ProjectDao projectDao;
	
	//Read_committed解决了脏读问题
	@Transactional(readOnly=true,isolation=Isolation.READ_COMMITTED)
	/**查找多条项目信息*/
	public List<Project> findObjects() {
		//......后续还会添加内容
		return projectDao.findObjects();
	}
	
	/**插入项目信息*/
	public int insertObject(Project project){
		return 1;
	}
	/**
	 * 返回分页信息
	 * 1)记录信息
	 * 2)分页信息
	 * @Param pageObject 用于接收控制层传递过来的分页信息
	 * 1)此参数中应包含startIndex
	 * 2)此参数中应包含pageSize
	 */
	public Map<String, Object> findPageObjects(Project project,PageObject pageObject) {
		//1.获得页面表格中要显示的数据
		List<Project> list =projectDao.findPageObjects(project,pageObject);
		//2.获得表中记录数并计算页数
		int rowCount=projectDao.getRowCount(project);
		pageObject.setRowCount(rowCount);
		//3.构建map对象封装从dao层获得的数据
		Map<String,Object> map =new HashMap<String,Object>();
		map.put("list", list);
		map.put("pageObject", pageObject);
		return map;
	}

	
	@Transactional(rollbackFor={UpdateRuntimeException.class})
	/**
	 * 修改启用禁用状态
	 */
	public void ValidById(String checkedIds, Integer valid){
		
		//判定参数checkedIds
		if(checkedIds==null || checkedIds.length()==0){
			throw new NullPointerException("必须有选中");
		}
		System.out.println(valid);
		if(valid!=1 && valid!=0){
			throw new IllegalArgumentException("无效的valid值");
		}
		//解析字符串(1,2,3,4,5);
		String ids[]=checkedIds.split(",");
		
		//访问dao层方法执行启用禁用的更新操作
		int rows = projectDao.validById(ids,valid);
		//如果返回的更新行数为-1 则更新失败
		if(rows==-1){
			throw new RuntimeException("更新失败");
		}
	}
	
	/**
	 * 插入一个项目
	 */
	public void saveObject(Project project) {
		int rows=projectDao.insertObject(project);
		if(rows==-1)
			new RuntimeException("save error~~");
		
	}
	@Cacheable(value="projectCache",key="#id")//map.put(key,data)
	/**根据ID查找项目信息*/
	public Project findObjectById(Integer id) {
		System.out.println("==findObjectId==");
		if(id==null)
			throw new NullPointerException("id can not be null");
		Project pro=projectDao.findObjectById(id);
		if(pro==null)
			throw new SaveRuntimeException("project does not exists");
		return pro;
	}
	
	
	
	
	@CacheEvict(value="projectCache",key="#project.id")
	/**修改项目信息*/
	public void updateObject(Project project) {
		int rows = projectDao.updateObject(project);
		if(rows==-1){
			throw new UpdateRuntimeException("update error"); 
		}
	}
	
	
	
	
	
	
	
	
	
	

}
