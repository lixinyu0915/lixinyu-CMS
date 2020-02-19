package com.lixinyu.pojo;

public class ArticleTag {
	private Integer aid;
	
	private Integer tid;

	public Integer getAid() {
		return aid;
	}

	public void setAid(Integer aid) {
		this.aid = aid;
	}

	public Integer getTid() {
		return tid;
	}

	public void setTid(Integer tid) {
		this.tid = tid;
	}

	@Override
	public String toString() {
		return "Article_tag [aid=" + aid + ", tid=" + tid + "]";
	}

	public ArticleTag(Integer aid, Integer tid) {
		super();
		this.aid = aid;
		this.tid = tid;
	}

	public ArticleTag() {
		super();
	}
	
	
	
}
