package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.entity.User;
import web.service.UserService;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/")
    public String allUsers(Model model) {
        model.addAttribute("users", userService.showAll());
        return "users";
    }

    @GetMapping(value = "/new")
    public String creationPage(@ModelAttribute("user") User user, Model model) {
        model.addAttribute(user);
        return "new_user";
    }

    @PostMapping(value = "/")
    public String addOrUpdateUser(@ModelAttribute("user") User user) {
        userService.addOrUpdateUser(user);
        return "redirect:/";
    }

    @GetMapping(value = "/update")
    public String updatePage(@RequestParam("id") Long id, @ModelAttribute("user") User user, Model model) {
        model.addAttribute(userService.getUser(id));
        return "update_user";
    }

    @PostMapping(value = "/delete")
    public String deleteUser(@RequestParam("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/";
    }

}