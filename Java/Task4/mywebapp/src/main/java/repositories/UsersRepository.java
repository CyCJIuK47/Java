package repositories;

import models.User;

import java.util.ArrayList;
import java.util.List;

public class UsersRepository {
    private List<User> users;

    public UsersRepository() {
        this.users = new ArrayList<>();
    }

    public UsersRepository(List<User> users) {
        this.users = users;
    }

    public List<User> getAll()
    {
        return users;
    }

    public User getUserByLogin(String login) {
        return users.stream().filter(x->x.getLogin().equals(login)).findFirst().orElse(null);
    }
}
