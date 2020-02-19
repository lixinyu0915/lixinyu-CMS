package com.lixinyu.pojo;

import java.io.Serializable;
import java.sql.Date;

public class Bookmark implements Serializable {
	/**
	 *  @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

	private String text;

	private String url;

	private Integer user_id;

	private String created;
	
	private String username;

	@Override
	public String toString() {
		return "Bookmark [id=" + id + ", text=" + text + ", url=" + url + ", user_id=" + user_id + ", created="
				+ created + ", username=" + username + "]";
	}

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

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Bookmark(Integer id, String text, String url, Integer user_id, String created, String username) {
		super();
		this.id = id;
		this.text = text;
		this.url = url;
		this.user_id = user_id;
		this.created = created;
		this.username = username;
	}

	public Bookmark() {
		super();
	}

	
}
