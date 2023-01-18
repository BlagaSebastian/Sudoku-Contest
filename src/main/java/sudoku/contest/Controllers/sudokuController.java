package sudoku.contest.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sudoku.contest.Models.ApplicationUser;
import sudoku.contest.Models.Sudoku;
import sudoku.contest.Repositories.UserRepository;
import sudoku.contest.Services.SudokuService;
import sudoku.contest.Services.UserService;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;


@Controller
public class sudokuController {

    private final UserService userService;
    private final SudokuService sudokuService;
    private final UserRepository userRepo;

    public sudokuController(UserService userService, SudokuService sudokuService, UserRepository userRepo) {
        this.userService = userService;
        this.sudokuService = sudokuService;
        this.userRepo = userRepo;
    }

    @GetMapping("/main")
    public String goToMainPage() {
        return "main";
    }

    @GetMapping("/sudoku/generate")
    public String playGame(Model model) {
        Sudoku game = new Sudoku();
        game = sudokuService.generateGame(game);
        model.addAttribute("puzzle", game);
        return "sudokuPuzzle";
    }

    @ResponseBody
    @PostMapping("/solution/check")
    public String gameResult(@RequestParam(value = "gameBoard") int[][] gameBoard,
                             HttpServletRequest request) {
        if (sudokuService.solutionCheck(gameBoard)) {
            Principal principal = request.getUserPrincipal();
            ApplicationUser user = userService.findByUsername(principal.getName());
            user.setExperiencePoints(user.getExperiencePoints() + 100);
            userRepo.save(user);
            return "Your solution is correct, congrats!";
        }
        return "I'm afraid your solution is incorrect.";
    }
}
