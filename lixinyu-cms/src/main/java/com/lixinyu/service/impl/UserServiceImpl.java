package com.lixinyu.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lixinyu.common.CmsMd5Util;
import com.lixinyu.dao.UserDao;
import com.lixinyu.pojo.User;
import com.lixinyu.service.UserService;

@Transactional
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userdao;
	
	public List<User> selectUser(){
		List<User> list=userdao.selectUser();
		return list;
	}

	@Override
	public int logins(User user) {
		// TODO Auto-generated method stub
		return userdao.logins(user);
	}
	@Override
	public boolean register(User user) {
		// TODO Auto-generated method stub
		user.setCreateTime(new Date());
		user.setUpdateTime(new Date());
		user.setPassword(CmsMd5Util.string2MD5(user.getPassword()));
		user.setLocked(0);
		user.setScore(0);
		user.setRole("0");
		return userdao.insert(user)>0;
	}

	@Override
	public User getByUsername(String username) {
		// TODO Auto-generated method stub
		return userdao.selectByUsername(username);
	}

	@Override
	public boolean locked(Integer userId) {
		return userdao.updateLocked(userId,1)>0;
	}

	@Override
	public boolean unLocked(Integer userId) {
		return userdao.updateLocked(userId,0)>0;
	}

	@Override
	public int addScore(Integer userId, int score) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public PageInfo<User> getPageInfo(User user, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<User> userList = userdao.select(user);
		return new PageInfo<>(userList);
	}
	
	@Override
	public boolean update(User user) {
		user.setUpdateTime(new Date());
		return userdao.update(user)>0;
	}
	
	@Override
	public boolean isExist(String username) {
		return getByUsername(username)!=null;
	}
	
	@Override
	public User getById(Integer id) {
		return userdao.selectById(id);
	}
}
