<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="common/header.jsp">
	<jsp:param name="pageTitle" value="index"/>
</jsp:include>

<html>
	<head>
		<title>A Spring MVC Index page</title>
	</head>
	
	<body>
		<h1>Test Header</h1>
		<!-- Turn some of these into ajax requests, have others use the ModelAndView combined with JSP's? -->
		<a id="addPersonLink" href="<c:url value='/spring/persons/add?insuranceNumber=JK168376A&firstName=Alex&secondName=Hopgood' />">Add Person</a>
		<br />
		<a id="getPersonLink" href="<c:url value='/spring/persons/get?insuranceNumber=JK168376A' />">Get person </a>
		<br />
		<a id="staticGet" href="<c:url value='/spring//persons/staticGet' />">Static Get</a>
		<br />
		<a id="parameter" href="<c:url value='/spring//persons/' />">Template URI Get</a>
		<p> a new paragraph boyo </p>
		<p>testing</p>
		
		<a href="<c:url value='/spring/views/protected-page'/>">Link to a protected page</a>
		<br />
		<a href="<c:url value='/spring/views/secured/securepage' />">A link to a secure page</a>


	</body>
</html>