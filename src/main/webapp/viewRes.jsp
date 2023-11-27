<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="com.uniquedeveloper.registration.Registration" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link href="CSS/homeCss.css" rel="stylesheet">
</head>
<body>
   <div class="navbar">
   </div>
   <section class="MSection">
    <section class="login">
    <div class="maindiv">
    <div class="app-name"><h1>View Registration</h1></div>
    <div class="container">
        <table class="table">
            <tr>
                <th><h3>Date</h3></th>
                <th><h3>Time</h3></th>
                <th><h3>Location</h3></th>
                <th><h3>Registration_number</h3></th>
                <th><h3>Mileage</h3></th>
                <th><h3>Message</h3></th>
                <th><h3>Delete</h3></th>
            </tr>
            <%
            List<Registration> registrations = (List<Registration>)request.getAttribute("registrations");
            
            if (registrations != null) {
                for (Registration registration : registrations) {
                    
            %>
            <tr>
                <td><%= registration.getDate() %></td>
                <td><%= registration.getTime() %></td>
                <td><%= registration.getLocation() %></td>
                <td><%= registration.getVehicleNo() %></td>
                <td><%= registration.getMileage() %></td>
                <td><%= registration.getMessage() %></td>
                <td>
                    <form method="post" action="DeleteRegistrationServlet">
                        <input type="hidden" name="registrationId" value="<%= registration.getBookingid() %>">
                        
                            <button type="submit" class="delete" onclick="return confirm('Are you sure you want to delete this registration?');">Delete</button>
                        
                    </form>
                </td>
            </tr>
            <%
                }
            }
            %>
        </table>
    </div>
    <div class="navigation">
    <a href="home.jsp" class="nav-link">Back to Home</a>
    <a href="reservation.jsp" class="nav-link">Make a reservation</a>
    <a href="logout?SAML2.HTTPBinding=HTTP-POST" class="logout" class="nav-link">Log Out</a>
    </div>
    
        
        </div>
         <div class="text">
         <br>
           <span>Copyright  &copy | Udesh Indumina</span>
        </div>
    </section>
    </section>
   
</body>
</html>
