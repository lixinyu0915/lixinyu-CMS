package com.lixinyu.controller.file;
/**   
* @Title: FileResult.java 
* @Package com.wenjiaquan.cms.controller.file 
* @Description: TODO(用一句话描述该文件做什么) 
* @author A18ccms A18ccms_gmail_com   
* @date 2019年12月13日 下午1:44:23 
* @version V1.0   
*/
public class FileResult {
	public FileResult(int error, String url) {
		super();
		this.error = error;
		this.url = url;
	}

	int error = 0;
	String url = "";

	public FileResult() {
		super();
	}

	public int getError() {
		return error;
	}

	public void setError(int error) {
		this.error = error;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
