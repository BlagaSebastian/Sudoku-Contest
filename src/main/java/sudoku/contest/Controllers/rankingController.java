package sudoku.contest.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import sudoku.contest.Models.RegistrationObject;
import sudoku.contest.Services.UserService;

import java.util.List;

@Controller
public class rankingController {

    private final UserService userService;

    public rankingController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/ranking")
    public String showRanking(Model model) {
        List<RegistrationObject> usersList = userService.findAllUsers();
        userService.sortByEXP(usersList);
        model.addAttribute("usersList", usersList);
        return "ranking";
    }
}