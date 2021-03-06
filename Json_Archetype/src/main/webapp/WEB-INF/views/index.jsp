<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
	<head>
		<script type="text/javascript" src="<c:url value="/resources/jquery/1.8.2/jquery-1.8.2.js" />"></script>

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
				var json = JSON.parse(request.responseText);
				console.log(json);
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
			post(params, url, function success(request){
				var jsonObj 	= JSON.parse(request.responseText);
				var template 	= document.getElementById("personTemplate").cloneNode(true);
				var childNodes 	= template.childNodes;
				var insuranceNode = childNodes.item(1);
				insuranceNode.innerHTML = jsonObj.nationalInsuranceNumber;
				var firstNameNode = childNodes.item(2);
				firstNameNode.innerHTML = jsonObj.firstName;
				var lastNameNode = childNodes.item(3);
				lastNameNode.innerHTML = jsonObj.lastName;
				//add the new node to the 
				document.getElementById("personTable").appendChild(template);
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
		
		
		
		function getJQueryJsonIndividual(){
			var insNumber 	= document.getElementById("jsonJqueryInsuranceNumber").value;
			var params		= "insuranceNumber="+insNumber;
			var url = "persons/jsonGet";
			jqueryJsonGet(params, url, 
				function success( result ){
					console.log(result);
					var template = tabulatePerson(result, "personTemplate");
					//jsonPersonTable
					document.getElementById("personTable").appendChild(template);
				},
				function failure(jqXHR, textStatus){
					alert("Request failed "+textStatus);
				}
			);
		};
		
		function postJqueryJsonIndividual(){
			var insNumber 	= document.getElementById("postJqueryJsonInsuranceNumber").value;
			var firstName 	= document.getElementById("postJqueryJsonFirstName").value;
			var lastName 	= document.getElementById("postJqueryJsonLastName").value;
			var params		= { 	"nationalInsuranceNumber" 	: insNumber,
									"firstName"					: firstName,
									"lastName"					: lastName
							};
			var url			= "persons/addJson";
			jqueryJsonPost(params, url, 
				function success( result ){
				console.log(result);
				var template = tabulatePerson(result, "personTemplate");
				//jsonPersonTable
				document.getElementById("personTable").appendChild(template);
				}, 
				function failure(jqXHR, textStatus){
					alert("Request failed "+textStatus);
				}
			);
		};
		
		function jqueryJsonGet(params, url, success, failure){
			jqueryGet(params, url, success, failure, "application/json", "json");
		};
		
		function jqueryJsonPost(params, url, success, failure){
			jqueryPost(params, url, success, failure, "application/json", "json");
		};
		
		function jqueryGet(params, url, success, failure, contentType, dataType){
			jqueryAjax(params, url, success, failure, contentType, dataType, "GET");
		};
		
		function jqueryPost(params, url, success, failure, contentType, dataType){
			params = JSON.stringify(params);
			jqueryAjax(params, url, success, failure, contentType, dataType, "POST");
		};
		
		function jqueryAjax(params, url, success, failure, contentType, dataType, requestType){
			$.ajax({
				type: requestType,
				url: url,
				data: params,
				dataType: dataType,
				accept: contentType,
				contentType: contentType
			})
			.done(success)
			.fail(failure);
		};
		
		function tabulatePerson( person, templateId ){
			console.log("in tabulate person");
			var template 	= document.getElementById(templateId).cloneNode(true);
			var childNodes 	= template.childNodes;
			var insuranceNode = childNodes.item(1);
			insuranceNode.innerHTML = person.nationalInsuranceNumber;
			var firstNameNode = childNodes.item(2);
			firstNameNode.innerHTML = person.firstName;
			var lastNameNode = childNodes.item(3);
			lastNameNode.innerHTML = person.lastName;
			return template;
		};
		
	</script>
		<title>A Spring MVC Index page</title>
	</head>
	
	<body>
		<h1>Test Header</h1>
		<!-- Turn some of these into ajax requests, have others use the ModelAndView combined with JSP's? -->
		<fieldset>
			<legend>JSP Links</legend>
			<a id="addPersonLink" href="<c:url value='/persons/add?insuranceNumber=JK168376A&firstName=Alex&secondName=Hopgood' />">Add Person</a>
			<br />
			<a id="getPersonLink" href="<c:url value='/persons/get?insuranceNumber=JK168376A' />">Get person </a>
			<br />
			<a id="staticGet" href="<c:url value='/persons/staticGet' />">Static Get</a>
			<br />
		</fieldset>
		<fieldset>
			<legend>Ajax Onclick</legend>
			<a href="#" onclick="getAll()">Get All</a>
			<br />
			<label>Get with Ajax</label>
			<input type="text" id="insuranceNumber"/>
			<input type="button" onclick="getIndividual()" value="Click Me"/>
			<br />
			<table>
				<tr id="personHeaderRow">
					<td>Insurance Number</td><td>First Name</td><td>Last Name</td>
				</tr>
				<tr id="inputHeaderRow">
					<td><input type="text" id="postInsuranceNumber"/></td><td><input type="text" id="postFirstName"/></td><td><input type="text" id="postLastName"/></td><td><input type="button" onclick="postIndividual()" value="Post Person"/></td>
				</tr>
			</table>
		</fieldset>
		<fieldset>
			<legend>JSON Onclick</legend>
			<label>Get with JSON</label>
			<input type="text" id="jsonInsuranceNumber"/>
			<input type="button" onclick="getJsonIndividual()" value="Click Me"/>
			<table>
				<tr id="personHeaderRow">
					<td>Insurance Number</td><td>First Name</td><td>Last Name</td>
				</tr>
				<tr id="jsonInputHeaderRow">
					<td><input type="text" id="postJsonInsuranceNumber"/></td>
					<td><input type="text" id="postJsonFirstName"/></td>
					<td><input type="text" id="postJsonLastName"/></td>
					<td><input type="button" onclick="postJsonIndividual()" value="Post Json Person"/></td>
				</tr>
			</table>
		</fieldset>
		
		<fieldset>
			<legend>JQuery JSON Onclick</legend>
			<label>JQuery with JSON</label>
			<input type="text" id="jsonJqueryInsuranceNumber"/>
			<input type="button" onclick="getJQueryJsonIndividual()" value="Click Me"/>
			<table id="jsonPersonTable">
				<tr id="jsonPersonHeaderRow">
					<td>Insurance Number</td><td>First Name</td><td>Last Name</td>
				</tr>
				<tr id="jqueryJsonInputHeaderRow">
					<td><input type="text" id="postJqueryJsonInsuranceNumber"/></td>
					<td><input type="text" id="postJqueryJsonFirstName"/></td>
					<td><input type="text" id="postJqueryJsonLastName"/></td>
					<td><input type="button" onclick="postJqueryJsonIndividual()" value="Post Jquery Json Person"/></td>
				</tr>
			</table>
		</fieldset>

		<div id="tableContainer">
			<table id="personTable">
				<tr id="personHeaderRow">
					<td>Insurance Number</td><td>First Name</td><td>Last Name</td>
				</tr>
			</table>
		</div>
		<div id="results">	
		</div>
		<table>
			<tr id="personTemplate">
				<td id="templateInsuranceNumber"></td><td id="templateFirstName"><td id="templateLastName"></td>
			</tr>
		</table>		
	</body>
</html>