package com.sso.auth.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "APPLICATION_INFO")
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    @Column(name = "APP_CODE")
    Integer appCode;
    @Column(name = "APP_NAME")
    String appName;
    @Column(name = "APP_URL")
    String appUrl;
    @Column(name = "APP_STATUS")
    String appStatus;
}
