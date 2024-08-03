package com.sso.auth.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "MENU")
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "LEVEL")
    private String level;
    @Column(name = "URL")
    private String url;
    @Column(name = "IS_ROOT")
    private String isRoot;
    @Column(name = "IS_PARENT")
    private String isParent;
    @Column(name = "IS_CHILD")
    private String isChild;
    @Column(name = "PARENT_ID")
    private int parentId;
    @Column(name = "APP_ID")
    private int appId;
    @Column(name = "ROLE_ID")
    private int roleId;
}
