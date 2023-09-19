package com.tr.mustafakacar.postyApp.responses;

import com.tr.mustafakacar.postyApp.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserResponse {
Long id;
String username;
int avatarId;
public UserResponse(User user){
    this.id=user.getId();
    this.username=user.getUsername();
    this.avatarId=user.getAvatarId();
}
}
