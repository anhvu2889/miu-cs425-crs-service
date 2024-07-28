package miu.cs425.repositories;

import miu.cs425.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IRoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByRoleName(String roleName);

    @Query("select r from Role r join r.users u where u.username = :username")
    List<Role> findRolesByUserName(@Param("username") String username);
}
