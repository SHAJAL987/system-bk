package com.sso.auth.payload.menu;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MenuCommon {
    private int id;
    private String name;
    private String app_name;
    private String level;
    private String url;
    private String is_root;
    private String is_parent;
    private String is_child;
    private int parent_id;
    private int app_id;
}
