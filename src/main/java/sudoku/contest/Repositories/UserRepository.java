package sudoku.contest.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sudoku.contest.Models.ApplicationUser;


@Repository
public interface UserRepository extends JpaRepository<ApplicationUser, Integer> {
    ApplicationUser findByUsername(String username);
}
