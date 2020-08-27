<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
	<head>
		<title>${pageTitle}</title>
		<c:url value="/style.css" var="cssUrl"/>
		<link rel="stylesheet" type="text/css" href="${cssUrl}"/>
	</head>
	<body>
		<div id="header">
			<ul>
				<c:url value="/" var="homeUrl"/>
				<li><a href="${homeUrl}">Home</a></li>
				<c:url value="/j_spring_security_logout" var="logoutUrl"/>
				<li><a href="${logoutUrl}">Logout</a></li>
			</ul>
		</div>
	</body>
</html>