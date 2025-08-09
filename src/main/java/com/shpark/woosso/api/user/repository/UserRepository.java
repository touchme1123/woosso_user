package com.shpark.woosso.api.user.repository;

import com.shpark.woosso.api.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    Boolean existsByUserId(String userId);

    User findByUserId(String userId);
}
