package com.amstech.restaurant.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.amstech.restaurant.dto.UserDto;
import com.amstech.restaurant.util.DBUtil;

public class UserDao {

		// stmt
//		String USER_INSERT_DATA1 = "insert into user(first_name, lastname, email, password, mobile_number, Address, city_id)"
//				+ "value'Daksh', 'va', 'dv@g.com', 'abc', '8885558855', 'bholaram', 5)";
	//	
		// pstmt
		
		private final String USER_INSERT_DATA = "insert into user(name,e_mail,phone_no,address,city_id,password) value(?,?,?,?,?,?)";
		
		private final String USER_UPDATE_DATA = "update user set name = ?, e_mail = ?, address = ? where id = ?";

		private final String USER_FIND_BY_ID = "select * from user where id = ?";

		private final String USER_FIND_BY_PHONE_NUMBER = "select * from user where phone_no = ?";

		private final String USER_FIND_BY_MOBILE_EMAIL_PASS = "select * from user where (phone_no = ? or e_mail = ?) and password = ?";

		private final String USER_FIND_ALL = "select * from user";

		private final String USER_DELETE_BY_ID = "delete from user where id = ?";

		private DBUtil dbUtil;

		public UserDao(DBUtil dbUtil) {
			this.dbUtil = dbUtil;
		}

		public int save(UserDto userDto) throws Exception {

			Connection connection = null;
			PreparedStatement pstmt = null;
			try {
				connection = dbUtil.getConnection();

				pstmt = connection.prepareStatement(USER_INSERT_DATA);
				/* pstmt.setInt(1, userDto.getId()); */
				pstmt.setString(1, userDto.getName());
				pstmt.setString(2, userDto.getEmail());
				pstmt.setString(3, userDto.getPhoneNumber());
				pstmt.setString(4, userDto.getAddress());
				pstmt.setInt(5, userDto.getCityId());
				pstmt.setString(6, userDto.getPassword());
				int count = pstmt.executeUpdate();
				return count;
			} catch (Exception e) {
				throw e;
			} finally {
				dbUtil.getClose(connection, pstmt);
			}
		}

		public int update(UserDto userDto) throws Exception {
			Connection connection = null;
			PreparedStatement pstmt = null;
			try {
				connection = dbUtil.getConnection();
				pstmt = connection.prepareStatement(USER_UPDATE_DATA);
				pstmt.setString(1, userDto.getName());
				pstmt.setString(2, userDto.getEmail());
				pstmt.setString(3, userDto.getAddress());
				pstmt.setInt(6,userDto.getId());
				int count = pstmt.executeUpdate();
				return count;
			} catch (Exception e) {
				throw e;
			} finally {
				dbUtil.getClose(connection, pstmt);
			}

		}

		public UserDto findById(int id) throws Exception {
			Connection connection = null;
			PreparedStatement pstmt = null;
			UserDto userDto = null;
			try {
				connection = dbUtil.getConnection();
				pstmt = connection.prepareStatement(USER_FIND_BY_ID);
				pstmt.setInt(1, id);
				ResultSet rs = pstmt.executeQuery();
				if (rs.next()) {
					userDto = new UserDto();
					userDto.setName(rs.getString("name"));
					userDto.setEmail(rs.getString("e_mail"));
					userDto.setPhoneNumber(rs.getString("phone_no"));
					userDto.setAddress(rs.getString("address"));
					userDto.setPassword(rs.getString("password"));
				}
				return userDto;
			} catch (Exception e) {
				throw e;
			} finally {
				dbUtil.getClose(connection, pstmt);
			}
		}

		public UserDto findByPhoneNumber(String phone_no) throws Exception {
			Connection connection = null;
			PreparedStatement pstmt = null;
			UserDto userDto = null;
			try {
				connection = dbUtil.getConnection();
				pstmt = connection.prepareStatement(USER_FIND_BY_PHONE_NUMBER);
				pstmt.setString(1, phone_no);
				ResultSet rs = pstmt.executeQuery();
				if (rs.next()) {
					userDto = new UserDto();
					userDto.setName(rs.getString("name"));
      				userDto.setEmail(rs.getString("e_mail"));
					userDto.setPhoneNumber(rs.getString("phone_no"));
					userDto.setAddress(rs.getString("address"));
					userDto.setPassword(rs.getString("password"));
				}
				return userDto;
			} catch (Exception e) {
				throw e;
			} finally {
				dbUtil.getClose(connection, pstmt);
			}
		}

		public UserDto findByMobileEmailPassword(String username, String password) throws Exception {
			Connection connection = null;
			PreparedStatement pstmt = null;
			UserDto userDto = null;
			try {
				connection = dbUtil.getConnection();
				pstmt = connection.prepareStatement(USER_FIND_BY_MOBILE_EMAIL_PASS);
				pstmt.setString(1, username);
				pstmt.setString(2, username);
				pstmt.setString(3, password);
				ResultSet rs = pstmt.executeQuery();
				if (rs.next()) {
					userDto = new UserDto();
					userDto.setName(rs.getString("name"));
					userDto.setEmail(rs.getString("e_mail"));
					userDto.setPhoneNumber(rs.getString("phone_no"));
					userDto.setAddress(rs.getString("address"));
					userDto.setPassword(rs.getString("password"));
				}
				return userDto;
			} catch (Exception e) {
				throw e;
			} finally {
				dbUtil.getClose(connection, pstmt);
			}

		}

		public List<UserDto> findAll() throws Exception {
			Connection connection = null;
			PreparedStatement pstmt = null;
			List<UserDto> userDtoList = new ArrayList<>();

			try {
				connection = dbUtil.getConnection();
				pstmt = connection.prepareStatement(USER_FIND_ALL);
				ResultSet rs = pstmt.executeQuery();

				while (rs.next()) {
					UserDto userDto = new UserDto();
					userDto.setName(rs.getString("name"));
					userDto.setEmail(rs.getString("e_mail"));
					userDto.setPhoneNumber(rs.getString("phone_no"));
					userDto.setAddress(rs.getString("address"));
					userDto.setPassword(rs.getString("password"));
					userDtoList.add(userDto);
				}
				return userDtoList;
			} catch (Exception e) {
				throw e;
			} finally {
				dbUtil.getClose(connection, pstmt);
			}
		}

		public int deleteById(int id) throws Exception {
			Connection connection = null;
			PreparedStatement pstmt = null;
			try {
				connection = dbUtil.getConnection();
				pstmt = connection.prepareStatement(USER_DELETE_BY_ID);
				pstmt.setInt(1, id);
				int count = pstmt.executeUpdate();
				return count;
			} catch (Exception e) {
				throw e;
			} finally {
				dbUtil.getClose(connection, pstmt);
			}
		}

	
}

