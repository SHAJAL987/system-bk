package com.sso.auth.payload.credentials;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponse {
    private ParamOne userBasic;
    private String token;
}
