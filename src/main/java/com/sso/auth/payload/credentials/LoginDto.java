package com.sso.auth.payload.credentials;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDto {
    private String mail;
    private String password;
}
