<%@page import="com.amstech.restaurant.dto.UserDto"%>
<%@page import="java.util.List"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home</title>
</head>
<body>
	<%
   if (request.getAttribute("loginUserDto") != null) {
	UserDto loginUserDto = (UserDto) request.getAttribute("loginUserDto");
	%>
	

	<h1>Welcome <%=loginUserDto.getName() %>..</h1>
	<%
	}
   %>
	<label for="mnumber">Mobile Number:</label>
	<br>

	<form action="users" method="get">
		<input type="hidden" name="task" value="findByMobileNumber"> <input
			type="text" id="mnumber" class="form-input"
			placeholder="enter your mobile number" required="required"
			name="mobileNumber" /> <input type="submit" value="Find By Number">
	</form>


	<br> OR
	<br>
	<form action="users" method="get">
		<input type="hidden" name="task" value="findAll"> <input
			type="submit" value="Find All">
	</form>


	<br>
	<br>

	<table>
		<tr>
			<th>id</th>
			<th>Name</th>
			<th>Email</th>
			<th>Mobile Number</th>
			<th>Address</th>
			<th>Action</th>
		</tr>

		<%
		if (request.getAttribute("userDto") != null) {
			UserDto userDto = (UserDto) request.getAttribute("userDto");
		%>
		<tr>
			<td><%=userDto.getId()%></td>
			<td><%=userDto.getName()%></td>
			<td><%=userDto.getEmail()%></td>
			<td><%=userDto.getPhoneNumber()%></td>
			<td><%=userDto.getAddress()%></td>
			<td>
				<form action="users" method="get">
					<input type="hidden" name="task" value="findById">
					 <input type="hidden" name="userId" value="<%=userDto.getId()%>">
					<input type="submit" class="button1" name="Edit" value="Edit" />
				</form>

				   <form action="users" method="get">
					<input type="hidden" name="task" value="deleteById"> <input
						type="hidden" name="userId" value="<%=userDto.getId()%>">
					<input type="submit" class="button1" name="Delete" value="Delete" />
				   </form>
			       </td>
		</tr>

		<%
		}
		%>

	<%
		if (request.getAttribute("userDtoList") != null) {
			List<UserDto> userDtoList = (List) request.getAttribute("userDtoList");
			int i = 0;
			for (UserDto userDto : userDtoList) {
		%>
		<tr>
			<td><%=userDto.getId()%></td>
			<td><%=userDto.getName()%></td>
			<td><%=userDto.getEmail()%></td>
			<td><%=userDto.getPhoneNumber()%></td>
			<td><%=userDto.getAddress()%></td>
			<td>
				<form action="users" method="get">
					<input type="hidden" name="task" value="findById"> <input
						type="hidden" name="userId" value="<%=userDto.getId()%>">
					<input type="submit" class="button1" name="Edit" value="Edit" />
				</form>

				<form action="users" method="get">
					<input type="hidden" name="task" value="deleteById"> <input
						type="hidden" name="userId" value="<%=userDto.getId()%>">
					<input type="submit" class="button1" name="Delete" value="Delete" />
				</form>
			</td>
		</tr>

		<%
		}
		}
		%>



	</table>
	</fieldset>
	
<%
if(request.getAttribute("userDtoEdit")!= null){
	UserDto userDto = (UserDto) request.getAttribute("userDtoEdit");

%>


	<h1>Edit User</h1>
	<form action="users" method="post">
		<input type="hidden" name="task" value="updateById"> <input
			type="hidden" name="id" value="<%=userDto.getId()%>">
		<table>
			<tr>
				<td>FirstName:</td>
				<td><input type="text" name="name"
					value="<%=userDto.getName()%>" /></td>
			</tr>

			<tr>
				<td>Email:</td>
				<td><input type="text" name="email"
					value="<%=userDto.getEmail()%>" /></td>
			</tr>

			<tr>
				<td>MobileNumber:</td>
				<td><input type="text" name="mobileNumber"
					value="<%=userDto.getPhoneNumber()%>" /></td>
			</tr>

			
			<tr>
				<td>Address:</td>
				<td><input type="text" name="address"
					value="<%=userDto.getAddress()%>" /></td>
			</tr>
			<tr>
				<td><input type="submit" name="submit" value="Update" /></td>
				<td><input type="reset" name="reset" /></td>

			</tr>

		</table>
	</form>

	<%
	}
	%>

</body>

</html>