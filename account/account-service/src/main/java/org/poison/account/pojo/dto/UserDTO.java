package org.poison.account.pojo.dto;

import lombok.Data;
import org.poison.account.core.enums.UserStatus;
import org.poison.account.core.resp.UserVO;
import org.poison.account.pojo.entity.User;

import java.time.Instant;

@Data
public class UserDTO {

    private String id;

    private String username;

    private String email;

    private String mobile;

    private UserStatus status;

    private Instant createTime;

    private Instant updateTime;

    public static UserDTO fromEntity(User user) {
        if (user == null) {
            return null;
        }
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setMobile(user.getMobile());
        dto.setStatus(user.getStatus());
        dto.setCreateTime(user.getCreateTime());
        dto.setUpdateTime(user.getUpdateTime());
        return dto;
    }

    public static UserVO toVO(UserDTO dto) {
        if (dto == null) {
            return null;
        }
        UserVO vo = new UserVO();
        vo.setId(dto.getId());
        vo.setUsername(dto.getUsername());
        return vo;
    }
}
