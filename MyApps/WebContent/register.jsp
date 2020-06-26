<%@ include file="header.jsp" %>
<%
	String errorMessage = "";
	if(session.getAttribute("ERROR") != null){
		errorMessage = (String)session.getAttribute("ERROR");
	}
	
	
%>

<body>

<div class="container-fluid">
	<div class="row">
		<div class="col-12 bg-dark text-light d-flex justify-content-center align-items-center" style="height:100vh">
			<form action="register-servlet" method="post">
				<% if(errorMessage == "User already exist"){ %>
				<div class="alert alert-danger" role="alert">
					<%=errorMessage %>
				</div>
				<%} %>
				<div class="form-group">
				    <label>Username</label>
				    <input type="text" name="username" class="form-control">
				</div>
				  <div class="form-group">
				    <label>Email address</label>
				    <input type="email" name="email" class="form-control">
				  </div>
				  <div class="form-group">
				    <label>Password</label>
				    <input type="password" name="password" class="form-control">
				  </div>
				  
				  
				  <button type="submit" class="btn btn-primary">Register</button>
				<a class="btn btn-primary" href="login.jsp" role="button">Login</a>
			</form>
		</div>
	</div>
</div>

</body>
</html>