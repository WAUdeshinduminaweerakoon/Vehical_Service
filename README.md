# Vehical_Service

Vehicle Service Reservation Web Application
This repository contains a web application for vehicle service reservations, focusing on security, authentication, and access control. 
The application is designed to mitigate OWASP Top 10 vulnerabilities and implement user authentication and access control using OIDC (OpenID Connect) or 
SAML (Security Assertion Markup Language) protocols with a cloud-based Identity Provider (IDP) of your choice.

Application Overview 

Features
Vehicle Service Reservation: Display user profile information of the authenticated user.
Insert vehicle service reservation information.
Delete upcoming vehicle service reservation information.
View all vehicle service reservation information (both past and future reservations).

Authentication and Logout: Implement authentication and logout functionality using OIDC or SAML protocols.
Utilize a cloud-based IDP (Identity Provider) like Asgardeo.
  
  
Security Measures: Mitigate OWASP Top 10 vulnerabilities, including SQL injection, XSS, CSRF, and authentication bypass.
Implement access control to ensure users can only access and delete their own reservation information.
Technology Stack
MySQL DB instance for storing reservation records.
JSP for web application development.
Tomcat 9 server for hosting the application with HTTPS.
Configurable parameters in application.properties or web.xml.

Setup Instructions

my first Repository : https://github.com/WAUdeshinduminaweerakoon/Vehical_Service_Booking_System.git

Clone the Repository: https://github.com/WAUdeshinduminaweerakoon/Vehical_Service.git
Use the provided MySQL instance details to set up the database.
Import the provided table schema into the isec_assessment2 database.
Configure Application:

Edit application.properties or web.xml to set configurable parameters, such as the IDP details.
Run the Application.

For any questions or issues, please contact W.A.Udesh Indumina at udeshidumina@gmail.com.
