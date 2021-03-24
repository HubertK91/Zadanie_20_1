package pl.hk.zadanie_20_1;

import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class UserController {
    private final UsersRepository usersRepository;

    public UserController(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @ResponseBody
    @GetMapping("/users")
    public String displayListUsers() {
        List<User> listUsers = usersRepository.getListUsers();
        StringBuilder result = new StringBuilder();
        for (User user : listUsers) {
            result.append(user);
        }
        return result.toString();
    }

    @GetMapping("/add")
    public String addUser(@RequestParam(name = "imie", required = false) String firstName,
                          @RequestParam(name = "nazwisko") String lastName, @RequestParam(name = "wiek") Integer age) {
        return createAndAddUser(firstName, lastName, age);
    }

    @PostMapping("/add")
    public String addUserViaForm(@RequestParam String firstName, @RequestParam String lastName, @RequestParam Integer age) {
        return createAndAddUser(firstName, lastName, age);
    }

    private String createAndAddUser(String firstName, String lastName, Integer age) {
        if (ObjectUtils.isEmpty(firstName)) {
            return "redirect:/err.html";
        } else {
            User user = new User(firstName, lastName, age);
            usersRepository.add(user);
            return "redirect:/success.html";
        }
    }
}
