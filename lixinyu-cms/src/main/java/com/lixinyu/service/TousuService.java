package com.lixinyu.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lixinyu.commonUtil.DateUtil;
import com.lixinyu.dao.TousuDao;
import com.lixinyu.pojo.Tousu;
@Service
public class TousuService {
	@Autowired
	private TousuDao tousuDao;
	@Autowired
	private ArticleService articleService;
	/**
	 * @Title: add   
	 * @Description: 添加评论   
	 * @param: @param comment
	 * @param: @return      
	 * @return: boolean      
	 * @throws
	 */
	public boolean add(Tousu tousu) {
		String createdStr = DateUtil.dateTimeFormat.format(new Date());
		System.out.println(createdStr);
		tousu.setCreated(createdStr);
		articleService.addTousu(tousu.getArticleId());
		return tousuDao.insert(tousu)>0;
	}
	
}
