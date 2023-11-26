package com.uniquedeveloper.registration;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import io.asgardeo.java.saml.sdk.util.SSOAgentConstants;
import io.asgardeo.java.saml.sdk.bean.LoggedInSessionBean;
import io.asgardeo.java.saml.sdk.bean.LoggedInSessionBean.SAML2SSO;

@WebServlet("/booking")
public class bookingServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Registration> registrations = new ArrayList<>();
        Connection con = null;

        try {
            // Retrieve the session bean.
            LoggedInSessionBean sessionBean = (LoggedInSessionBean) request.getSession().getAttribute(SSOAgentConstants.SESSION_BEAN_NAME);
            // SAML response
            SAML2SSO samlResponse = sessionBean.getSAML2SSO();
            // Authenticated username
            String username = samlResponse.getSubjectId();

            // Load the MySQL driver and establish a connection
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://172.187.178.153:3306/isec_assessment2?useSSL=false","isec","EUHHaYAmtzbv");

            // Prepare the SQL query with a placeholder for the username
            String sql = "SELECT booking_id, date, time, location, vehicle_no, mileage, message FROM vehicle_service WHERE username = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, username); // Set the username in the query

            // Execute the query
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Registration registration = new Registration();
                registration.setBookingid(rs.getString("booking_id"));
                registration.setDate(rs.getString("date"));
                registration.setTime(rs.getString("time"));
                registration.setLocation(rs.getString("location"));
                registration.setVehicleNo(rs.getString("vehicle_no"));
                registration.setMileage(rs.getString("mileage"));
                registration.setMessage(rs.getString("message"));
                registrations.add(registration);
            }

            request.setAttribute("registrations", registrations);

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

        RequestDispatcher dispatcher = request.getRequestDispatcher("viewRes.jsp");
        dispatcher.forward(request, response);
    }
}