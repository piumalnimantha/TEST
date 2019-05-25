<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/views/includes.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Login</title>
</head>
<body>

<div>
	
	<div>
		<form:form method="post" action="/paf_project/loginProceed"
			modelAttribute="login">
			
			Email<br>
			<form:input type="text" path="username" class="form-control"
				name="username" required="required" autofocus="autofocus" />
				<br><br>

			Password<br>
			<form:input type="password" path="password" class="form-control"
				name="password" required="required" autofocus="autofocus" />
					<br><br>



			<button type="submit">Login</button>
			<input type="reset" value="Clear">

		</form:form>
	</div>
	
</div>

</body>
</html>