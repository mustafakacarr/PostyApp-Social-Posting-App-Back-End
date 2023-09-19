package com.tr.mustafakacar.postyApp.services;

import com.tr.mustafakacar.postyApp.entities.RefreshToken;
import com.tr.mustafakacar.postyApp.entities.User;
import com.tr.mustafakacar.postyApp.repositories.RefrestTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Ref;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@Service
public class RefreshTokenService {
    @Autowired
    RefrestTokenRepository refrestTokenRepository;

    @Value("${refresh.token.expires.in}")
    long expireSeconds;

    public boolean isRefreshExpired(RefreshToken refreshToken) {
        return refreshToken.getExpireDate().before(new Date());
    }

    public String createRefreshToken(User user) {
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setUser(user);
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken.setExpireDate(Date.from(Instant.now().plusSeconds(expireSeconds)));
        RefreshToken refreshTokenOfUser = refrestTokenRepository.findByUserId(user.getId());
        if (refreshTokenOfUser != null)
            refreshToken.setId(refreshTokenOfUser.getId());
        refrestTokenRepository.save(refreshToken);
        return refreshToken.getToken();
    }

    public RefreshToken getByUserId(long userId) {
    return refrestTokenRepository.findByUserId(userId);
    }
}
