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
@Table(name = "MENU_ROLE")
public class MenuRole {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "MENU_ID")
    private Integer menuId;
    @Column(name = "ROLE_ID")
    private Integer roleId;
    @Column(name = "APP_ID")
    private Integer appId;
}
