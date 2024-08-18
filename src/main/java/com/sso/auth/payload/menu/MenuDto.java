package com.sso.auth.payload.menu;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MenuDto {
    @NotEmpty(message = "Menu id cannot be empty.")
    private int id;
    @NotEmpty(message = "Menu name id cannot be empty.")
    private String name;
    private String level;
    private String url;
    @NotEmpty(message = "Menu position cannot be empty.")
    private String isRoot;
    private String isParent;
    private String isChild;
    private int parentId;
    @NotEmpty(message = "Application id cannot be empty.")
    private int appId;
//    private int roleId;
    private String correlationId;
    private String responseCode;
    private String responseMessage;
    @NotEmpty(message = "Transaction id cannot be empty.")
    private String transactionId;
}
