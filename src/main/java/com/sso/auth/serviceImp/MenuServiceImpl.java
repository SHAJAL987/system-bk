package com.sso.auth.serviceImp;

import com.sso.auth.Utilities.ResponseEnum;
import com.sso.auth.exception.ResourceNotFoundException;
import com.sso.auth.mapper.MenuMapper;
import com.sso.auth.model.Menu;
import com.sso.auth.payload.CommonResponse;
import com.sso.auth.payload.application.ApplicationMenuCustomDto;
import com.sso.auth.payload.application.ApplicationMenuDto;
import com.sso.auth.payload.menu.MenuChildDto;
import com.sso.auth.payload.menu.MenuCommon;
import com.sso.auth.payload.menu.MenuDto;
import com.sso.auth.repository.MenuRepository;
import com.sso.auth.service.MenuService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MenuServiceImpl implements MenuService {
    private MenuRepository menuRepository;
    @Override
    public MenuDto saveMenu(String serviceId, MenuDto request) {
        MenuDto response = new MenuDto();
        request.setIsParent(request.getIsParent().toUpperCase());
        request.setIsChild(request.getIsChild().toUpperCase());
        Menu menu = MenuMapper.mapToMenu(request);
        menu = menuRepository.save(menu);
        response = MenuMapper.mapToMenuDto(menu);
        response.setCorrelationId(serviceId);
        response.setResponseCode(ResponseEnum.ResponseCode.REQUEST_SUCCESS.getCode());
        response.setResponseMessage(ResponseEnum.ResponseCode.REQUEST_SUCCESS.getMessage());
        response.setTransactionId(request.getTransactionId());
        return response;
    }

    @Override
    public List<MenuChildDto> getAllMenus() {
        List<Menu> allMenus = menuRepository.findAllMenus();
        Map<Integer, MenuChildDto> menuMap = allMenus.stream()
                .map(menu -> new MenuChildDto(
                        menu.getId(),
                        menu.getAppId(),
                        menu.getAppName(),  // Using appName now
                        menu.getIsChild(),
                        menu.getIsParent(),
                        menu.getLevel(),
                        menu.getName(),
                        menu.getParentId(),
                        menu.getUrl(),
                        menu.getIsRoot(),
                        new ArrayList<>()
                ))
                .collect(Collectors.toMap(MenuChildDto::getId, menuDTO -> menuDTO));

        List<MenuChildDto> rootMenus = new ArrayList<>();
        for (MenuChildDto menuDTO : menuMap.values()) {
            if (menuDTO.getParentId() == 0) {
                rootMenus.add(buildMenuHierarchy(menuDTO, menuMap));
            }
        }
        return rootMenus;
    }

    @Override
    public List<ApplicationMenuDto> getMenusByUserId(int userId) {
        List<Object[]> results = menuRepository.findMenuByUserId(userId);
        Map<Integer, MenuChildDto> menuMap = new HashMap<>();
        Map<Integer, ApplicationMenuDto> appMenuMap = new LinkedHashMap<>();

        for (Object[] row : results) {
            // Create menu DTO
            MenuChildDto menu = new MenuChildDto();
            menu.setId((Integer) row[0]);
            menu.setAppId((Integer) row[1]);
            menu.setAppName((String) row[2]);
            menu.setIsChild((String) row[3]);
            menu.setIsParent((String) row[4]);
            menu.setLevel((String) row[5]);
            menu.setName((String) row[6]);
            menu.setParentId((Integer) row[7]);
            menu.setUrl((String) row[8]);
            menu.setIsRoot((String) row[9]);

            menuMap.put(menu.getId(), menu);

            // Group by application
            int appId = menu.getAppId();
            if (!appMenuMap.containsKey(appId)) {
                ApplicationMenuDto appMenu = new ApplicationMenuDto();
                appMenu.setAppId(appId);
                appMenu.setAppName(menu.getAppName());
                appMenuMap.put(appId, appMenu);
            }
        }

        // Build hierarchical structure
        for (MenuChildDto menu : menuMap.values()) {
            if (menu.getParentId() == 0) {
                appMenuMap.get(menu.getAppId()).getChildren().add(menu);
            } else {
                MenuChildDto parentMenu = menuMap.get(menu.getParentId());
                if (parentMenu != null) {
                    parentMenu.getChildren().add(menu);
                }
            }
        }

        return new ArrayList<>(appMenuMap.values());
    }

    @Override
    public List<ApplicationMenuCustomDto> fetchApplicationMenus() {
        Map<Integer, ApplicationMenuCustomDto> appMap = new HashMap<>();
        Map<Integer, MenuChildDto> menuMap = new HashMap<>();

        // Step 1: Populate appMap with ApplicationMenuCustomDto and menuMap with MenuChildDto
        for (Object[] row : menuRepository.fetchApplicationWithMenus()) {
            Integer appId = (Integer) row[0];  // app_id
            String appName = (String) row[1];  // app_name
            Integer menuId = (Integer) row[2]; // menu_id
            String menuName = (String) row[3]; // menu_name
            Integer parentId = (Integer) row[4]; // parent_id
            String url = (String) row[5]; // menu_url
            String isRoot = (String) row[6]; // is_root
            String isChild = (String) row[7]; // is_child
            String isParent = (String) row[8]; // is_parent
            String level = (String) row[9]; // level

            // Ensure the application is in the appMap, even if there are no menus for this app
            ApplicationMenuCustomDto app = appMap.getOrDefault(appId, new ApplicationMenuCustomDto(appId, appName, new ArrayList<>()));

            // Create MenuChildDto object only if menuId is not null
            if (menuId != null) {
                // Create MenuChildDto object
                MenuChildDto menu = new MenuChildDto(menuId, appId, appName, isChild, isParent, level, menuName, parentId, url, isRoot, new ArrayList<>());

                // Put menu in the map for later reference (parent-child relationship)
                menuMap.put(menuId, menu);

                // Add the menu to its respective application
                appMap.putIfAbsent(appId, app); // Ensure app is initialized before adding

                if (parentId == 0) {
                    app.getChildren().add(menu);  // If it's a root menu, add directly to children
                } else {
                    // Add menu to the parent (parent exists in menuMap)
                    MenuChildDto parentMenu = menuMap.get(parentId);
                    if (parentMenu != null) {
                        parentMenu.getChildren().add(menu);  // Add this menu as a child of the parent menu
                    }
                }
            } else {
                // If there's no menuId, ensure the app is still included with empty children
                appMap.putIfAbsent(appId, app);
            }
        }

        // Step 2: Return all applications with their menus, including those with no menus
        return new ArrayList<>(appMap.values());
    }

    @Override
    public CommonResponse menuDeletedById(int menuId,String correlationId) {
        CommonResponse response = new CommonResponse();
        if (!menuRepository.existsById(menuId)){
            throw new ResourceNotFoundException("Menu with Id Not found","Menu Id",String.valueOf(menuId));
        }
        menuRepository.deleteById(menuId);
        response.setCorrelationId(correlationId);
        response.setResponseCode(ResponseEnum.ResponseCode.REQUEST_SUCCESS.getCode());
        response.setResponseMessage(ResponseEnum.ResponseCode.REQUEST_SUCCESS.getMessage());
        return response;
    }

    private MenuChildDto buildMenuHierarchy(MenuChildDto menuDTO, Map<Integer, MenuChildDto> menuMap) {
        List<MenuChildDto> children = menuMap.values().stream()
                .filter(child -> child.getParentId() == menuDTO.getId())
                .map(child -> buildMenuHierarchy(child, menuMap))
                .collect(Collectors.toList());
        menuDTO.setChildren(children);
        return menuDTO;
    }
}
