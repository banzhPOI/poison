package org.poison.account.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "banzh-account-service", contextId = "user")
public interface UserClient {

}
