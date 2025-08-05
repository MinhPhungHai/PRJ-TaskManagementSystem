package com.minhphung.taskmanagementsystem.auth.payload.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class JwtResponse {
    String jwtToken;
    String type = "Bearer";
    Long id;
    String username;
    String email;
    List<String> roles;

    public JwtResponse(String jwtToken, Long id, String username, String email, List<String> roles) {
        this.jwtToken = jwtToken;
        this.id = id;
        this.username = username;
        this.email = email;
        this.roles = roles;
    }
}
