package com.tr.mustafakacar.postyApp.repositories;

import com.tr.mustafakacar.postyApp.entities.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefrestTokenRepository extends JpaRepository<RefreshToken,Long> {
    RefreshToken findByUserId(long userId);
}
