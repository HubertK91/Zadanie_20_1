package pl.hk.zadanie_20_1;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UsersRepository {
    private final List<User> listUsers = new ArrayList<>();

    public UsersRepository() {
        User user1 = new User("Jan", "Kowalski", 28);
        User user2 = new User("Monika", "Zawadzka", 38);
        User user3 = new User("Michał", "Nowak", 18);
        listUsers.add(user1);
        listUsers.add(user2);
        listUsers.add(user3);
    }

    public List<User> getListUsers() {
        return listUsers;
    }

    public void add(User user){
        listUsers.add(user);
    }
}
