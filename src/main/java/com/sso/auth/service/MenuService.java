package com.sso.auth.service;

import com.sso.auth.payload.application.ApplicationMenuDto;
import com.sso.auth.payload.menu.MenuChildDto;
import com.sso.auth.payload.menu.MenuDto;

import java.util.List;

public interface MenuService {
    MenuDto saveMenu(String serviceId, MenuDto request);
    List<MenuChildDto> getAllMenus();
    List<ApplicationMenuDto> getMenusByUserId(int userId);
}
