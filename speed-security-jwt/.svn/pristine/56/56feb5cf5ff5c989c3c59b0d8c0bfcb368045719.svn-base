package com.speedfrwk.security.jwt.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserTokenState {

    @JsonProperty("token")
    private String token;

    @JsonProperty("expiration")
    private Long expiration;
}