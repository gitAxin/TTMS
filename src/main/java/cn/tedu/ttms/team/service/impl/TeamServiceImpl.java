package cn.tedu.ttms.team.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.tedu.ttms.common.exception.SaveRuntimeException;
import cn.tedu.ttms.common.exception.UpdateRuntimeException;
import cn.tedu.ttms.common.web.PageObject;
import cn.tedu.ttms.project.dao.ProjectDao;
import cn.tedu.ttms.team.dao.TeamDao;
import cn.tedu.ttms.team.entity.Team;
import cn.tedu.ttms.team.service.TeamService;

@Transactional
@Service
public class TeamServiceImpl implements TeamService {
	
	@Resource
	private TeamDao teamDao;
	
	@Resource
	private ProjectDao projectDao;
	
	/**
	 * 插入团
	 */
	public void insertObject(Team team) {
		teamDao.insertObject(team);
	}

	/**
	 * 获得Team
	 */
	public Map<String, Object> findPageObject(
			String projectName,
			Integer valid,
			Integer pageCurrent) {
		System.out.println("pageCurrent(Service):"+pageCurrent);
		PageObject pageObject = new PageObject();
		//不写默认是2 写了就按写了的算
		pageObject.setPageSize(2);
		pageObject.setPageCurrent(pageCurrent);
		//根据startIndex及参数获得当前页数据
		List<Map<String,Object>> list=
				teamDao.findPageObject(
						projectName, valid,
						pageObject.getStartIndex(),
						pageObject.getPageSize());
		//获得总页数
		int rowCount = teamDao.getRowCount(projectName, valid);
		pageObject.setRowCount(rowCount);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("pageObject", pageObject);
		map.put("list", list);
		return map;
	}
	
	//保存团信息
	public void saveObject(Team team) {
		if(team==null)
			throw new SaveRuntimeException("保存信息不能为空");
		int rows=teamDao.insertObject(team);
		if(rows==-1)
			throw new SaveRuntimeException("保存失败");
		
	}

	/**查询项目的id和名字*/
	public List<Map<String, Object>> findProjectIdAndNames() {
		return projectDao.findIdAndNames();
		
	}
	
	/**根据id查询团信息，将团信息填充到编辑框，便于修改*/
	@Override
	public Team findObjectById(Integer id) {
		if(id==null){
			throw new RuntimeException("id不能为空");
		}
		return teamDao.getObjectById(id);
	}

	@Override
	public void updateObject(Team team) {
		int row = teamDao.updateObject(team);
		if(row==-1){
			throw new RuntimeException("修改失败,请重试");
		}
		
	}
	
	@Transactional(rollbackFor={UpdateRuntimeException.class})
	@Override
	public void validById(String checkedId, Integer valid) {
		if(checkedId==null || checkedId.length()==0){
			throw new NullPointerException("必须有选中");
		}
		
		if(valid!=0 && valid!=1){
			throw new IllegalArgumentException("参数无效");
		}
		
		String[] ids =checkedId.split(",");
		int rows =teamDao.validById(ids, valid);
		if(rows==-1)
			throw new UpdateRuntimeException("更新失败");
	}
	
	
	
	
	
	
	
	
	
}
