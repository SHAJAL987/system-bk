package com.sso.auth.payload.role;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoleDto {
    private int id;
    private String roleName;
    private String correlationId;
    private String responseCode;
    private String responseMessage;
}
