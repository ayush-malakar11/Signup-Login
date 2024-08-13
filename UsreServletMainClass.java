package com.amstech.restaurant.servlet;

import java.util.List;

import com.amstech.restaurant.dao.UserDao;
import com.amstech.restaurant.dto.UserDto;
import com.amstech.restaurant.service.UserService;
import com.amstech.restaurant.servlet.UsreServletMainClass;
import com.amstech.restaurant.util.DBUtil;

public class UsreServletMainClass {

	// Temp Class
		private DBUtil dbUtil;
		private UserDao userDao;
		private UserService userService;

		public UsreServletMainClass() {
			this.dbUtil = new DBUtil();
			this.userDao = new UserDao(dbUtil);
			this.userService = new UserService(userDao);

		}

		public static void main(String[] args) {
			UsreServletMainClass main = new UsreServletMainClass();

//			main.save();
//			main.update();
//			main.findById();
//			main.findByPhoneNumber();
//			main.findByMobileEmailPassword();
			main.findAll();
		}

		public void save() {
			try {

				UserDto userDto = new UserDto();
				userDto .setName("Daksh"); // request.getParam('name');
				userDto.setEmail("daksh@gmail.com"); // request.getParam('firstname');
				userDto.setPhoneNumber("9992226699"); // request.getParam('firstname');
				userDto.setAddress("Bholaram"); // request.getParam('firstname');
				userDto.setCityId(2);
				userDto.setPassword("abc123"); // request.getParam('firstname');

				int count = userService.save(userDto);
				if (count > 0)
					System.out.println("User created successfully");
				else
					System.out.println("Failed to create user data..");
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		public void update() {
			try {
				UserDto userDTO = new UserDto();
				userDTO.setName("Bhaksh"); // request.getParam('firstname');
				userDTO.setEmail("bhaksh@gmail.com"); // request.getParam('firstname');
				userDTO.setAddress("Cholaram"); // request.getParam('firstname');
				userDTO.setId(6); // request.getParam('id');

				int count = userService.update(userDTO);
				if (count > 0) {
					System.out.println("User updated successfully");
				} else {
					System.out.println("failed to updated user.");
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		public void findById() {
			try {
				int id = 2; // request.getParam("id")

				UserDto userDto = userService.findById(id);
				if (userDto != null) {
					System.out.println("User found successfully");
					System.out.println("FistName: " + userDto.getName());
					System.out.println("LastName: " + userDto.getEmail());
				} else {
					System.out.println("failed to find user.");
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		public void findByPhoneNumber() {
			try {
				String phone_no = "3333333333"; // request.getParam("mobileNumber")

				UserDto userDto = userService.findByPhoneNumber(phone_no);
				if (userDto != null) {
					System.out.println("User found successfully");
					System.out.println("FistName: " + userDto.getName());
					System.out.println("LastName: " + userDto.getEmail());
					System.out.println("FistName: " + userDto.getPhoneNumber());
					System.out.println("LastName: " + userDto.getAddress());
					System.out.println("LastName: " + userDto.getPassword());
				} else {
					System.out.println("failed to find user.");
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		public void findByMobileEmailPassword() {
			try {
//				String phone_no = "3333333333"; // request.getParam("username")
//				String e_mail = "ayush@gmail.com";
				String userName = "3333333333";
				String password = "ayush"; // request.getParam("password")

				UserDto userDto = userService.findByMobileEmailPassword(userName, password);
				if (userDto != null) {
					System.out.println("User found successfully");
					System.out.println("FistName: " + userDto.getName());
					System.out.println("LastName: " + userDto.getPhoneNumber());
				} else {
					System.out.println("failed to find user.");
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		public void findAll() {
			try {

				List<UserDto> userDtoList = userService.findAll();
				if (!userDtoList.isEmpty()) {
					System.out.println("User found successfully");
					System.out.println("UserDTO List size: " + userDtoList.size());

				for (UserDto userDTO : userDtoList) {
						System.out.println("FistName: " + userDTO.getName());
						System.out.println("LastName: " + userDTO.getEmail());
					}

				} else {
					System.out.println("failed to find user.");
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		public void deleteById() {
			try {
				int id = 2; // request.getParam("id")

				int count = userService.deleteById(id);
				if (count > 0) {
					System.out.println("User deleted successfully");
				} else {
					System.out.println("failed to delete  user.");
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	
}
