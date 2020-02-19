package com.lixinyu.controller;

import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.pagehelper.PageInfo;
import com.lixinyu.pojo.Article;
import com.lixinyu.pojo.Category;
import com.lixinyu.pojo.Channel;
import com.lixinyu.pojo.Slide;
import com.lixinyu.pojo.User;
import com.lixinyu.service.ArticleService;
import com.lixinyu.service.SlideService;
import com.lixinyu.service.UserService;

@Controller
public class IndexController {
	@Autowired
	private ArticleService articleService;
	@Autowired
	private UserService userService;
	@Autowired
	private SlideService slideService;
	@Autowired
	private KafkaTemplate<String,String> kafkaTemplate;
	@Autowired
	private RedisTemplate redisTemplate;
	
	@RequestMapping(value="/")
	public String index(Model model) {
		return index(1, model);
	}

	@RequestMapping(value="/hot/{pageNum}.html")
	public String index(@PathVariable Integer pageNum, Model model) {
		/** 频道 */
		List<Channel> channelList = articleService.getChannelList();
		model.addAttribute("channelList", channelList);
		/** 轮播图 */
		List<Slide> slideList = slideService.getAll();
		model.addAttribute("slideList", slideList);
		/** 最新文章 **/
		List<Article> newArticleList = articleService.getNewList(6);
		model.addAttribute("newArticleList", newArticleList);
		/** 热点文章 **/
		if(pageNum==null) {
			pageNum=1;
		}
		PageInfo<Article> pageInfo =  articleService.getHotList(pageNum);
		List<Article> list = pageInfo.getList();
		for (Article article : list) {
			HashMap<Object, Object> hashMap = new HashMap<>();
			hashMap.put("article", article);
			HashOperations opsForHash = redisTemplate.opsForHash();
			opsForHash.putAll("cms_article", hashMap);
			redisTemplate.expire("cms_article", 5, TimeUnit.MINUTES);
		}
		model.addAttribute("pageInfo", pageInfo);
		return "index";
	}
	@RequestMapping("/{channelId}/{cateId}/{pageNo}.html")
	public String channel(@PathVariable Integer channelId, Model model,
			@PathVariable Integer cateId,@PathVariable Integer pageNo) {
		/** 频道 */
		List<Channel> channelList = articleService.getChannelList();
		model.addAttribute("channelList", channelList);
		/** 最新文章 **/
		List<Article> newArticleList = articleService.getNewList(6);
		model.addAttribute("newArticleList", newArticleList);
		/** 分类 */
		List<Category> cateList = articleService.getCateListByChannelId(channelId);
		model.addAttribute("cateList", cateList);
		PageInfo<Article> pageInfo =  articleService.getListByChannelIdAndCateId(channelId,cateId,pageNo);
		model.addAttribute("pageInfo", pageInfo);
		return "index";
	}
	@RequestMapping("article/{id}.html")
	public String articleDetail(@PathVariable Integer id,Model model,HttpServletRequest request) throws UnknownHostException {
		/** 查询文章 **/
		Article article = articleService.getById(id);
		model.addAttribute("article", article);
		if(article.getStatus()==3) {
			return "article/error";
		}
		/** 查询用户 **/
		User user = userService.getById(article.getUserId());
		model.addAttribute("user", user);
		/** 查询相关文章 **/
		//String hostAddress = InetAddress.getLocalHost().getHostAddress();
		String remoteAddr = request.getRemoteAddr();
		ValueOperations opsForValue = redisTemplate.opsForValue();
		boolean key=redisTemplate.hasKey("hits_"+id+"_"+remoteAddr);
		if(!key) {
			articleService.hits(id.toString());
			opsForValue.set("hits_"+id+"_"+remoteAddr, null,10,TimeUnit.SECONDS);
		}
		List<Article> articleList = articleService.getListByChannelId(article.getChannelId(),id,10);
		model.addAttribute("articleList", articleList);
		for (Article article2 : articleList) {
			model.addAttribute("hits",article.getHits()+1);
		}
		return "article/detail";
	}
}
