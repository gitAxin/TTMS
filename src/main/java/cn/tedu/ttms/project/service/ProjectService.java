package cn.tedu.ttms.project.service;

import java.util.List;
import java.util.Map;

import cn.tedu.ttms.common.web.PageObject;
import cn.tedu.ttms.project.entity.Project;

public interface ProjectService {
	
	List<Project> findObjects();
	
	
	/**
	 * 返回分页信息
	 * 1)记录信息
	 * 2)分页信息
	 */
	Map<String,Object> findPageObjects(Project project,PageObject pageObject);
	
	void ValidById(String checkedIds,Integer valid);


	void saveObject(Project project);
	
	/**根据ID查找项目信息*/
	Project findObjectById(Integer id);
	
	/**根据ID修改项目信息*/
	void updateObject(Project project);
}
