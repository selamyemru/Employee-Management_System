package com.itsc;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class OperationServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String operation = request.getParameter("operation");

        if ("addition".equals(operation)) {
            request.getRequestDispatcher("/AdditionServlet").forward(request, response);
        } else if ("multiplication".equals(operation)) {
            request.getRequestDispatcher("/MultiplicationServlet").forward(request, response);
        } else {
            response.getWriter().print("Invalid operation");
        }
    }

}
