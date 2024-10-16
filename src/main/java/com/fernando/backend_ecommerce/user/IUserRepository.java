package com.fernando.backend_ecommerce.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface IUserRepository extends JpaRepository<UserModel, Long>{
    Optional<UserModel> findByUserEmail(String userEmail);
}
