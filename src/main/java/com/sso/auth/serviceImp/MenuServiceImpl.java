package com.sso.auth.serviceImp;

import com.sso.auth.Utilities.ResponseEnum;
import com.sso.auth.mapper.MenuMapper;
import com.sso.auth.model.Menu;
import com.sso.auth.payload.menu.MenuChildDto;
import com.sso.auth.payload.menu.MenuDto;
import com.sso.auth.repository.MenuRepository;
import com.sso.auth.service.MenuService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
        return response;
    }

    @Override
    public List<MenuChildDto> getAllMenus() {
        List<Menu> allMenus = menuRepository.findAllMenus();
        Map<Integer, MenuChildDto> menuMap = allMenus.stream()
                .map(menu -> new MenuChildDto(
                        menu.getId(),
                        menu.getAppId(),
                        menu.getIsChild(),
                        menu.getIsParent(),
                        menu.getLevel(),
                        menu.getName(),
                        menu.getParentId(),
                        menu.getRoleId(),
                        menu.getUrl(),
                        menu.getIsRoot(),
                        new ArrayList<>()
                ))
                .collect(Collectors.toMap(MenuChildDto::getId, menuDTO -> menuDTO));

        // Create the hierarchical structure
        List<MenuChildDto> rootMenus = new ArrayList<>();
        for (MenuChildDto menuDTO : menuMap.values()) {
            if (menuDTO.getParentId() == 0) {
                rootMenus.add(buildMenuHierarchy(menuDTO, menuMap));
            }
        }
        return rootMenus;
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
