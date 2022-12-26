package servlets;

import models.User;
import repositories.UsersRepository;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.Arrays;

@WebListener
public class AppContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext ctx = sce.getServletContext();
        UsersRepository usersRepository = new UsersRepository(
                Arrays.asList(
                        new User("admin", "admin", "admin"),
                        new User("tester", "tester", "tester"),
                        new User("dev", "dev", "dev")
                )
        );
        ctx.setAttribute("UsersRepository", usersRepository);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
