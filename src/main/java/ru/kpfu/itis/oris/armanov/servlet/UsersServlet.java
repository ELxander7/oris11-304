package ru.kpfu.itis.oris.armanov.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.kpfu.itis.oris.armanov.dto.UserDto;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet(name = "userServlet", urlPatterns = "/user")
public class UsersServlet extends HttpServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(UsersServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        LOGGER.info("Fetching users list");

        req.setAttribute("users", List.of(new UserDto("Ivan", 100, "Ivan228")));

        req.getRequestDispatcher("users.ftl").forward(req, resp);

        LOGGER.info("Forwarded to users.ftl") ;
    }
}
