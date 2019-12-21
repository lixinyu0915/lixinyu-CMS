package com.lixinyu.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lixinyu.dao.ArticleDao;
import com.lixinyu.dao.ArticleVoteDao;
import com.lixinyu.dao.CategoryDao;
import com.lixinyu.dao.ChannelDao;
import com.lixinyu.dao.CommentDao;
import com.lixinyu.dao.SettingsDao;
import com.lixinyu.dao.SlideDao;
import com.lixinyu.dao.TagDao;
import com.lixinyu.dao.UserDao;
import com.lixinyu.dao.VoteContentDao;
import com.lixinyu.pojo.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="/spring-beans.xml")
public class UserTests {
	@Autowired
	private UserDao userDao;
	@Autowired
	private ArticleDao articleDao;
	@Autowired
	private ArticleVoteDao articleVoteDao;
	@Autowired
	private CategoryDao categoryDao;
	@Autowired
	private ChannelDao channelDao;
	@Autowired
	private CommentDao commentDao;
	@Autowired
	private SettingsDao settingsDao;
	@Autowired
	private SlideDao slideDao;
	@Autowired
	private TagDao tagDao;
	@Autowired
	private VoteContentDao voteContentDao;
	
	@Test
	public void select() {
		List<User> userList = userDao.select(null);
		System.out.println(userList);
		
		User user = new User();
		user.setUsername("lisi");
		user.setPassword("123456");
		userDao.deleteByIds("170");
		
	}
}
