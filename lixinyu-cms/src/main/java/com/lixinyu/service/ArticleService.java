package com.lixinyu.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.lixinyu.pojo.Article;
import com.lixinyu.pojo.Bookmark;
import com.lixinyu.pojo.Category;
import com.lixinyu.pojo.Channel;
import com.lixinyu.pojo.Comment;

public interface ArticleService {
	/**
	 * @Title: getPageInfo   
	 * @Description: 分页列表   
	 * @param: @param user
	 * @param: @param pageNum
	 * @param: @param pageSize
	 * @param: @return      
	 * @return: PageInfo<Article>      
	 * @throws
	 */
	PageInfo<Article> getPageInfo(Article article, int pageNum,int pageSize); 
	/**
	 * @Title: updateStatus   
	 * @Description: 修改文章状态   
	 * @param: @param id
	 * @param: @param status
	 * @param: @return      
	 * @return: boolean      
	 * @throws
	 */
	public boolean updateStatus(Integer id,int status);
	/**
	 * @Title: addHot   
	 * @Description: 加热，每次给hot值加1   
	 * @param: @param id
	 * @param: @return      
	 * @return: boolean      
	 * @throws
	 */
	public boolean addHot(Integer id);
	/**
	 * @Title: getChannelList   
	 * @Description: 查询频道列表   
	 * @param: @return      
	 * @return: List<Channel>      
	 * @throws
	 */
	public List<Channel> getChannelList();
	
	/**
	 * @Title: getById   
	 * @Description: 根据id查询文章 
	 * @param: @param id
	 * @param: @return      
	 * @return: Article      
	 * @throws
	 */
	public Article getById(Integer id);
	/**
	 * @Title: save   
	 * @Description: 保存或修改文章 
	 * @param: @param article
	 * @param: @return      
	 * @return: boolean      
	 * @throws
	 */
	boolean save(Article article);
	/**
	 * @Title: getCateListByChannelId   
	 * @Description: 根据频道Id查询分类列表  
	 * @param: @param channelId
	 * @param: @return      
	 * @return: List<Category>      
	 * @throws
	 */
	List<Category> getCateListByChannelId(Integer channelId);
	
	/**
	 * @Title: delByIds   
	 * @Description: 批量删除   
	 * @param: @param ids
	 * @param: @return      
	 * @return: boolean      
	 * @throws
	 */
	boolean delByIds(String ids);
	/**
	 * @Title: isAllCheck   
	 * @Description: 根据Ids判断文章是否已审核   
	 * @param: @param ids
	 * @param: @return      
	 * @return: boolean      
	 * @throws
	 */
	boolean isAllCheck(String ids);
	
	/**
	 * @Title: getListByChannelId   
	 * @Description: 根据频道Id查询文章列表   
	 * @param: @param channelId
	 * @param: @return      
	 * @return: List<Article>      
	 * @throws
	 */
	List<Article> getListByChannelId(Integer channelId, Integer id, int num);
	/**
	 * @Title: getHotList   
	 * @Description: 热点文章   
	 * @param: @param pageNum
	 * @param: @return      
	 * @return: PageInfo<Article>      
	 * @throws
	 */
	PageInfo<Article> getHotList(int pageNum);
	/**
	 * @Title: getListByChannelIdAndCateId   
	 * @Description: 根据频道Id和分类Id查询文章列表  
	 * @param: @param channelId
	 * @param: @param cateId
	 * @param: @param pageNo
	 * @param: @return      
	 * @return: PageInfo<Article>      
	 * @throws
	 */
	PageInfo<Article> getListByChannelIdAndCateId(Integer channelId, Integer cateId, Integer pageNo);

	/**
	 * @Title: getNewList   
	 * @Description: 查询指定条数的最新文章
	 * @param: @param num
	 * @param: @return      
	 * @return: List<Article>      
	 * @throws
	 */
	List<Article> getNewList(int num);
	
	
	List<Comment> comment(int id);
	int deleteComment(String ids);
	
	void addTousu(Integer articleid);
	
	int kafkaSave(Article article);
	void hits(String id);
	
	List<Bookmark> bookmark(Integer id);
	
	boolean bookmarkAdd(Bookmark bookmark);
	
	Bookmark selectBookMarkById(Integer id);
	boolean bookmarkDel(Integer id);
}
