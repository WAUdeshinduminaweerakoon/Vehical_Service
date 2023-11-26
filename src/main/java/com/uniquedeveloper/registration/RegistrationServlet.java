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

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("uname");
        String date = request.getParameter("date");
        String time = request.getParameter("time");
        String location = request.getParameter("location");
        String vehicle_no = request.getParameter("reg_number");
        String mileage = request.getParameter("mileage");
        String message = request.getParameter("message");
        RequestDispatcher dispatcher = null;
        Connection con = null;

        try {
            // Basic input validation
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
                dispatcher = request.getRequestDispatcher("your_registration_form.jsp"); // Replace with your actual registration form page
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

    // Basic input validation method
    private boolean isValidInput(String username, String date, String time, String location,
                                  String vehicle_no, String mileage, String message) {
        // Implement your validation logic here
        // For example, check if fields are not empty or if they meet specific criteria
        return !username.isEmpty() && !date.isEmpty() && !time.isEmpty() && !location.isEmpty() &&
                !vehicle_no.isEmpty() && !mileage.isEmpty() && !message.isEmpty();
    }
}
