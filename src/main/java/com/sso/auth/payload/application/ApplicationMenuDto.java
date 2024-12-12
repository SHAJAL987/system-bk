package com.sso.auth.payload.application;

import com.sso.auth.payload.menu.MenuChildDto;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ApplicationMenuDto {
    private int appId;
    private String appName;
    private List<MenuChildDto> menus = new ArrayList<>();
}
