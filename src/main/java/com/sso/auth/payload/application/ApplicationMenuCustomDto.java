package com.sso.auth.payload.application;

import com.sso.auth.payload.menu.MenuChildDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationMenuCustomDto {
    private int appId;
    private String name;
    private List<MenuChildDto> children = new ArrayList<>();
}
