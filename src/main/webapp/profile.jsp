<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@ page import="io.asgardeo.java.saml.sdk.util.SSOAgentConstants" %>
<%@ page import="io.asgardeo.java.saml.sdk.bean.LoggedInSessionBean" %>
<%@ page import="io.asgardeo.java.saml.sdk.bean.LoggedInSessionBean.SAML2SSO" %>
<%@ page import="java.util.Map" %>
<%
// Retrieve the session bean.
LoggedInSessionBean sessionBean = (LoggedInSessionBean) session.getAttribute(SSOAgentConstants.SESSION_BEAN_NAME);
// SAML response
SAML2SSO samlResponse = sessionBean.getSAML2SSO();
// Autheticated username
String subjectId = samlResponse.getSubjectId();
// Authenticated user's attributes
Map<String, String> saml2SSOAttributes = samlResponse.getSubjectAttributes();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link href="CSS/proCss.css" rel="stylesheet">
</head>
<body>
<section>
	<div class="maindiv">
	<div>
	</div>
	     <h1>View profile information</h1>
	<div>
	<div>
		<table class="table">
		<% 
					String uname = null;
					String email = null;
					String country = null;
					String mobile = null;
					String name = null;
					if (saml2SSOAttributes != null) {
					    for (Map.Entry<String, String> entry : saml2SSOAttributes.entrySet()) {
					        String attributeName = entry.getKey();
					        String attributeValue = entry.getValue();
					        if ("http://wso2.org/claims/username".equals(attributeName)) {
					             uname = attributeValue;
					        }
					        else if ("http://wso2.org/claims/emailaddress".equals(attributeName)) {
					             email = attributeValue;
					        }
					        else if ("http://wso2.org/claims/country".equals(attributeName)) {
					             country = attributeValue;
					        }
					        else if ("http://wso2.org/claims/mobile".equals(attributeName)) {
					            mobile = attributeValue;
					        }
					        else if ("http://wso2.org/claims/givenname".equals(attributeName)) {
					            name = attributeValue;
					        }
					    }
					}
				%>
			
			<tr>
			<th><h3>Name</h3></th>
		    <th>:</th>
			<th><h3><%=name %></h3></th>
			</tr>
			<tr>
			<th><h3>User name</h3></th>
			<th>:</th>
			<th><h3><%=uname %></h3></th>
			</tr>
			<tr>
			<th><h3>User Email</h3></th>
			<th>:</th>
			<th><h3><%=email %></h3></th>
			</tr>
			<tr>
			<th><h3>Phone Number</h3></th>
			<th>:</th>
			<th><h3><%=mobile %></h3></th>
			</tr>
			<tr>
			<th><h3>User Country</h3></th>
			<th>:</th>
			<th><h3><%=country %></h3></th>
			</tr>
		</table>
		<a href="home.jsp">Back to Home</a>
		<a href="index.jsp">Log out</a>
	</div>
	</div>
	</div>
	
		<span>Copyright  &copy | Udesh Indumina</span>
	
</section>
</body>
</html>