package ru.kpfu.itis.oris.armanov.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;

@WebServlet(urlPatterns = "/logout")
public class LogoutServlet extends HttpServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogoutServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        clear(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        clear(req, resp);
    }

    public void clear(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie c : cookies) {
                c.setMaxAge(0);
                resp.addCookie(c);

                LOGGER.info("Cleared cookie: {}", c.getName());
            }
        }

        HttpSession session = req.getSession();
        if (session != null) {
            session.invalidate();

            LOGGER.info("User session invalidated");
        }

        resp.sendRedirect("index.html");

    }
}
