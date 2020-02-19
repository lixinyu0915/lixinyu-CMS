package com.lixinyu.pojo;
/**   
* @Title: Tousu.java 
* @Package com.wenjiaquan.cms.pojo 
* @Description: TODO(用一句话描述该文件做什么) 
* @author A18ccms A18ccms_gmail_com   
* @date 2019年12月23日 下午1:46:33 
* @version V1.0   
*/
public class Tousu {
	private Integer id;

    private Integer articleId;

    private Integer userId;

    private String content;

    private String created;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getArticleId() {
		return articleId;
	}

	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}
    
}
