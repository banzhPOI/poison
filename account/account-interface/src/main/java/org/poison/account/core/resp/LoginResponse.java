package org.poison.account.core.resp;

import lombok.Data;

@Data
public class LoginResponse {

    private String userId;

    private String tokenKey;

    private String tokenValue;

    public LoginResponse(String userId, String tokenKey, String tokenValue) {
        this.userId = userId;
        this.tokenKey = tokenKey;
        this.tokenValue = tokenValue;
    }
}
