package com.sso.auth.payload.credentials;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParamOne {
    private Integer roleId;
    private String roleName;
    private String mailId;
    private String status;
}
