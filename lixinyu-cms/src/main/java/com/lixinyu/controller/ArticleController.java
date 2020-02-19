package com.lixinyu.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.lixinyu.common.CmsConstant;
import com.lixinyu.common.JsonResult;
import com.lixinyu.pojo.Article;
import com.lixinyu.pojo.Category;
import com.lixinyu.pojo.Channel;
import com.lixinyu.pojo.Slide;
import com.lixinyu.pojo.User;
import com.lixinyu.service.ArticleService;
import com.lixinyu.service.SlideService;
import com.lixinyu.util.ESUtils;

@Controller
@RequestMapping("/article/")
public class ArticleController {
	@Autowired
	private ArticleService articleService;
	@Autowired
	private ElasticsearchTemplate elasticsearchTemplate;
	@Autowired
	private SlideService slideService;
	
	private Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * @Title: add
	 * 
	 * @@ -22,13 +33,42 @@ @return: String @throws
	 */
	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String add(Integer id, Model model) {
		logger.info("articleId:{}", id);
		if (id != null) {
			Article article = articleService.getById(id);
			logger.info(article.toString());
			model.addAttribute("article", article);
			List<Category> cateList = articleService.getCateListByChannelId(article.getChannelId());
			model.addAttribute("cateList", cateList);
		}
		List<Channel> channelList = articleService.getChannelList();
		model.addAttribute("channelList", channelList);
		return "article/add";
	}

	@RequestMapping(value = "add", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult add(Article article, Model model, HttpSession session) {
		System.out.println(article);
		User userInfo = (User) session.getAttribute(CmsConstant.UserSessionKey);
		if (userInfo == null) {
			return JsonResult.fail(CmsConstant.unLoginErrorCode, "未登录");
		}
		article.setUserId(userInfo.getId());
		boolean result = articleService.save(article);
		return JsonResult.sucess(result);
	}

	/**
	 * @Title: getCateList @Description: 根据频道Id查询分类列表 @param: @param
	 * channelId @param: @param model @param: @param
	 * session @param: @return @return: JsonResult @throws
	 */
	@RequestMapping(value = "getCateList", method = RequestMethod.GET)
	@ResponseBody
	public JsonResult getCateList(Integer channelId, Model model, HttpSession session) {
		return JsonResult.sucess(articleService.getCateListByChannelId(channelId));
	}

	/**
	 * @Title: delByIds @Description: 批量删除 @param: @param
	 * ids @param: @return @return: JsonResult @throws
	 */
	@RequestMapping("delByIds")
	public @ResponseBody JsonResult delByIds(String ids) {
		if (ids == null) {
			return JsonResult.fail(10001, "请选择删除的文章");
		}
		// 已审核判断
		boolean isCheck = articleService.isAllCheck(ids);
		if (!isCheck) {
			return JsonResult.fail(10001, "请选择未审核的文章删除");
		}
		// 删除
		boolean result = articleService.delByIds(ids);
		if (result) {
			return JsonResult.sucess();
		}
		return JsonResult.fail(500, "未知错误");
	}
	
	@RequestMapping("search")
	public String searchRepository(Model m,String key,@RequestParam(defaultValue = "1")Integer pageNum,@RequestParam(defaultValue = "5")Integer pageSize) {
		long start = System.currentTimeMillis();
		AggregatedPage<Article> selectObjects = ESUtils.selectObjects(elasticsearchTemplate, Article.class, null, pageNum-1, pageSize, "id", new String[] {"title"}, key);
		long end = System.currentTimeMillis();
		System.err.println("查询所耗时长"+(end-start)+"毫秒");
		PageInfo<Article> pageInfo=new PageInfo<Article>(selectObjects.getContent());
		pageInfo.setPageNum(pageNum);
		m.addAttribute("pageInfo",pageInfo);
		/** 频道 */
		List<Channel> channelList = articleService.getChannelList();
		m.addAttribute("channelList", channelList);
		/** 最新文章 **/
		List<Article> newArticleList = articleService.getNewList(6);
		m.addAttribute("newArticleList", newArticleList);
		/** 轮播图 */
		
		
		List<Slide> slideList = slideService.getAll();
		m.addAttribute("slideList", slideList);
		m.addAttribute("key",key);
		
		return "index";
	}
	
}
