package servlets;

import models.User;
import repositories.UsersRepository;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String login = req.getParameter("login");
        String password = req.getParameter("password");

        RequestDispatcher failedAuth = getServletContext().getRequestDispatcher("/WEB-INF/login.jsp");

        if (login == null || password == null) {
            req.setAttribute("errorMessage", "All fields must be not empty.");
            failedAuth.forward(req, resp);
        }

        UsersRepository usersRepository = (UsersRepository) req.getServletContext().getAttribute("UsersRepository");
        User user = usersRepository.getUserByLogin(login);

        if (user == null || !password.equals(user.getPassword())) {
            req.setAttribute("errorMessage", "Invalid login or password");
            failedAuth.forward(req, resp);
        }

        //Authorization success
        req.getSession().setAttribute("user", user);
        resp.sendRedirect(req.getContextPath() + "/greetings");
    }
}