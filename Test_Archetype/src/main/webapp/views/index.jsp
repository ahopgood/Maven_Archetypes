<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
	<script type="text/javascript">
		var bob = function test(){
			console.log("Testing log output");
		};
		
		function test(){
			var params = "?insuranceNumber="+document.getElementById("insuranceNumber").value;
			var request = new XMLHttpRequest();
			request.open("GET","persons/get"+params,true);
			request.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
			request.send();
			request.onreadystatechange = function() {
				if (request.readyState == 4 && request.status == 200) {
					console.log(request.responseText);
					document.getElementById("results").innerHTML = request.responseText;		
				} else if (request.readyState == 2){
					//do some error handling
				}
			}
		};
		
		function getAll(){
			var params 	= "";
			var url		= "persons/getAll";
			get(params, url, function success(request){
				console.log(request);
				console.log("Initial Response Text " + request.responseText);
				document.getElementById("results").innerHTML = "All national ins numbers : <br />" + request.responseText;
			});
		};
		
		function getIndividual(){
			var params 	= "?insuranceNumber="+document.getElementById("insuranceNumber").value;
			var url		= "persons/get";
			get(params, url, function success(request){
				console.log(request);
				console.log("Initial Response Text " + request.responseText);
				document.getElementById("results").innerHTML = request.responseText;

			});
		};

		function getJsonIndividual(){
			var params 	= "?insuranceNumber="+document.getElementById("jsonInsuranceNumber").value;
			var url 	= "persons/jsonGet";
			var contentType	= "application/json";
			get(params, url, function success(request){
				console.log(request);
				console.log("Initial Response Text " + request.responseText);
				document.getElementById("results").innerHTML = request.responseText;
			}, contentType);
		};
		
		function get(params, url, success){
			get(params, url, success, null);
		};
		
		function get(params, url, success, contentType){
			var request = new XMLHttpRequest();
			var response;
			request.open("GET",url+params,true);
			console.log("Content type "+contentType);
			if (contentType == undefined){
				request.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
			} else {
				request.setRequestHeader("Content-type", contentType);
			}
			request.send();
			request.onreadystatechange = function() {
				if (request.readyState == 4 && request.status == 200){
					success(request);			
				} else if (request.readyState == 2){
					//do some error handling
					console.log(request.responseText);
				}
			}
			return response;
		};
		
		function postIndividual(){
			var params 	= "insuranceNumber="+document.getElementById("postInsuranceNumber").value+"&firstName="+document.getElementById("postFirstName").value+"&lastName="+document.getElementById("postLastName").value;
			
			var url		= "persons/add";
			post(params, url, function success(request){
				console.log(request);
				console.log("Initial Response Text " + request.responseText);
				document.getElementById("results").innerHTML = request.responseText;

			});
		};
		
		function postJsonIndividual(){
			var insNumber 	= document.getElementById("postJsonInsuranceNumber").value;
			var firstName	= document.getElementById("postJsonFirstName").value;
			var lastName	= document.getElementById("postJsonLastName").value;
			var params		= { 	"nationalInsuranceNumber" 	: insNumber,
									"firstName"					: firstName,
									"lastName"					: lastName
							};
			var url			= "persons/addJson";
			var contentType = "application/json";
			console.log("Params");
			console.log(params);
			post(params, url, function success(request){
				console.log(request);
				console.log("Initial Response Text " + request.responseText);
				document.getElementById("results").innerHTML = request.responseText;

			}, contentType);
		};
		
		function post(params, url, success){
			post(params, url, success, null);
		};
		
		function post(params, url, success, contentType){
			var request = new XMLHttpRequest();
			var response;
			request.open("POST",url,true);
			console.log("Content type "+contentType);
			if (contentType == undefined){
				request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
			} else {
				request.setRequestHeader("Content-Type", contentType);
				request.setRequestHeader("Accept", contentType);
				request.setRequestHeader("DataType", "json");
			}
			request.send(JSON.stringify(params));
			
			request.onreadystatechange = function() {
				if (request.readyState == 4 && request.status == 200){
					success(request);			
				} else if (request.readyState == 2 ||
						request.status == 500){
					//do some error handling
					console.log(request.responseText);
				}
			}
			return response;
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
		<a href="#" onclick="getAll()">Get All</a>
		<br />
		<label>Get with Ajax</label>
		<input type="text" id="insuranceNumber"/>
		<input type="button" onclick="getIndividual()" value="Click Me"/>
		<br />
		<label>Get with JSON</label>
		<input type="text" id="jsonInsuranceNumber"/>
		<input type="button" onclick="getJsonIndividual()" value="Click Me"/>		
		<br />
		<div id="tableContainer">
			<table>
				<tr id="personHeaderRow">
					<td>Insurance Number</td><td>First Name</td><td>Last Name</td>
				</tr>
				<tr id="inputHeaderRow">
					<td><input type="text" id="postInsuranceNumber"/></td><td><input type="text" id="postFirstName"/></td><td><input type="text" id="postLastName"/></td><td><input type="button" onclick="postIndividual()" value="Post Person"/></td>
				</tr>
				<tr id="jsonInputHeaderRow">
					<td><input type="text" id="postJsonInsuranceNumber"/></td>
					<td><input type="text" id="postJsonFirstName"/></td>
					<td><input type="text" id="postJsonLastName"/></td>
					<td><input type="button" onclick="postJsonIndividual()" value="Post Json Person"/></td>
				</tr>
			</table>
		</div>
		<div id="results">	
		</div>
		<tr id="personTemplate">
			<td id="templateInsuranceNumber"></td><td id="templateFirstName"><td></td><td id="templateLastName"></td>
		</tr>
		
	</body>
</html>