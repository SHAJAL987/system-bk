package com.sso.auth.mapper;

import com.sso.auth.payload.credentials.ParamOne;

public class LogingMapper {
    public static ParamOne convertToParamOne(Object[] resultArray) {
        Integer roleId = ((Number) resultArray[0]).intValue(); // Casting to Number, then getting int value
        String roleName = (String) resultArray[1];
        String mailId = (String) resultArray[2];
        String status = (String) resultArray[3];

        return new ParamOne(roleId, roleName, mailId, status);
    }
}
