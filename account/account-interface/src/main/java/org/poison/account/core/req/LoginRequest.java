package org.poison.account.core.req;

import lombok.Data;

@Data
public class LoginRequest {

    private String username;

    private String password;
}
