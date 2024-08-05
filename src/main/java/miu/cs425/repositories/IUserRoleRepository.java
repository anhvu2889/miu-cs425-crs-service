package miu.cs425.repositories;

import miu.cs425.models.UserRole;
import miu.cs425.models.UserRoleId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserRoleRepository extends JpaRepository<UserRole, UserRoleId> {
    void deleteByUserUserId(Long id);

    List<UserRole> findByUserUserId(Long id);
}
