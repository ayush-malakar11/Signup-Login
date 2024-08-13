package com.amstech.restaurant.service;

import java.util.List;

import com.amstech.restaurant.dao.UserDao;
import com.amstech.restaurant.dto.UserDto;

public class UserService {

	private UserDao userDao;

	public UserService(UserDao userDao) {
		this.userDao = userDao;
	}

	public int save(UserDto userDto) throws Exception {
		// pre process
		int count = userDao.save(userDto);
		// post process(send welcome email, sms)
		return count;
	}

	public int update(UserDto userDto) throws Exception {
		return userDao.update(userDto);
	}

	public UserDto findById(int id) throws Exception {
		return userDao.findById(id);
	}

	public UserDto findByPhoneNumber(String phone_no) throws Exception {
		return userDao.findByPhoneNumber(phone_no);
	}

	public UserDto findByMobileEmailPassword(String username, String password) throws Exception {
		return userDao.findByMobileEmailPassword(username, password);
	}

	public List<UserDto> findAll() throws Exception {
		return userDao.findAll();
	}

	public int deleteById(int id) throws Exception {
		return userDao.deleteById(id);
	}
	
}

