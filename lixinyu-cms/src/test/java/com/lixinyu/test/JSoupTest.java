package com.lixinyu.test;



import java.io.File;
import java.io.IOException;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import com.lixinyu.util.FileUtil;

public class JSoupTest {
	
	@Test
	public void test163() throws IOException {
		// 记录文章数
		int count = 0;
		// 获取连接对象
		Connection connect = Jsoup.connect("http://news.163.com/rank/");
		// 获取文档对象
		Document document = connect.get();
		// 获取当前文档的所有超链接
		Elements ahrefs = document.select("a[href]");
		
		//本地文件存储的路径
		String file_path="E:\\1709DJsoup";
		
		//如果没有当前文件夹的路径，则创建
		File file = new File(file_path);
		
		if(!file.exists()) {
			file.mkdir();
		}
		
		// 遍历元素对象
		for (Element href : ahrefs) {
			// 超链接的url地址
			String url = href.attr("href");
			// 定义表达式 https://news.163.com ***** html

//			String regex = "https://news\\\\.163\\\\.com.*html$";
			
			//以https://news.163.com开头，以html结尾
			//https://news\\.163\\.com.*html$
			// 特殊要求  
			if (url != null && url.startsWith("https://auto.163.com/") && url.endsWith("html")) {
//			if (url != null && Pattern.matches(regex, url)) {
				// 连接的文本内容
				String title = href.text();
				
				
				// 获取文章的文档对象
				Document articleDoc = Jsoup.connect(url).get();
				// 获取文章的内容元素对象
				Element articleContentElement = articleDoc.getElementById("endText");
				// 判断元素是否为空
				if (articleContentElement != null) {
					System.out.println(url + "@@@@@@@@@" + title);
					
					// 获取纯文本内容
					String content = articleContentElement.text();
					
					//去除标题中的特殊符号
					title = title.replace("?", "").replace("\"", "").replace(":", "").replace("/", "").replace("\\", "").replace("|", "");
					
					//写入到文件中
					FileUtil.writeFile(file_path + "\\" + title + ".txt", content, "utf8");
					
					count++;
				}
			}
		}
		System.out.println("首页中找到了复合条件的网址有：" + count + "篇文章");
	}
	
	
	@Test
	public void testXS() throws IOException {
		// 记录文章数
		int count = 0;
		// 获取连接对象
		Connection connect = Jsoup.connect("http://www.xbiquge.la/10/10489/");
		// 获取文档对象
		Document document = connect.get();
		// 获取当前文档的所有超链接
		Elements ahrefs = document.select("a[href]");
		
		//本地文件存储的路径
		String file_path="D:\\1709DJsoup";
		
		//如果没有当前文件夹的路径，则创建
		File file = new File(file_path);
		
		if(!file.exists()) {
			file.mkdir();
		}
		
		// 遍历元素对象
		for (Element href : ahrefs) {
			// 超链接的url地址
			String url = href.attr("href");
			// 定义表达式 https://news.163.com ***** html

//			String regex = "https://news\\\\.163\\\\.com.*html$";
			
			//以https://news.163.com开头，以html结尾
			//https://news\\.163\\.com.*html$
			// 特殊要求  
			if (url != null && url.startsWith("/10/10489") && url.endsWith("html")) {
//			if (url != null && Pattern.matches(regex, url)) {
				// 连接的文本内容
				String title = href.text();
				url = "http://www.xbiquge.la" + url;
				
				// 获取文章的文档对象
				Document articleDoc = Jsoup.connect(url).get();
				// 获取文章的内容元素对象
				Element articleContentElement = articleDoc.getElementById("content");
				// 判断元素是否为空
				if (articleContentElement != null) {
					System.out.println(url + "@@@@@@@@@" + title);
					
					// 获取纯文本内容
					String content = articleContentElement.text();
					
					//去除标题中的特殊符号
					title = title.replace("?", "").replace("\"", "").replace(":", "").replace("/", "").replace("\\", "").replace("|", "");
					
					//写入到文件中
					FileUtil.writeFile(file_path+"\\" + title + ".txt", content, "utf8");
					
					count++;
				}
			}
		}
		System.out.println("首页中找到了复合条件的网址有：" + count + "篇文章");
	}
}
