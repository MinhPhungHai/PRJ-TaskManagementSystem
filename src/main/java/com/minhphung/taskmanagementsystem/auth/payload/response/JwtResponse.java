package com.minhphung.taskmanagementsystem.auth.payload.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class JwtResponse {
    String jwtToken = "Bearer ";

    public JwtResponse(String jwtToken) {
        this.jwtToken += jwtToken;
    }
}
