package servlets;

import repositories.UsersRepository;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/userList")
public class UserListServlet extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        UsersRepository usersRepository = (UsersRepository) req.getServletContext().getAttribute("UsersRepository");
        req.setAttribute("userList", usersRepository.getAll());

        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/WEB-INF/userList.jsp");
        requestDispatcher.forward(req, resp);
    }
}