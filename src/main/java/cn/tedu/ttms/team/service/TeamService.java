package cn.tedu.ttms.team.service;

import java.util.List;
import java.util.Map;

import cn.tedu.ttms.team.entity.Team;

/**
 * Team模块服务层接口
 * @author 天大java
 *
 */
public interface TeamService {
	
	
	void insertObject(Team team);
	
	//执行分页查询
	//页面传过来当前页
	/**
	 * 
	 * @param ProjectName 根据项目名称执行查询操作，
	 * @param valid 根据禁用启用执行查询操作
	 * @param pageCurrent 表示当前页，参数是页面传过来的
	 * @return
	 */
	Map<String,Object> findPageObject(String ProjectName,Integer valid,Integer pageCurrent);
	
		//根据当前页面，和页面记录数，计算出开始显示条数
	
	
	/**保存表单team信息*/
	void saveObject(Team team);
	
	
	/**查询项目的id和名字*/
	List<Map<String,Object>> findProjectIdAndNames();
		
	/**根据id查询团信息，将团信息填充到编辑框，便于修改*/
	Team findObjectById(Integer id);
	
	
	/**
	 * 根据id修改团信息
	 */
	void updateObject(Team team);
	
	
	/**根据id修改状态信息*/
	void validById(String checkedId,Integer valid);
	
	
}
