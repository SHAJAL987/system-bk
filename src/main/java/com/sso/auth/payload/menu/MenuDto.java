package com.sso.auth.payload.menu;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MenuDto {
    private int id;
    private String name;
    private String level;
    private String url;
    private String isRoot;
    private String isParent;
    private String isChild;
    private int parentId;
    private int appId;
    private int roleId;
    private String correlationId;
    private String responseCode;
    private String responseMessage;
}
