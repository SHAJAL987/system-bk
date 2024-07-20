package com.sso.auth.payload.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private int id;
    private String mail;
    private String password;
    private String status;
    private String correlationId;
    private String responseCode;
    private String responseMessage;
}
