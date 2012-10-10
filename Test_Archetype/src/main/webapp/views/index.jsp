<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
	<script type="text/javascript">
		var bob = function test(){
			console.log("Testing log output");
		};
		
	</script>
	<head>
		<title>A Spring MVC Index page</title>
	</head>
	
	<body>
		<h1>Test Header</h1>
		<!-- Turn some of these into ajax requests, have others use the ModelAndView combined with JSP's? -->
		<a id="addPersonLink" href="<c:url value='/persons/add?insuranceNumber=JK168376A&firstName=Alex&secondName=Hopgood' />">Add Person</a>
		<br />
		<a id="getPersonLink" href="<c:url value='/persons/get?insuranceNumber=JK168376A' />">Get person </a>
		<br />
		<a id="staticGet" href="<c:url value='/persons/staticGet' />">Static Get</a>
		<br />
		<input type="button" onclick="bob()" value="Click Me"/>
	</body>
</html>