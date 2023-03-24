package org.poison.account.core.resp;

import lombok.Data;

@Data
public class LoginResponse {

    private String tokenKey;

    private String tokenValue;

    public LoginResponse(String tokenKey, String tokenValue) {
        this.tokenKey = tokenKey;
        this.tokenValue = tokenValue;
    }
}
