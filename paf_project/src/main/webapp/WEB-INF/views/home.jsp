<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/views/includes.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<div>
		<form:form method="post" action="/paf_project/searchRegistration">
			Search - <input type="text" name = "searchVal">
			<input type="submit" value="Search"/>
			
			<a href="/paf_project/viewAllRegistration"><button>View All</button></a>
		</form:form>
	</div>
	
	<br>
	<br>

	<div>
		<table border="1">
			<tr>
				
				<th>UserID</th>
				<th>Email</th>
				<th>Username</th>
				<th>Role</th>
				<th>Added Date</th>
				<th>Status</th>
				<th colspan = "2"><a href="/paf_project/logout"><button style="width:80%;">Logout</button></a></th>
			</tr>


			<c:forEach var="registration" items="${listRegistration}">
				<tr>
					<th>${registration.userID}</th>
					<th>${registration.email}</th>
					<th>${registration.username}</th>
					<th>${registration.role}</th>
					<th>${registration.addedDate}</th>
					<th>${registration.status}</th>
					<th> <a href="/paf_project/editRegistration	?userID=${registration.userID}"> <input type="button" value = "Edit"> </a> </th>
					<th> <a href="/paf_project/setStatus?userID=${registration.userID}&command=active"> <input type="button" value = "Activate"> </a> </th>
					<th> <a href="/paf_project/setStatus?userID=${registration.userID}&command=inactive"> <input type="button" value = "Deactivate"> </a> </th>
				</tr>
			</c:forEach>
		</table>
	</div>

</body>
</html>