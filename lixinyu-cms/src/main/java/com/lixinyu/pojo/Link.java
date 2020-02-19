package com.lixinyu.pojo;

import java.util.Date;

/**   
* @Title: Link.java 
* @Package com.wenjiaquan.cms.pojo 
* @Description: TODO(用一句话描述该文件做什么) 
* @author A18ccms A18ccms_gmail_com   
* @date 2019年12月21日 上午11:19:27 
* @version V1.0   
*/
public class Link {
	private Integer id;
	private String text;
	private String url;
	private Date created;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}

}
