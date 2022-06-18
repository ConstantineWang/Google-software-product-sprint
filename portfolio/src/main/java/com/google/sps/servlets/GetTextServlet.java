package com.google.sps.servlets;

import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.FullEntity;
import com.google.cloud.datastore.KeyFactory;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

/** Handles requests sent to the /hello URL. Try running a server and navigating to /hello! */
@WebServlet("/contact")
public class GetTextServlet extends HttpServlet {

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    // Get the value entered in the form.
    String name = Jsoup.clean(request.getParameter("name"), Whitelist.none());
    String email = request.getParameter("email");
    String message = Jsoup.clean(request.getParameter("message"), Whitelist.none());

    // Print the value so you can see it in the server logs.
    System.out.println("New Name: " + name);
    System.out.println("New email: " + email);
    System.out.println("New message: " + message);
    
    long timestamp = System.currentTimeMillis();

    Datastore datastore = DatastoreOptions.getDefaultInstance().getService();
    KeyFactory keyFactory = datastore.newKeyFactory().setKind("Message");
     FullEntity taskEntity =
        Entity.newBuilder(keyFactory.newKey())
            .set("name", name)
            .set("message", message)
            .set("timestamp",timestamp )
            .build();
    datastore.put(taskEntity);
    response.sendRedirect("/index.html");


    
    response.setContentType("text/html;");
    // Write the value to the response so the user can see it.
    
    response.getWriter().println("<h1>Thanks for contacting me!</h1>");
    response.getWriter().println("<h3>Your Name: " + name + "</h3>");
    response.getWriter().println("<h3>Your email: " + email + "</h3>");
    response.getWriter().println("<h3>Your message: " + message + "</h3>");
    response.getWriter().println("<button onclick='window.location.href=`https://cwang-sps-summer22.wl.r.appspot.com/`;setTimeout(() => window.location.reload(), 250);'>Go Back</button>");
  }
}
