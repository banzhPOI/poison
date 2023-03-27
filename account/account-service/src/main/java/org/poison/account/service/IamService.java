package org.poison.account.service;

import org.poison.account.core.req.LoginRequest;
import org.poison.account.core.req.LogoutRequest;
import org.poison.account.core.resp.LoginResponse;

public interface IamService {

    LoginResponse login(LoginRequest loginRequest);

    void logout(LogoutRequest logoutRequest);
}
