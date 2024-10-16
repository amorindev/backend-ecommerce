package com.fernando.backend_ecommerce.role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface IRoleRepository extends JpaRepository<RoleModel, Long>{
    Optional<RoleModel> findByRoleName(String roleName);
}
