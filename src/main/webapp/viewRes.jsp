<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="com.uniquedeveloper.registration.Registration" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
</head>
<body>
   <div class="navbar">
        <div class="logoname"><h1>Vehicle Service</h1></div>
        
    </div>
    <section class="login">
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
            Date today = new Date(); // Get the current date and time
            SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            if (registrations != null) {
                for (Registration registration : registrations) {
                    Date registrationDateTime = dateTimeFormat.parse(registration.getDate() + " " + registration.getTime());
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
                        <% if (registrationDateTime.compareTo(today) > 0) {  %>
                            <button type="submit" class="delete" onclick="return confirm('Are you sure you want to delete this registration?');">Delete</button>
                        <% } else { %>
                            <p>Closed</p>
                        <% }  %>
                    </form>
                </td>
            </tr>
            <%
                }
            }
            %>
        </table>
    </div>
    <a href="home.jsp">Back to Home</a>
    <a href="logout?SAML2.HTTPBinding=HTTP-POST" class="logout">Log Out</a>
        <div class="text">
           <span>Copyright  &copy | Udesh Indumina</span>
        </div>
    </section>
</body>
</html>
