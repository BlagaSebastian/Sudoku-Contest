package sudoku.contest.Services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sudoku.contest.Models.ApplicationUser;
import sudoku.contest.Models.RegistrationObject;
import sudoku.contest.Models.Role;
import sudoku.contest.Repositories.RoleRepository;
import sudoku.contest.Repositories.UserRepository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepo;
    private final RoleRepository roleRepo;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepo, RoleRepository roleRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.passwordEncoder = passwordEncoder;
    }

    public void registerUser(RegistrationObject ro) {
        ApplicationUser user = new ApplicationUser();
        user.setUsername(ro.getUsername());
        user.setEmail(ro.getEmail());
        user.setPassword(passwordEncoder.encode(ro.getPassword()));

        Role authority = roleRepo.findByAuthority("USER");
        if (authority == null) {
            authority = createNewRole();
        }
        user.setAuthorities(List.of(authority));
        userRepo.save(user);
    }

    private Role createNewRole() {
        Role authority = new Role();
        authority.setAuthority("ROLE_USER");
        return roleRepo.save(authority);
    }

    public ApplicationUser findByUsername(String username) {
        return userRepo.findByUsername(username);
    }

    public List<RegistrationObject> findAllUsers() {
        List<ApplicationUser> usersList = userRepo.findAll();
        return usersList.stream().map(this::convertEntityToObject)
                .collect(Collectors.toList());
    }

    private RegistrationObject convertEntityToObject(ApplicationUser user) {
        RegistrationObject ro = new RegistrationObject();
        ro.setUsername(user.getUsername());
        ro.setExp(user.getExperiencePoints());
        return ro;
    }

    public void sortByEXP(List<RegistrationObject> usersList) {
        usersList.sort(Comparator.comparing(RegistrationObject::getExp).reversed());
    }
}
