package com.sso.auth.logging;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "DB_LOGGER")
public class DbLogger {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(
            name = "ID",
            unique = true,
            nullable = false,
            updatable = false
    )
    private int id;
    @Column(name = "CORRELATION_ID")
    private String correlationId;
    @Column(name = "IP")
    private String ip;
    @Column(name = "OPERATION_NAME")
    private String operationName;
    @Column(name = "REQ_RES_TYPE")
    private String reqResType;
    @Column(
            name = "REQ_RES_LOG",
            length = 5000
    )
    private String reqResLog;
    private String code;
    @Column(name = "TIMESTAMP")
    private Date timeStamp;
}
