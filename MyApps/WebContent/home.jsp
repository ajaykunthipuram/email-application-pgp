<%@ include file="header.jsp" %>
<jsp:include page="sessionValidation.jsp"></jsp:include>
<%
	if(session.getAttribute("AUTH") == null){
		response.sendRedirect("login.jsp");
	}
%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div class="conatiner-fluid">
	<!-- NAVIGATION -->
		<div class="row">
			<div class="col-6 bg-dark text-light d-flex align-items-center" style="height:60px">
				<a class="btn btn-secondary ml-2" href="home.jsp" role="button">Home</a>
				<a class="btn btn-secondary ml-2" href="profiles.jsp" role="button">Public Profiles</a>
				<a class="btn btn-secondary ml-2" href="inbox.jsp" role="button">Inbox</a>
				<a class="btn btn-secondary ml-2" href="outbox.jsp" role="button">Outbox</a>
				
				
			</div>
			
			<div class="col-6 bg-dark text-light d-flex justify-content-end align-items-center" style="height:60px">
				<a class="btn btn-secondary" href="logout-servlet" role="button">Logout</a>
			</div>
		</div>
		
		
		
		<!-- <div class="row">
			<div class="col-3"></div>
			<div class="col-6 bg-secondary text-light d-flex align-items-center" style="height:80px">
				
				
				  
				  <button type="submit" class="btn btn-primary">Send</button>
				<br /> -->
				
<div class="container-fluid">

	<div class="row">
		<div class="col-12 bg-dark text-light d-flex justify-content-center align-items-center" style="height:100vh">
			<form action="send-servlet" method="post">
				
				  <div class="form-group">
				    <label>To</label>
				    <input type="email" name="to" class="form-control">
				  </div>
				  <div class="form-group">
				    <label>Message</label>
				    <input type="text" name="message" class="form-control">
				    
				  </div>
				  
				  <button type="submit" class="btn btn-primary">Send</button>
				  
			</form>
		</div>
	</div>
</div>
				
				<!-- <div class="row">
					<div class="row">
					<div class="col-3"></div>
					<div class="col-6 bg-secondary text-light d-flex align-items-center" style="height:50px">
						<div class="col-12">
							HELLOWORLD
						</div>
					</div>
					
					</div>
				
				</div> -->
			</div>
		</div>
		
	</div>
	
	
			
</body>
</html>