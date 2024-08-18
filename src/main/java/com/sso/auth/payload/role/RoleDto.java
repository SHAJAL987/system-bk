package com.sso.auth.payload.role;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoleDto {
    private int id;
    @NotEmpty(message = "Role name cannot be empty.")
    private String roleName;
    private String correlationId;
    private String responseCode;
    private String responseMessage;
    @NotEmpty(message = "Transaction id cannot be empty.")
    private String transactionId;
}
