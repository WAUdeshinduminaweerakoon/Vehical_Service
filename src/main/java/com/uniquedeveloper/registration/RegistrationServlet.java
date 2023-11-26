package com.uniquedeveloper.registration;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import io.asgardeo.java.saml.sdk.util.SSOAgentConstants;
import io.asgardeo.java.saml.sdk.bean.LoggedInSessionBean;
import io.asgardeo.java.saml.sdk.bean.LoggedInSessionBean.SAML2SSO;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	// Validate CSRF token
        String csrfTokenFromSession = (String) request.getSession().getAttribute("csrfToken");
        String csrfTokenFromForm = request.getParameter("csrfToken");

        if (csrfTokenFromSession != null && csrfTokenFromSession.equals(csrfTokenFromForm)) {
         

        String username = request.getParameter("uname");
        String date = request.getParameter("date");
        String time = request.getParameter("time");
        String location = request.getParameter("location");
        String vehicle_no = request.getParameter("reg_number");
        String mileage = request.getParameter("mileage");
        String message = request.getParameter("message");
        RequestDispatcher dispatcher = null;
        Connection con = null;
     
        LoggedInSessionBean sessionBean = (LoggedInSessionBean) request.getSession().getAttribute(SSOAgentConstants.SESSION_BEAN_NAME);
       
        SAML2SSO samlResponse = sessionBean.getSAML2SSO();
        
        String uname = samlResponse.getSubjectId();
        if(!username.equals(uname)){
			RequestDispatcher dispatcher1 = request.getRequestDispatcher("reservation.jsp");
			request.setAttribute("status", "invaliduser");
			dispatcher1.forward(request, response);
			return;
		}
        if(!vehicle_no.matches("[A-Z]{2,3}[0-9]{4}")) {
			RequestDispatcher dispatcher1 = request.getRequestDispatcher("reservation.jsp");
			request.setAttribute("status", "invalidvehicle_no");
			dispatcher1.forward(request, response);
			return;
		}
		if(!mileage.matches("[0-9]+")) {
			RequestDispatcher dispatcher1 = request.getRequestDispatcher("reservation.jsp");
			request.setAttribute("status", "invalidmileage");
			dispatcher1.forward(request, response);
			return;
		}
		if(!message.matches("[a-zA-Z0-9 ]+")) {
			RequestDispatcher dispatcher1 = request.getRequestDispatcher("reservation.jsp");
			request.setAttribute("status", "invalidmsg");
			dispatcher1.forward(request, response);
			return;
		}
		if(!date.matches("[0-9]{4}-[0-9]{2}-[0-9]{2}")) {
			RequestDispatcher dispatcher1 = request.getRequestDispatcher("reservation.jsp");
			request.setAttribute("status", "invaliddate");
			dispatcher1.forward(request, response);
			return;
		}
		if(!time.matches("[0-9]{2}:[0-9]{2}")) {
			RequestDispatcher dispatcher1 = request.getRequestDispatcher("reservation.jsp");
			request.setAttribute("status", "invalidtime");
			dispatcher1.forward(request, response);
			return;
		}
		if(!location.matches("[a-zA-Z0-9]+")) {
			RequestDispatcher dispatcher1 = request.getRequestDispatcher("reservation.jsp");
			request.setAttribute("status", "invalidlocation");
			dispatcher1.forward(request, response);
			return;
		}
        
        

        try {
            
            if (isValidInput(username, date, time, location, vehicle_no, mileage, message)) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://172.187.178.153:3306/isec_assessment2?useSSL=false","isec","EUHHaYAmtzbv");
                PreparedStatement pst = con.prepareStatement("insert into vehicle_service(username,date,time,location,vehicle_no,mileage,message) values(?,?,?,?,?,?,?)");

                pst.setString(1, username);
                pst.setString(2, date);
                pst.setString(3, time);
                pst.setString(4, location);
                pst.setString(5, vehicle_no);
                pst.setString(6, mileage);
                pst.setString(7, message);

                int rowCount = pst.executeUpdate();
                dispatcher = request.getRequestDispatcher("reservation.jsp");
                if (rowCount > 0) {
                    request.setAttribute("status", "Registration was successful!");
                } else {
                    request.setAttribute("status", "Registration failed. Please try again.");
                }
            } else {
                // Input validation failed
                dispatcher = request.getRequestDispatcher("your_registration_form.jsp"); 
                request.setAttribute("status", "Invalid input. Please check your input and try again.");
            }

            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        }
    }

        private boolean isValidInput(String username, String date, String time, String location,
                                  String vehicle_no, String mileage, String message) {
       
        return !username.isEmpty() && !date.isEmpty() && !time.isEmpty() && !location.isEmpty() &&
                !vehicle_no.isEmpty() && !mileage.isEmpty() && !message.isEmpty();
    }
}










