package com.lixinyu.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lixinyu.dao.ArticleDao;
import com.lixinyu.dao.CategoryDao;
import com.lixinyu.dao.ChannelDao;
import com.lixinyu.pojo.Article;
import com.lixinyu.pojo.Category;
import com.lixinyu.pojo.Channel;
import com.lixinyu.service.ArticleService;
@Service
public class ArticleServiceImpl implements ArticleService {
	@Autowired
	private ArticleDao articleDao;
	@Autowired
	private ChannelDao channelDao;
	@Autowired
	private CategoryDao categoryDao;
	
	
	@Override
	public PageInfo<Article> getPageInfo(Article article, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<Article> articleList = articleDao.select(article);
		return new PageInfo<>(articleList);
	}

	@Override
	public boolean updateStatus(Integer id, int status) {
		return articleDao.updateStatus(id, status)>0;
	}

	@Override
	public boolean addHot(Integer id) {
		return articleDao.addHot(id)>0;
	}

	@Override
	public List<Channel> getChannelList() {
		return channelDao.select(null);
	}

	@Override
	public Article getById(Integer id) {
		return articleDao.selectById(id);
	}

	@Override
	public boolean save(Article article) {
		if(article.getId()==null) {
			article.setDeleted(0);
			article.setCreated(new Date());
			article.setUpdated(new Date());
			article.setCommentcnt(0);
			article.setHits(0);
			article.setHot(0);
			articleDao.insert(article);
		}else {
			article.setUpdated(new Date());
			articleDao.update(article);
		}
		return true;
	}

	@Override
	public List<Category> getCateListByChannelId(Integer channelId) {
		return categoryDao.selectListByChannelId(channelId);
	}

	@Override
	public boolean delByIds(String ids) {
		return articleDao.updateDeletedByIds(ids)>0;
	}

	@Override
	public boolean isAllCheck(String ids) {
		List<Article> articleList = articleDao.selectByIds(ids);
		for (Article article:articleList) {
			if(article.getStatus()==1) {
				return false;
			}
		}
		return true;
	}

	@Override
	public List<Article> getListByChannelId(Integer channelId, Integer aritcleId, int num) {
		return articleDao.selectListByChannelId(channelId,aritcleId,num);
	}

	@Override
	public PageInfo<Article> getHotList(int pageNum) {
		PageHelper.startPage(pageNum, 6);
		List<Article> articleList = articleDao.selectByHot();
		
		return new PageInfo<>(articleList);
	}

	@Override
	public PageInfo<Article> getListByChannelIdAndCateId(Integer channelId, Integer cateId, Integer pageNum) {
		PageHelper.startPage(pageNum, 6);
		List<Article> articleList = articleDao.selectListByChannelIdAndCateId(channelId,cateId);
		 return new PageInfo<>(articleList);
	}

	@Override
	public List<Article> getNewList(int num) {
		return articleDao.selectNewList(num);
	}

	@Override
	public void addTousu(Integer id) {
		// TODO Auto-generated method stub
		articleDao.addTousu(id);
	}

}
