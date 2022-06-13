package com.google.sps.servlets;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** Handles requests sent to the /hello URL. Try running a server and navigating to /hello! */
@WebServlet("/contact")
public class GetTextServlet extends HttpServlet {

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    // Get the value entered in the form.
    String name = request.getParameter("name");
    String email = request.getParameter("email");
    String message = request.getParameter("message");

    // Print the value so you can see it in the server logs.
    System.out.println("New Name: " + name);
    System.out.println("New email: " + email);
    System.out.println("New message: " + message);

    response.setContentType("text/html;");
    // Write the value to the response so the user can see it.
    
    response.getWriter().println("<h1>Thanks for contacting me!</h1>");
    response.getWriter().println("<h3>Your Name: " + name + "</h3>");
    response.getWriter().println("<h3>Your email: " + email + "</h3>");
    response.getWriter().println("<h3>Your message: " + message + "</h3>");
    response.getWriter().println("<button onclick='window.location.href=`https://cwang-sps-summer22.wl.r.appspot.com/`;setTimeout(() => window.location.reload(), 250);'>Go Back</button>");
  }
}