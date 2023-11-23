<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link href="CSS/reservationCss.css" rel="stylesheet">
</head>
<body>
<input type="hidden" id="status" value="<%= request.getAttribute("status")%>">
<section>
<div class="maindiv">
	<h1>Make a reservation</h1>
	<form method="post" action="Registartion">
	<div >
	 <table>
        <tr>
            <td><label for="fname">First Name:</label></td>
            <td><input type="text" id="fname" name="fname" required></td>
        </tr>
        <tr>
            <td><label for="lname">Last Name:</label></td>
            <td><input type="text" id="lname" name="lname" required></td>
        </tr>
        <tr>
            <td><label for="uname">User Name:</label></td>
            <td><input type="text" id="uname" name="uname" required></td>
        </tr>
        <tr>
            <td><label for="email">Email:</label></td>
            <td><input type="email" id="name" name="email" required></td>
        </tr>
        <tr>
            <td><label for="phone">Phone Number:</label></td>
            <td><input type="tel" id="phone" name="phone" required></td>
        </tr>
                <tr>
                <td><label for="country">Country:</label></td>
                <td>
                    <select id="country" name="country">
                        <option value="sl">Sri Lanka</option>
                        <option value="usa">United States</option>
                        <option value="canada">Canada</option>
                        <option value="uk">United Kingdom</option>
                        <option value="australia">Australia</option>
                        <!-- Add more countries as needed -->
                    </select>
                </td>
         </tr>
         <tr>
                <td><label for="location">Location:</label></td>
                <td><input type="text" id="location" name="location"></td>
         </tr>
          <tr>
                <td><label for="date">Date:</label></td>
                <td><input type="date" id="date" name="date"></td>
            </tr>
            <tr>
                <td><label for="time">Time:</label></td>
                <td><input type="time" id="time" name="time"></td>
         </tr>
           <tr>
                <td><label for="reg_number">Vehicle Registration Number:</label></td>
                <td><input type="text" id="reg_number" name="reg_number"></td>
           </tr>
           <tr>
                <td><label for="mileage">Current Mileage:</label></td>
                <td><input type="number" id="mileage" name="mileage"></td>
           </tr>
           <tr>
                <td><label for="message">Message:</label></td>
                <td>
                    <textarea id="message" name="message" rows="4" cols="50"></textarea>
                </td>
            </tr>
        
        
        <tr>
            <td colspan="4" align="center">
                <input type="submit" value="Submit">
            </td>
        </tr>
        </table>
        <a href="index.jsp">Log out</a>
	    
	</div>
	
	
	
	
	</form>
	
	

	
	
	</div>
	<div>
		<span>Copyright  &copy | Udesh Indumina</span>
	</div>
</section>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>

<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>

<script type="text/javascript">
    var status = document.getElementById("status").value;
    if (status === "Registration was successful!") {
        swal("Success", "Registration was successful!", "success");
    } else if (status === "Registration failed. Please try again.") {
        swal("Error", "Registration failed. Please try again.", "error");
    }
</script>
</body>
</html>