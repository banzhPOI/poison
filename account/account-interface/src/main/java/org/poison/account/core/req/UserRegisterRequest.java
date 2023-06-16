package org.poison.account.core.req;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.poison.common.exception.BizException;

@Data
public class UserRegisterRequest {

    private String nickname;

    private String email;

    private String mobile;

    private String password;

    public void checkParam() {
        // 不能全为空
        if (StringUtils.isAllBlank(email, mobile)) {
            throw new BizException("请输入正确的注册信息");
        }
        if (StringUtils.isBlank(password)) {
            throw new BizException("密码不能为空");
        }
    }
}
