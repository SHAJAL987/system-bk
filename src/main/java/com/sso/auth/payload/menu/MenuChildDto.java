package com.sso.auth.payload.menu;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MenuChildDto {
    private int id;
    private int appId;
    private String appName;
    private String isChild;
    private String isParent;
    private String level;
    private String name;
    private int parentId;
//    private int roleId;
    private String url;
    private String isRoot;
    private List<MenuChildDto> children = new ArrayList<>();
}
