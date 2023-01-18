package sudoku.contest.Services;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import sudoku.contest.Models.ApplicationUser;
import sudoku.contest.Repositories.UserRepository;

import java.util.stream.Collectors;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final UserRepository userRepo;

    public UserDetailsService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ApplicationUser user = userRepo.findByUsername(username);
        if (user != null) {
            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                    user.getAuthorities().stream().map((role) -> new SimpleGrantedAuthority(role.getAuthority()))
                            .collect(Collectors.toList()));
        } else {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
    }
}
