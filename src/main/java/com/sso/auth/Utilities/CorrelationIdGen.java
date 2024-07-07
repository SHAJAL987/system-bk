package com.sso.auth.Utilities;

import java.util.UUID;

public class CorrelationIdGen {
    public static String getCorrelationId(){
        return UUID.randomUUID().toString();
    }
}
