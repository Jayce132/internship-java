package ro.nymphis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.nymphis.model.user.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {

    Optional<Role> findRoleByRoleName(final String roleName);

}
