package com.uniquedeveloper.registration;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.uniquedeveloper.registration.Registration;
import com.uniquedeveloper.registration.RegistrationDAO; // Assuming you have a DAO class for handling database operations

@WebServlet("/DeleteRegistrationServlet")
public class DeleteRegistrationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve the registration ID from the form
        int registrationId = Integer.parseInt(request.getParameter("registrationId"));

        // Assuming you have a DAO class for handling database operations
        RegistrationDAO registrationDAO = new RegistrationDAO();

        try {
            // Retrieve the registration object by ID
            Registration registration = registrationDAO.getRegistrationById(registrationId);

            if (registration != null) {
                // Delete the registration
                registrationDAO.deleteRegistration(registration);

                // Redirect to the page where registrations are listed
                response.sendRedirect("ListRegistrations.jsp");
            } else {
                // Handle the case where the registration was not found
                response.getWriter().println("Registration not found!");
            }
        } catch (Exception e) {
            // Handle any exceptions that may occur during the deletion process
            e.printStackTrace();
            response.getWriter().println("An error occurred while deleting the registration.");
        }
    }
}
