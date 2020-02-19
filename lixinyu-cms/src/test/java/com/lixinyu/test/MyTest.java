package com.lixinyu.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetAddress;
import java.sql.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.gson.Gson;
import com.lixinyu.commonUtil.DateUtil;
import com.lixinyu.commonUtil.FileUtil;
import com.lixinyu.commonUtil.RandomUtil;
import com.lixinyu.commonUtil.StringUtil;
import com.lixinyu.dao.ArticleDao;
import com.lixinyu.dao.ArticleRepository;
import com.lixinyu.dao.UserDao;
import com.lixinyu.pojo.Article;
import com.lixinyu.pojo.Bookmark;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring-beans.xml")
public class MyTest {
	@Autowired
	private UserDao userdao;
	@Autowired
	private ArticleDao articleDao;
	@Autowired
	private ArticleRepository articleRepository;
	@Resource
	private KafkaTemplate<String, String> kafkaTemplate;
	@Test
	public void list() {
		/*
		 * List<User> selectUser = userdao.selectUser(); System.out.println(selectUser);
		 */
		int random = RandomUtil.random(1,7);
		System.out.println(random);
	}
	
	@Test
	public void addArticleRepository() {
		Article article = new Article();
		List<Article> list = articleDao.select(article);
		articleRepository.saveAll(list);
	}
	
	
	  @Test 
	  public void add() throws FileNotFoundException { 
		  File file = new File("E:\\1709DJsoup");
		  File[] listFiles = file.listFiles(); 
		  for (File file2: listFiles) { 
			  System.out.println(file2);
			  Article article = new Article(); 
			  String readTextFileByLine =FileUtil.readTextFileByLine(file2);
			  if(readTextFileByLine.length()>140) {
				  String substring = readTextFileByLine.substring(0,140);
				  article.setContent(substring); 
			 } 
			  String replace =file2.getName().replace(".txt","");
			  article.setTitle(replace);
			  System.out.println(article.getTitle());
			  article.setPicture("E:\\v2-f0a8815514d59da49bfcdeff93872deb_r.jpg");
			  System.out.println(article.getPicture());
			  int random = RandomUtil.random(1,9);
			  article.setChannelId(random); 
			  System.out.println(article.getChannelId());
			  int random1 =RandomUtil.random(1,32);
			  article.setCategoryId(random1);
			  System.out.println(article.getCategoryId());
			  article.setUserId(182); 
			  System.out.println(article.getUserId());
			  article.setHits(0); 
			  System.out.println(article.getHits());
			  Date randomDate =null;
			  article.setCreated(randomDate);
			  System.out.println(article.getCreated());
			  article.setUpdated(randomDate);
			  System.out.println(article.getUpdated());
			  article.setCommentcnt(0);
			  System.out.println(article.getCommentcnt());
			  System.out.println(article); 
			  Gson g=new Gson();
			 // kafkaTemplate.sendDefault("articleAdd", g.toJson(article)); 
		}
	  
	 }
	 
	@Test
	public void jsoup() throws IOException {
		//JSoup.sohu();
		String hostAddress = InetAddress.getLocalHost().getHostAddress();
		System.out.println(hostAddress);
	}
	
	/*@Test
	public void add() {
		File file = new File("E:\\1709DJsoup");
		File[] listFiles = file.listFiles();
		for (File file2 : listFiles) {
			
			String name = file.getName();
			System.out.println(name.replace(".txt", ""));
			Article article = new Article();
			//article.setTitle(name);
		}
	}*/
	
	
	@Test
	public void bookmarkTest() {
		int i = articleDao.bookmarkAdd(new Bookmark(null,"sssss","www.ccc.com",182,"2020-02-18 10:38:45", null));
		System.out.println(i>0);
		List<Bookmark> bookmark = articleDao.bookmark(182);
		System.out.println(bookmark);
		
		boolean httpUrl = StringUtil.isHttpUrl("ssss");
		System.out.println(httpUrl);
		
		int bookmarkDel = articleDao.bookmarkDel(3);
		System.out.println(bookmarkDel>0);
	}
}
