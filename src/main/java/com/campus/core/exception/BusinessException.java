package com.campus.core.exception;
/**
 * 业务异常类    用户业务操作错误则会抛出此异常
 * @author 刘汉升
 *
 */
public class BusinessException extends RuntimeException{

	private static final long serialVersionUID = 4103212582387658649L;

    private String error_code; //异常代码  
      
    private Object[] data;//一些其他信息  
    
    private String original_url; // 原来访问的网址
    
    private String redirect_url; // 重定向地址
      
    public BusinessException() {  
        super();  
    }  
  
    public BusinessException(String message, Throwable throwable) {  
        super(message, throwable);  
    }  
  
    public BusinessException(String message) {  
        super(message);  
    }  
  
    public BusinessException(Throwable throwable) {  
        super(throwable);  
    }

	public String getError_code() {
		return error_code;
	}

	public void setError_code(String error_code) {
		this.error_code = error_code;
	}

	public Object[] getData() {
		return data;
	}

	public void setData(Object[] data) {
		this.data = data;
	}

	public String getOriginal_url() {
		return original_url;
	}

	public void setOriginal_url(String original_url) {
		this.original_url = original_url;
	}

	public String getRedirect_url() {
		return redirect_url;
	}

	public void setRedirect_url(String redirect_url) {
		this.redirect_url = redirect_url;
	}  
      
    
    
}
