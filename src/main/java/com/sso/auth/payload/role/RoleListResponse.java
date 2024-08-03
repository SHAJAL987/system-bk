package com.sso.auth.payload.role;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoleListResponse {
    private List<RoleList> roleList;
    private String correlationId;
    private String responseCode;
    private String responseMessage;
}
