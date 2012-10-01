<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
	<head>
		<title>A JSON SPring MVC Index page</title>
	</head>
	
	<body>
		<h1>Test Header</h1>
		<a id="getPersonLink" href="<c:url value='/persons/get?insuranceNumber=JK168376A' />">Get person </a>
		
		<a id="staticGet" href="<c:url value='/persons/staticGet' />">Static Get</a>
	</body>
</html>