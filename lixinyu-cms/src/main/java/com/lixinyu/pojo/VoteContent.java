package com.lixinyu.pojo;
/**   
* @Title: Vote_Content.java 
* @Package com.wenjiaquan.cms.pojo 
* @Description: TODO(用一句话描述该文件做什么) 
* @author A18ccms A18ccms_gmail_com   
* @date 2019年12月10日 下午6:52:05 
* @version V1.0   
*/
public class VoteContent {
	private Integer id;
	private String content;
	private String title;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public VoteContent() {
		super();
		// TODO Auto-generated constructor stub
	}

	public VoteContent(Integer id, String content, String title) {
		super();
		this.id = id;
		this.content = content;
		this.title = title;
	}

	@Override
	public String toString() {
		return "Vote_Content [id=" + id + ", content=" + content + ", title=" + title + "]";
	}
	
}
