package com.itsc;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MultiplicationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try {
            int num1 = Integer.parseInt(request.getParameter("num1"));
            int num2 = Integer.parseInt(request.getParameter("num2"));

            // Perform multiplication
            int result = num1 * num2;

            // HTML styles for the result
            String resultMessage = "<div style='text-align: center; padding: 20px; background-color: #27AE60; color: #ECF0F1; border-radius: 10px;width:50%'>";
            resultMessage += "<h3>Multiplication Result:</h3>";
            resultMessage += "<p style='font-size: 34px;'>" + result + "</p>";
            resultMessage += "</div>";

            response.getWriter().print(resultMessage);
        } catch (NumberFormatException e) {
            // HTML styles for the error message
            String errorMessage = "<div style='text-align: center; padding: 20px; background-color: #E74C3C; color: #ECF0F1; border-radius: 10px;'>";
            errorMessage += "<h3>Error:</h3>";
            errorMessage += "<p style='font-size: 18px;'>Invalid input. Please enter valid numbers.</p>";
            errorMessage += "</div>";

            response.getWriter().print(errorMessage);
        }
    }
}
