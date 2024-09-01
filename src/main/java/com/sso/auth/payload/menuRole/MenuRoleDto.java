package com.sso.auth.payload.menuRole;

import jakarta.persistence.Column;

public class MenuRoleDto {
    private Integer id;
    private Integer menuId;
    private Integer roleId;
    private Integer appId;
    private String correlationId;
    private String responseCode;
    private String responseMessage;
}
