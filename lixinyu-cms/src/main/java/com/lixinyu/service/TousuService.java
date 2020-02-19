package com.lixinyu.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lixinyu.dao.TousuDao;
import com.lixinyu.pojo.Tousu;

@Service
@Transactional
public class TousuService {
	@Autowired
	private TousuDao tousuDao;
	@Autowired
	private ArticleService articleService;
	public boolean add(Tousu tousu) {
		// TODO Auto-generated method stub
		String format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		tousu.setCreated(format);
		tousuDao.insert(tousu);
		articleService.addTousu(tousu.getArticleId());
		return true;
	}
}
