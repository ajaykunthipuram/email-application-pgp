<%@ include file="header.jsp" %>
<%
	String errorMessage = "";
	if(session.getAttribute("ERROR") != null){
		errorMessage = (String)session.getAttribute("ERROR");
		
		session.removeAttribute("ERROR");
	}
	
	
%>

<body>
<div class="container-fluid">
	<div class="row">
		<div class="col-12 bg-dark text-light d-flex justify-content-center align-items-center" style="height:100vh">
			<form action="login-servlet" method="post">
				<% if(errorMessage == "Authentication Failed!!"){ %>
				<div class="alert alert-danger" role="alert">
					<%=errorMessage %>
				</div>
				<%} %>
				  <div class="form-group">
				    <label>Email address</label>
				    <input type="email" name="email" class="form-control">
				  </div>
				  <div class="form-group">
				    <label>Password</label>
				    <input type="password" name="password" class="form-control">
				    
				  </div>
				  
				  <button type="submit" class="btn btn-primary">Login</button>
				  <a class="btn btn-primary" href="register.jsp" role="button">Register</a>
			</form>
		</div>
	</div>
</div>

</div>

</body>
</html>