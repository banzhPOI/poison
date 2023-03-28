package org.poison.account.core.req;

import lombok.Data;

@Data
public class RegisterRequest {

    private String email;

    private String mobile;

    private String username;

    private String password;

    private String optCode;
}
