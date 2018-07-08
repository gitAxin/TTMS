package cn.tedu.ttms.common.web;
/**
 * 通过此对象封装控制层返回的结果
 * @author 天大java
 *
 */
public class JsonResult {
	
	
	/**表示成功*/
	public static final int SUCCESS=1;
	/**表示失败*/
	public static final int ERROR=0;
	
	/**状态 */
	private int state;
	/**信息(给用户提示的信息)*/
	private String message;
	/**具体业务数据*/
	private Object data;
	
	
	public JsonResult(){
		this.state=SUCCESS;
		this.message="OK";
	}
	
	public JsonResult(Object data){
		this();//调用当前类的构造方法
		this.data=data;
	}
	
	public JsonResult(Throwable e){
		this.state=ERROR;
		this.message=e.getMessage();
	}

	public int getState() {
		return state;
	}


	public void setState(int state) {
		this.state = state;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public Object getData() {
		return data;
	}


	public void setData(Object data) {
		this.data = data;
	}


	public static int getSuccess() {
		return SUCCESS;
	}


	public static int getError() {
		return ERROR;
	}
	
	

}
