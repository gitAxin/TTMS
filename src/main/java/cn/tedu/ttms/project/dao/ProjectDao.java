package cn.tedu.ttms.project.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.tedu.ttms.common.dao.BaseDao;
import cn.tedu.ttms.common.web.PageObject;
import cn.tedu.ttms.project.entity.Project;

/**
 * 此接口对象为一个数据访问对象(关联一个mapper)
 * 所在层:DAL(数据访问层)
 * @author 天大java
 *
 */
public interface ProjectDao extends BaseDao<Project> {
	
	/**
	 * 插入一个项目
	 * @author 天大java
	 * @param entity代表是一个project对象
	 * @return 表示insert记录的行数
	 */
	//public int insertObject(Project project);
	/**
	 * 查询所有的项目
	 * @return 表示所有的记录
	 */
	public List<Project> findObjects();
	
	
	/**
	 * 分页查询项目信息
	 */
	public List<Project> findPageObjects(
			@Param("project")Project project,
			@Param("pageObject")PageObject pageObject);
	
	
	/**
	 * 查询表中的总记录数
	 */
	public int getRowCount(@Param("project")Project project);
	
	
	
	/**
	 * 禁用启用记录数
	 * @return 表示更新的行数,假如返回值为-1表示更新失败
	 */
	public int validById(@Param("ids")String[] ids,
							@Param("valid")Integer valid);
	
	/**
	 * 根据id修改project对象
	 */
	//public int updateObject(Project project);
	
	
	/**
	 * 根据id查找Project对象
	 * @param id
	 * @return
	 */
	public Project findObjectById(Integer id);
	
	
	/**
	 * 查询项目的id和名字
	 * 
	 */
	List<Map<String,Object>> findIdAndNames();
	
}
