package com.sso.auth.payload.userRole;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRoleDto {
    private int id;
    private int userId;
    private int roleId;
    private String correlationId;
    private String responseCode;
    private String responseMessage;
}
