package sudoku.contest.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import sudoku.contest.Models.ApplicationUser;
import sudoku.contest.Models.RegistrationObject;
import sudoku.contest.Services.UserService;

import javax.validation.Valid;

@Controller
public class AuthenticationController {

    private final UserService userService;

    public AuthenticationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public String viewHomePage() {
        return "home";
    }

    @GetMapping("/register")
    public String registrationForm(Model model) {
        RegistrationObject ro = new RegistrationObject();
        model.addAttribute("ro", ro);
        return "registration";
    }


    @PostMapping("/register")
    public String registration(@Valid @ModelAttribute("ro") RegistrationObject ro,
                               BindingResult result,
                               Model model) {
        ApplicationUser tempUser = userService.findByUsername(ro.getUsername());
        if (tempUser != null) {
            result.rejectValue("username", null);
            return "registrationError";
        }
        if (result.hasErrors()) {
            model.addAttribute("ro", ro);
            return "registration";
        }
        userService.registerUser(ro);
        return "login";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
