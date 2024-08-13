<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Signup</title>
</head>
<body>
	<h1>Singup</h1>

	<form action="users" method="post">
		<input type="hidden" name="task" value="signup" />

		<table>
			<tr>
				<td>First Name</td>
				<td><input type="text" name="name"
					placeholder="enter name" /></td>
			</tr>
			<!-- <tr>
				<td>Last Name</td>
				<td><input type="text" name="lastName" placeholder="Last name" /></td>
			</tr> -->

			<tr>
				<td>Email</td>
				<td><input type="text" name="e_mail" placeholder="Email" /></td>
			</tr>

			<tr>
				<td>Mobile Number</td>
				<td><input type="text" name="phone_no"
					placeholder="Mobile Number" /></td>
			</tr>

			<tr>
				<td>Address</td>
				<td><input type="text" name="address" placeholder="Address" /></td>
			</tr>
			<tr>
				<td>Password</td>
				<td><input type="password" name="password"
					placeholder="Password" /></td>
			</tr>
			<!-- 		<tr>
				<td>Password</td>
				<td><input type="password" name="confirmPassword"
					placeholder="Confirm Password" /></td>
			</tr> -->
			<tr>
				<td>City</td>
				<td><select name="city_id" placeholder="City">
						<option value="0">Select City</option>
						<option value="1">Indore</option>
						<option value="2">Bhopal</option>
				</select></td>
			</tr>
			<tr>
				<td><input type="submit" value="Login" /></td>
				<td><input type="reset" /></td>
			</tr>
		</table>
	</form>


</body>
</html>






 