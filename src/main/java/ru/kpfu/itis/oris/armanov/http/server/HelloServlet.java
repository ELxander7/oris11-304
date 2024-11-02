package ru.kpfu.itis.oris.armanov.http.server;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Collectors;

@WebServlet(name = "HelloServlet", urlPatterns = "/hello")

public class HelloServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String requestBody = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        PrintWriter writer = resp.getWriter();
        writer.println(requestBody);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("hello");

    }
}
