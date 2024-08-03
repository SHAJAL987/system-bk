package com.sso.auth.mapper;

import com.sso.auth.model.Menu;
import com.sso.auth.payload.menu.MenuDto;

public class MenuMapper {
    public static Menu mapToMenu(MenuDto request){
        Menu menu = new Menu();
        menu.setName(request.getName());
        menu.setLevel(request.getLevel());
        menu.setUrl(request.getUrl());
        menu.setIsRoot(request.getIsRoot());
        menu.setIsParent(request.getIsParent());
        menu.setIsChild(request.getIsChild());
        menu.setAppId(request.getAppId());
        menu.setParentId(request.getParentId());
        menu.setRoleId(request.getRoleId());
        return menu;
    }
    public static MenuDto mapToMenuDto(Menu request){
        MenuDto menuDto = new MenuDto();
        menuDto.setId(request.getId());
        menuDto.setName(request.getName());
        menuDto.setLevel(request.getLevel());
        menuDto.setUrl(request.getUrl());
        menuDto.setName(request.getName());
        menuDto.setIsRoot(request.getIsRoot());
        menuDto.setParentId(request.getParentId());
        menuDto.setIsChild(request.getIsChild());
        menuDto.setAppId(request.getAppId());
        menuDto.setRoleId(request.getRoleId());
        return menuDto;
    }
}
