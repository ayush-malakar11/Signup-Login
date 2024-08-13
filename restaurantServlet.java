package com.amstech.restaurant.servlet;

import java.io.IOException;
import java.util.List;

import com.amstech.restaurant.dao.UserDao;
import com.amstech.restaurant.dto.UserDto;
import com.amstech.restaurant.service.UserService;
import com.amstech.restaurant.util.DBUtil;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//URL: UserServlet

@WebServlet("/users")

public class restaurantServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DBUtil dbUtil;
	private UserDao userDao;
	private UserService userService;

	public restaurantServlet() {
		System.out.println("UserServlet: Object created..");
		this.dbUtil = new DBUtil();
		this.userDao = new UserDao(dbUtil);
		this.userService = new UserService(userDao);
	}

	public void init(ServletConfig config) throws ServletException {
		System.out.println("UserServlet: Init method..");

	}

//	protected void service(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		System.out.println("UserServlet: Service method..");
//	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		System.out.println("UserServlet: doGet method..");
		String task =request.getParameter("task");
		System.out.println("task: " + task);

		if (task.equalsIgnoreCase("findByMobileNumber")) {
			findByMobileNumber(request, response);
		} else if (task.equalsIgnoreCase("findAll")) {
			findAll(request, response);
		} else if (task.equalsIgnoreCase("findById")) {
			findById(request, response);
		} else if (task.equalsIgnoreCase("deleteById")) {
			deleteById(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("UserServlet: doPost method..");
		String task = request.getParameter("task");
		System.out.println("task: " +task);
		
		if (task.equalsIgnoreCase("login")) {
			findByMobileEmailPassword(request, response);
		} else if (task.equalsIgnoreCase("signup")) {
			save(request, response);
		}else if(task.equalsIgnoreCase("updateById")) {
			update(request, response);
		} else {
			System.out.println("No task found.");
		}

	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("UserServlet: doPut method..");
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("UserServlet: doDelete method..");
	}

	protected void doHead(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("UserServlet: doHead method..");
	}

	protected void doOptions(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("UserServlet: odOption method..");
	}

	protected void doTrace(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("UserServlet: doTrace method..");
	}

	public void destroy() {
		System.out.println("UserServlet: Destroy method..");
	}

	public void save(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {

			UserDto userDto = new UserDto();
			userDto.setName(request.getParameter("name")); // request.getParam('firstname');
			userDto.setEmail(request.getParameter("e_mail")); // request.getParam('firstname');
			userDto.setPhoneNumber(request.getParameter("phone_no")); // request.getParam('firstname');
			userDto.setAddress(request.getParameter("address")); // request.getParam('firstname');
			userDto.setCityId(Integer.parseInt(request.getParameter("city_id"))); // request.getParam('firstname');
			userDto.setPassword(request.getParameter("password")); // request.getParam('firstname');

			int count = userService.save(userDto);
			if (count > 0) {
				System.out.println("User created successfully");
		     	RequestDispatcher rd = request.getRequestDispatcher("message.jsp");
		    	request.setAttribute("status", "Success");
		    	request.setAttribute("message", "User created succesfully");
		    	request.setAttribute("redirectURL","login.jsp");
		     	rd.forward(request, response);
		    } else {
				System.out.println("Failed to create user data..");
				RequestDispatcher rd = request.getRequestDispatcher("message.jsp");
				request.setAttribute("status", "Error");
				request.setAttribute("message", "Failed to create user data");
				request.setAttribute("redirectURL","signup.jsp");
				rd.forward(request, response);
		    }
		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher rd = request.getRequestDispatcher("message.jsp");
			request.setAttribute("status", "Error");
			request.setAttribute("message", "Failed to create user data" + e.getMessage());
			request.setAttribute("redirectURL","signup.jsp");
			rd.forward(request, response);
		}

	}

	public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			UserDto userDto = new UserDto();
			userDto.setId(Integer.parseInt(request.getParameter("id")));
			userDto.setName(request.getParameter("name"));
			userDto.setEmail(request.getParameter("email"));
			userDto.setPhoneNumber(request.getParameter("mobileNumber"));
			userDto.setAddress(request.getParameter("address"));

			int count = userService.update(userDto);
			if (count > 0) {
				System.out.println("User updated successfully");
				RequestDispatcher rd = request.getRequestDispatcher("message.jsp");
				request.setAttribute("status", "Success");
			 	request.setAttribute("message", "User updated successfully.");
				request.setAttribute("redirectURL", "home.jsp");
				rd.forward(request, response);
			} else {
				System.out.println("failed to updated user.");
				RequestDispatcher rd = request.getRequestDispatcher("message.jsp");
				request.setAttribute("status", "Error");
				request.setAttribute("message", "failed to updated user.");
				request.setAttribute("redirectURL", "home.jsp");
				rd.forward(request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher rd = request.getRequestDispatcher("message.jsp");
			request.setAttribute("status", "Error");
			request.setAttribute("message", "Failed to update user data due to: " + e.getMessage());
			request.setAttribute("redirectURL", "home.jsp");
			rd.forward(request, response);
		}
	}
	

	public void findById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int id = Integer.parseInt(request.getParameter("userId")); // request.getParam("id")

			UserDto userDto = userService.findById(id);
			if (userDto != null) {
				System.out.println("User found successfully");
				RequestDispatcher rd = request.getRequestDispatcher("home.jsp");
				request.setAttribute("userDTOEdit", userDto);
				rd.forward(request, response);

			} else {
				System.out.println("failed to find user.");
				RequestDispatcher rd = request.getRequestDispatcher("message.jsp");
				request.setAttribute("status", "Error");
				request.setAttribute("message", "Failed to find user data");
				request.setAttribute("redirectURL", "home.jsp");
				rd.forward(request, response);

			}

		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher rd = request.getRequestDispatcher("message.jsp");
			request.setAttribute("status", "Error");
			request.setAttribute("message", "Failed to find user data to: " + e.getMessage());
			request.setAttribute("redirectURL", "home.jsp");
			rd.forward(request, response);
		}
	}

	public void findByMobileNumber(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String mobileNumber = request.getParameter("mobileNumber"); // request.getParam("mobileNumber")

			UserDto userDto = userService.findByPhoneNumber(mobileNumber);
			if (userDto != null) {
				System.out.println("User found successfully");
				RequestDispatcher rd = request.getRequestDispatcher("home.jsp");
				request.setAttribute("userDto", userDto);
				rd.forward(request, response);
//				System.out.println("Name: " + userDto.getName());
//				System.out.println("Email: " + userDto.getEmail());
			} else {
				System.out.println("failed to find user.");
				RequestDispatcher rd = request.getRequestDispatcher("message.jsp");
				request.setAttribute("status", "Error");
				request.setAttribute("message", "Failed to find user data for mobile number: " + mobileNumber);
				request.setAttribute("redirectURL", "home.jsp");
				rd.forward(request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher rd = request.getRequestDispatcher("message.jsp");
			request.setAttribute("status", "Error");
			request.setAttribute("message", "Failed to find user data to: " + e.getMessage());
			request.setAttribute("redirectURL", "home.jsp");
			rd.forward(request, response);
		}

	}

	public void findByMobileEmailPassword(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		try {
//			String phone_no = request.getParameter("phone_no");
//			String e_mail = request.getParameter("e_mail");
			String username = request.getParameter("username");
			String password = request.getParameter("password");

			UserDto userDto = userService.findByMobileEmailPassword(username, password);
			if (userDto != null) {
				System.out.println("Login successfully");
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("home.jsp");
				request.setAttribute("loginUserDto", userDto);
				// set data to the req before call forward
				requestDispatcher.forward(request, response);
			} else {
				System.out.println("Wrong userName or password.");
				// resp
//				response.sendRedirect("login.jsp");
				RequestDispatcher rd = request.getRequestDispatcher("message.jsp");
				request.setAttribute("status", "Error");
				request.setAttribute("message", "Wrong username or password");
				request.setAttribute("redirectURL", "login.jsp");
				rd.forward(request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("failed to Login due to.....");
			// resp
//			response.sendRedirect("login.jsp");
			RequestDispatcher rd = request.getRequestDispatcher("message.jsp");
			request.setAttribute("status", "Error");
			request.setAttribute("message", "failed to Login due to: " + e.getMessage());
			request.setAttribute("redirectURL", "login.jsp");
			rd.forward(request, response);
		}
	}

	public void findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {

			List<UserDto> userDtoList = userService.findAll();
			if (!userDtoList.isEmpty()) {
				System.out.println("User found successfully");
				RequestDispatcher rd = request.getRequestDispatcher("home.jsp");
				request.setAttribute("userDtoList", userDtoList);
				rd.forward(request, response);
				
//				for (UserDto userDto : userDtoList) {
//					System.out.println("FistName: " + userDto.getName());
//					System.out.println("LastName: " + userDto.getEmail());
//				}

			} else {
				System.out.println("failed to find user.");
				RequestDispatcher rd = request.getRequestDispatcher("message.jsp");
				request.setAttribute("status", "Error");
				request.setAttribute("message", "Failed to find user data.");
				request.setAttribute("redirectURL", "home.jsp");
				rd.forward(request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher rd = request.getRequestDispatcher("message.jsp");
			request.setAttribute("status", "Error");
			request.setAttribute("message", "failed to find user due to: " + e.getMessage());
			request.setAttribute("redirectURL", "home.jsp");
			rd.forward(request, response);
		}
	}

	public void deleteById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int id =Integer.parseInt(request.getParameter("userId"));
			System.out.println("UserId: " + id);
			int count = userService.deleteById(id);
			if(count > 0) {
				System.out.println("User delete successfully");
				RequestDispatcher rd = request.getRequestDispatcher("message.jsp");
				request.setAttribute("status", "Success");
				request.setAttribute("message", "User deleted successfully.");
				request.setAttribute("redirectURL", "home.jsp");
				rd.forward(request, response);

		}else {
				System.out.println("failed to delete user data...");  
				RequestDispatcher rd = request.getRequestDispatcher("message.jsp");
				request.setAttribute("status", "Error");
				request.setAttribute("message", "Failed to delete user data.");
				request.setAttribute("redirectURL", "home.jsp");
				rd.forward(request, response);
		}
		}catch(Exception e) {
			e.printStackTrace();
			RequestDispatcher rd = request.getRequestDispatcher("message.jsp");
			request.setAttribute("status", "Error");
			request.setAttribute("message", "failed to delete user due to: " + e.getMessage());
			request.setAttribute("redirectURL", "home.jsp");
			rd.forward(request, response);
		}
	}
}
