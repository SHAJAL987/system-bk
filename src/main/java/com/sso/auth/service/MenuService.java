package com.sso.auth.service;

import com.sso.auth.payload.CommonResponse;
import com.sso.auth.payload.application.ApplicationMenuCustomDto;
import com.sso.auth.payload.application.ApplicationMenuDto;
import com.sso.auth.payload.menu.MenuChildDto;
import com.sso.auth.payload.menu.MenuDto;

import java.util.List;

public interface MenuService {
    MenuDto saveMenu(String serviceId, MenuDto request);
    List<MenuChildDto> getAllMenus();
    List<ApplicationMenuDto> getMenusByUserId(int userId);
    List<ApplicationMenuCustomDto> fetchApplicationMenus();
    CommonResponse menuDeletedById(int menuId,String correlationId);
    CommonResponse menuUpdatedById(int menuId,String correlationId);
}
