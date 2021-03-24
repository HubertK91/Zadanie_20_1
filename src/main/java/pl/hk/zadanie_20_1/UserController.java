package pl.hk.zadanie_20_1;

import org.springframework.stereotype.Controller;
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
        String result = "";
        for (User user : listUsers) {
            result += user.getFirstName() + " " + user.getLastName() + " wiek: " + user.getAge() + "<br/>";
        }
        return result;
    }

    @GetMapping("/add")
    public String addUser(@RequestParam String imie, String nazwisko, Integer wiek) {
        User user = new User(imie, nazwisko, wiek);
        if (imie == null || imie.isEmpty()){
            return "redirect:/err.html";
        }else {
            usersRepository.add(user);
            return "redirect:/success.html";
        }
    }

    @PostMapping("/hello")
    public String addUserViaForm(@RequestParam String firstName, String lastName, Integer age) {
        User user = new User(firstName, lastName, age);
        if (firstName == null || firstName.isEmpty()){
            return "redirect:/err.html";
        }else {
            usersRepository.add(user);
            return "redirect:/success.html";
        }
    }

}
