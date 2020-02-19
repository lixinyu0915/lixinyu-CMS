package com.lixinyu.dao;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.lixinyu.pojo.Article;

public interface ArticleRepository extends ElasticsearchRepository<Article, Integer>{
	
}
