package cn.tedu.ttms.attachement.entity;

import java.io.Serializable;
import java.util.Date;

/**附件对象*/
public class Attachement implements Serializable {

	private static final long serialVersionUID = -7210213415966997694L;
	/**附件ID*/
	private Integer id;
	/**附件标题*/
	private String title;
	/**文件真实名称*/
	private String fileName;
	/**文件内容类型*/
	private String contentType;
	/**文件在服务器上的存储地址*/
	private String filePath;
	/**文件摘要信息*/
	private String fileDisgest;
	/**附件类型(1:产品附件,2:渠道商附件,3:供应商附件)*/
	private Integer athType;
	/**附件具体关联对象(例如属于具体的哪个产品)*/
	private Integer belongId;
	/**附件的创建用户*/
	private String createdUser;
	/**附件的创建时间*/
	private Date createdTime;
	/**附件的修改用户*/
	private String modifiedUser;
	/**附件的修改时间*/
	private Date modifiedTime;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getFileDisgest() {
		return fileDisgest;
	}
	public void setFileDisgest(String fileDisgest) {
		this.fileDisgest = fileDisgest;
	}
	public Integer getAthType() {
		return athType;
	}
	public void setAthType(Integer athType) {
		this.athType = athType;
	}
	public Integer getBelongId() {
		return belongId;
	}
	public void setBelongId(Integer belongId) {
		this.belongId = belongId;
	}
	public String getCreatedUser() {
		return createdUser;
	}
	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	public String getModifiedUser() {
		return modifiedUser;
	}
	public void setModifiedUser(String modifiedUser) {
		this.modifiedUser = modifiedUser;
	}
	public Date getModifiedTime() {
		return modifiedTime;
	}
	public void setModifiedTime(Date modifiedTime) {
		this.modifiedTime = modifiedTime;
	}
	@Override
	public String toString() {
		return "Attachement [id=" + id + ", title=" + title + ", fileName=" + fileName + ", contentType=" + contentType
				+ ", filePath=" + filePath + ", fileDisgest=" + fileDisgest + ", athType=" + athType + ", beblongId="
				+ belongId + ", createdUser=" + createdUser + ", createdTime=" + createdTime + ", modifiedUser="
				+ modifiedUser + ", modifiedTime=" + modifiedTime + "]";
	}
	
	
	
}
