package com.tr.mustafakacar.postyApp.requests;

import lombok.Data;

@Data
public class RefreshRequest {
    long userId;
    String refreshToken;
}
  //  Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI2NTIiLCJpYXQiOjE2OTQwMTI1NjUsImV4cCI6MTY5NDAxMjU2OX0.xs2U0or1AWu3-34U5cEhKfVfGk2lpUKUn82jfO46RI4
//Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI2NTIiLCJpYXQiOjE2OTQwMTI4NDAsImV4cCI6MTY5NDAxMjg0NH0.fsw5DfLHPG_jJXIKI-RQIgTkE22b6VSHIwJ6E2P-FRc