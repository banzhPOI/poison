package org.poison.account.core.req;

import lombok.Data;

@Data
public class UserGetRequest {

    public UserGetRequest(String userId) {
        this.userId = userId;
    }

    private String userId;
}
