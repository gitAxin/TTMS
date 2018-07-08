package cn.tedu.ttms.team.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.tedu.ttms.common.dao.BaseDao;
import cn.tedu.ttms.team.entity.Team;

public interface TeamDao extends BaseDao<Team>{
	
	/**插入一个团
	 * 已继承父类的
	 * */
	
	//public Integer insertObject(Team team);
	/**
	 * 一条记录对应一个map(key为列的名字,value为列的值
	 * 多条记录是多个map对象,然后多个map放到list集合中
	 * @return
	 */
	public List<Map<String,Object>> findPageObject(
			@Param("projectName")String projectName,
			@Param("valid")Integer valid,
			@Param("startIndex")int startIndex,
			@Param("pageSize")int pageSize);
	/**
	 * 统计有多少条记录
	 * @param projectName
	 * @param valid
	 * @param startIndex
	 * @param pageSize
	 * @return
	 */
	public int getRowCount(
			@Param("projectName")String projectName,
			@Param("valid")Integer valid);
	
	
	/**
	 * 根据id查询团信息
	 * @param id
	 * @return
	 */
	public Team getObjectById(Integer id);
	
	
	/**根据id修改状态信息(启用或禁用)*/
	public int validById(@Param("ids")String[] ids,
						@Param("valid")Integer valid);
	
	
	
	/**查找所有的团名称及团id*/
	public List<Map<String,Object>> findNameAndId();
	
}

