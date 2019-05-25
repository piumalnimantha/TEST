<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/views/includes.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit Registration</title>
</head>
<body>

	<div>
		<form:form method="post" action="/paf_project/editSaveRegistration"
			modelAttribute="registration">

			<form:input type="text" path="userID" name="userID"
				required="required" hidden="hidden" autofocus="autofocus" />
			
			Email<br>
			<form:input type="email" path="email" class="form-control"
				name="email" required="required" autofocus="autofocus" />
			<br>
			<br>


			Username<br>
			<form:input type="text" path="username" class="form-control"
				name="username" required="required" autofocus="autofocus" />
			<br>
			<br>

			Role<br>
			<form:input type="text" path="role" class="form-control" name="role"
				required="required" autofocus="autofocus" />
			<br>
			<br>


			Password<br>
			<form:input type="password" path="password" class="form-control"
				name="password" required="required" autofocus="autofocus" />
			<br>
			<br>



			<button type="submit">Add Registration</button>
			<input type="reset" value="Clear">

		</form:form>
	</div>

</body>
</html>