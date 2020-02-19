package com.lixinyu.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lixinyu.common.CmsConstant;
import com.lixinyu.common.CmsMd5Util;
import com.lixinyu.common.CookieUtil;
import com.lixinyu.common.JsonResult;
import com.lixinyu.commonUtil.StringUtil;
import com.lixinyu.pojo.Article;
import com.lixinyu.pojo.Bookmark;
import com.lixinyu.pojo.Channel;
import com.lixinyu.pojo.Comment;
import com.lixinyu.pojo.User;
import com.lixinyu.service.ArticleService;
import com.lixinyu.service.UserService;


@Controller
@RequestMapping("/user/")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private ArticleService articleService;
	/**
	 * @Title: login   
	 * @Description: 用户登录界面   
	 * @param: @return      
	 * @return: Object      
	 * @throws
	 */
	@RequestMapping(value="login",method=RequestMethod.GET)
	public Object login() {

		return "/user/login";
	}
	/**
	 * @Title: login   
	 * @Description: 用户登录接口   
	 * @param: @param user
	 * @param: @param session
	 * @param: @return      
	 * @return: Object    
	 * @throws
	 */
	@RequestMapping(value="login",method=RequestMethod.POST)
	@ResponseBody
	public Object login(User user,HttpSession session,HttpServletResponse response) {
		//判断用户名和密码
		if(StringUtil.isBlank(user.getUsername()) || StringUtil.isBlank(user.getPassword())) {
			return JsonResult.fail(1000, "用户名和密码不能为空");
		}
		//查询用户
		User userInfo = userService.getByUsername(user.getUsername());
		//用户为空
		if(userInfo==null) {
			return JsonResult.fail(1000, "用户名或密码错误");
		}
		if(userInfo.getLocked()==1) {
			return JsonResult.fail(1000, "用户被禁用");
		}
		//判断密码
		String string2md5 = CmsMd5Util.string2MD5(user.getPassword());
		if(string2md5.equals(userInfo.getPassword())) {
			session.setAttribute(CmsConstant.UserSessionKey, userInfo);
			if("1".equals(user.getIsMima())) {
				int maxAge=1000*60*60*24;
				CookieUtil.addCookie(response,"username", user.getUsername(), null,null, maxAge);
			}
			return JsonResult.sucess();
		}
		return JsonResult.fail(1000, "用户名或密码错误");
	}
	/**
	 * @Title: logout   
	 * @Description: TODO(描述这个方法的作用)   
	 * @param: @param response
	 * @param: @param session
	 * @param: @return      
	 * @return: Object      
	 * @throws
	 */
	@RequestMapping("logout")
	public Object logout(HttpServletResponse response,HttpSession session) {
		session.removeAttribute(CmsConstant.UserSessionKey);
		CookieUtil.addCookie(response,"username",null,null,null,0);
		return "redirect:/";
	}
	/**
	 * @Title: register   
	 * @Description: 用户注册页面   
	 * @param: @return      
	 * @return: Object      
	 * @throws
	 */
	@RequestMapping(value="register",method=RequestMethod.GET)
	public Object register() {
		return "/user/register";
	}
	/**
	 * @Title: register   
	 * @Description: 用户注册接口   
	 * @param: @param user
	 * @param: @param session
	 * @param: @return      
	 * @return: Object      
	 * @throws
	 */
	@RequestMapping(value="register",method=RequestMethod.POST)
	public @ResponseBody Object register(User user,HttpSession session) {
		//判断用户名是否存在
			boolean result = userService.isExist(user.getUsername());
			if(result) {
				return JsonResult.fail(10001, "用户名已存在");
			}
			//用户注册
			boolean register = userService.register(user);
			if(register) {
				return JsonResult.sucess();
			}
		return JsonResult.fail(500, "未知错误");
	}
	@RequestMapping("center")
	public String center(HttpServletResponse response,HttpSession session) {
		return "user/center";
	}
	
	
	/**
	 * @Title: settings   
	 * @Description: 设置用户信息   
	 * @param: @param response
	 * @param: @param session
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	@RequestMapping(value="settings",method=RequestMethod.GET)
	public String settings(HttpServletResponse response,HttpSession session,Model model) {
		User userInfo = (User)session.getAttribute(CmsConstant.UserSessionKey);
		/** 查询用户信息 **/
		User user = userService.getByUsername(userInfo.getUsername());
		model.addAttribute("user", user);
		return "user/settings";
	}
	
	/**
	 * @Title: settings   
	 * @Description: 保存用户信息  
	 * @param: @param user
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	@RequestMapping(value="settings",method=RequestMethod.POST)
	@ResponseBody
	public JsonResult settings(User user,HttpSession session) {
		//修改用户信息
		boolean result = userService.update(user);
		if(result) {
			//跟新session中的用户信息
			User userInfo = userService.getById(user.getId());
			session.setAttribute(CmsConstant.UserSessionKey, userInfo);
			return JsonResult.sucess();
		}
		return JsonResult.fail(100002, "修改失败");
	}
	
	@RequestMapping("comment")
	public String comment(HttpServletResponse response,HttpSession session) {
		return "user/comment";
	}
	
	@RequestMapping("article")
	public String article(Article article,Model model,HttpSession session,
			@RequestParam(value="pageNum",defaultValue="1") int pageNum,@RequestParam(value="pageSize",defaultValue="3") int pageSize) {
		//设置用户Id
				User userInfo = (User)session.getAttribute(CmsConstant.UserSessionKey);
				article.setUserId(userInfo.getId());
				//查询文章
		PageInfo<Article> pageInfo = articleService.getPageInfo(article,pageNum,pageSize);
		model.addAttribute("pageInfo", pageInfo);
		List<Channel> channelList = articleService.getChannelList();
		model.addAttribute("channelList", channelList);
		return "user/article";
	}
	
	/**
	 * 评论列表
	 */
	@RequestMapping(value="comment",method=RequestMethod.GET)
	public String comment(Model m,@RequestParam(value="pageNum",defaultValue="1") int pageNum,@RequestParam(value="pageSize",defaultValue="3") int pageSize,HttpSession session) {
		User userInfo = (User)session.getAttribute(CmsConstant.UserSessionKey);
		PageHelper.startPage(pageNum, pageSize);
		List<Comment> list=articleService.comment(userInfo.getId());
		PageInfo pageInfo=new PageInfo(list);
		m.addAttribute("pageInfo",pageInfo);
		m.addAttribute("list",list);
		return "user/comment";
	}
	
	@ResponseBody
	@RequestMapping("deleteComment")
	public Object deleteComment(String ids) {
		int rs=articleService.deleteComment(ids);
		return rs>0;
	}
	
	/**
	 * @Title: isLogin   
	 * @Description: 验证用户是否登录   
	 * @param: @param session
	 * @param: @return      
	 * @return: Object      
	 * @throws
	 */
	@RequestMapping(value="isLogin",method=RequestMethod.POST)
	public @ResponseBody Object isLogin(HttpSession session) {
		Object userInfo = session.getAttribute(CmsConstant.UserSessionKey);
		if(userInfo!=null) {
			return JsonResult.sucess();
		}
		return JsonResult.fail(CmsConstant.unLoginErrorCode, "未登录");
	}
	/**
	 * 
	* @Title: bookmark 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param m
	* @param @param pageNum
	* @param @param pageSize
	* @param @param session
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	@RequestMapping(value="bookmark",method=RequestMethod.GET)
	public String bookmark(Model m,@RequestParam(value="pageNum",defaultValue="1") int pageNum,@RequestParam(value="pageSize",defaultValue="3") int pageSize,HttpSession session) {
		User userInfo = (User)session.getAttribute(CmsConstant.UserSessionKey);
		PageHelper.startPage(pageNum, pageSize);
		List<Bookmark> list=articleService.bookmark(userInfo.getId());
		System.out.println(list);
		PageInfo pageInfo=new PageInfo(list);
		m.addAttribute("pageInfo",pageInfo);
		m.addAttribute("list",list);
		return "user/bookmark";
	}
	
	@ResponseBody
	@RequestMapping("bookmarkAdd")
	public Object bookmarkAdd(String url,HttpSession session,Integer id) {
		User userInfo = (User)session.getAttribute(CmsConstant.UserSessionKey);
		if(userInfo==null) {
			return JsonResult.fail(CmsConstant.unLoginErrorCode, "用户未登录");
		}
		Bookmark bookmark = new Bookmark();
		bookmark.setUser_id(userInfo.getId());
		bookmark.setUrl(url);
		String created =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		bookmark.setCreated(created);
		
		Article article = articleService.getById(id);
		System.out.println(article.getTitle());
		bookmark.setText(article.getTitle());
		
		boolean flag = articleService.bookmarkAdd(bookmark);
		return flag;
		
		
	}
	
	@ResponseBody
	@RequestMapping("bookmarkDel")
	public Object bookmarkDel(HttpSession session,@RequestParam("id")Integer id) {
		User userInfo = (User)session.getAttribute(CmsConstant.UserSessionKey);
		Bookmark bookmark = articleService.selectBookMarkById(id);
		System.out.println(userInfo.getId());
		System.out.println(bookmark.getUser_id());
		if(bookmark.getUser_id().equals(userInfo.getId())) {
			boolean flag=articleService.bookmarkDel(id);
			return flag;
		}else {
			System.err.println("您无权删除此信息");
			return false;
		}
	}
}
