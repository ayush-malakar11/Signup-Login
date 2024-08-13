 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>

</head>
<body>
	<h1>Login</h1>
	<form action="users" method="post">
		<input type="hidden" name="task" value="login" />
		<table>
			<tr>
				<td>Username</td>
				<td><input type="text" name="username" placeholder="Username" /></td>
			</tr>
			<tr>
				<td>Password</td>
				<td><input type="password" name="password"
					placeholder="Password" /></td>
			</tr>
			<tr>
				<td><input type="submit" value="login" /></td>
				<td><input type="reset" /></td>
			</tr>
		</table>
	</form>
</body>
</html>








