<%@ include file="header.jsp" %>
<jsp:include page="sessionValidation.jsp"></jsp:include>

<%
	if(session.getAttribute("AUTH") == null){
		response.sendRedirect("login.jsp");
	}
%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="in.ajay.LoginServlet" %>
<%@page import="in.ajay.*" %>
<%@page import="java.util.*" %>


<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
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
		<%
		//ArrayList<String> mmm = new ArrayList<>();
		%>
	<%
		ArrayList<String> al = new ArrayList<>();
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");//.newInstance();
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","");
			//System.out.println((String)session.getAttribute("USER"));
			String from = (String)session.getAttribute("USER");
			String sql1 = "SELECT * FROM PUBLIC" ;
			PreparedStatement ps = con.prepareStatement(sql1);
			ResultSet rs = ps.executeQuery();
			//Format f=  new Format();
			
			while(rs.next()){
				al.add(rs.getString("email"));
			}
		
		}
	catch(Exception e){
		
	}
	%>
	<%
	for(int i = 0 ; i < al.size()  ; i++) {
	%>
	<div class="card">
			  <div class="card-body">
			    <h5 class="card-title"><td><%=al.get(i) %></td></h5>
			    
		</div>
	</div>	
	<% } %>
			
</body>
</html>