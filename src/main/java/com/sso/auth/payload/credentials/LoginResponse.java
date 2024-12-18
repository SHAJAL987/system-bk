package com.sso.auth.payload.credentials;

import com.sso.auth.payload.application.ApplicationList;
import com.sso.auth.payload.application.ApplicationMenuDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class LoginResponse {
    private ParamOne userBasic;
    private List<ApplicationList> applications;
    private List<ApplicationMenuDto> menu;
    private String token;
}
