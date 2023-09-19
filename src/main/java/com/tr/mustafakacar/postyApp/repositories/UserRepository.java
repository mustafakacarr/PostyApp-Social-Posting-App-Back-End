package com.tr.mustafakacar.postyApp.repositories;

import com.tr.mustafakacar.postyApp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsername(String username);
}
