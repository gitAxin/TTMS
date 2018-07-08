package cn.tedu.ttms.project.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import cn.tedu.ttms.common.web.DateJsonTypeConvert;

/**
 * 领域对象:entity,vo,service
 * 实体对象(对应具体的一个实体,例如项目)
 * 对应的表名:tms_projects
 * @author 天大java
 *
 */
public class Project implements Serializable{

	private static final long serialVersionUID = 1L;
	/**表示项目的id对应表中的物理主键*/
	//UUID.randomUUID().toString();
	private Integer id;
	/**项目名称(例如纽约马拉松)*/
	private String name;
	/**项目编码(类似产品的序列号,具备业务特征)*/
	private String code;
	/**项目开始时间*/
	@DateTimeFormat(pattern="yyyy/MM/dd")
	private Date beginDate; 
	/**项目结束时间*/
	@DateTimeFormat(pattern="yyyy/MM/dd")
	private Date endDate;
	/**项目的有效性*/
	private Integer valid;
	/**项目备注*/
	private String note;
	/**项目的创建时间*/
	private Date createdTime;
	/**项目的修改时间*/
	private Date modifiedTime;
	/**项目的创建人*/
	private String createdUser;
	/**项目的修改人*/
	private String modifiedUser;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	@JsonSerialize(using=DateJsonTypeConvert.class)//如果是linux系统则起作用
	public Date getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}
	@JsonSerialize(using=DateJsonTypeConvert.class)//如果是linux系统则起作用
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Integer getValid() {
		return valid;
	}
	public void setValid(Integer valid) {
		this.valid = valid;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	
	
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	
	public Date getModifiedTime() {
		return modifiedTime;
	}
	public void setModifiedTime(Date modifiedTime) {
		this.modifiedTime = modifiedTime;
	}
	public String getCreatedUser() {
		return createdUser;
	}
	
	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}
	public String getModifiedUser() {
		return modifiedUser;
	}
	
	public void setModifiedUser(String modifiedUser) {
		this.modifiedUser = modifiedUser;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public String toString() {
		return "Project [id=" + id + ", name=" + name + ", code=" + code + ", beginDate=" + beginDate + ", endDate="
				+ endDate + ", valid=" + valid + ", note=" + note + ", createdTime=" + createdTime + ", modifiedTime="
				+ modifiedTime + ", createdUser=" + createdUser + ", modifiedUser=" + modifiedUser + "]";
	}
}
