package sudoku.contest.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sudoku.contest.Models.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findByAuthority(String authority);
}
