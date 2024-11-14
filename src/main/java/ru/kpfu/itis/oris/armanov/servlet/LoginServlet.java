package ru.kpfu.itis.oris.armanov.servlet;


import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.ServletException;

import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet(name = "LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        LOGGER.info("Redirecting to login page");

        resp.sendRedirect("login.html");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String login = req.getParameter("login");
        String password = req.getParameter("password");

        LOGGER.info("Login attempt for user: {}", login);

        if ("login".equalsIgnoreCase(login) && "password".equals(password)) {

            //session
            HttpSession httpSession = req.getSession();
            httpSession.setAttribute("user", login);
            httpSession.setMaxInactiveInterval(60*60);


            //cookie
            Cookie cookie = new Cookie("user", login);
            cookie.setMaxAge(24*60*60);
            resp.addCookie(cookie);
            LOGGER.info("User {} logged in successfully", login);
            resp.sendRedirect("main.jsp");

        } else {

            LOGGER.warn("Failed login attemped for user: {}", login);

            resp.sendRedirect("/login");
        }
    }
}

