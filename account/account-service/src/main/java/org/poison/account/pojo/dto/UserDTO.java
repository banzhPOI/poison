package org.poison.account.pojo.dto;

import lombok.Data;
import org.poison.account.core.enums.UserStatus;
import org.poison.account.core.resp.UserVO;
import org.poison.account.pojo.entity.User;
import org.springframework.util.CollectionUtils;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class UserDTO {

    private String id;

    private String nickname;

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
        dto.setNickname(user.getNickname());
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
        vo.setNickname(dto.getNickname());
        return vo;
    }

    public static List<UserVO> toVO(List<UserDTO> dtoList) {
        List<UserVO> resp = new ArrayList<>();
        if (CollectionUtils.isEmpty(dtoList)) {
            return resp;
        }
        return dtoList.stream().map(UserDTO::toVO).collect(Collectors.toList());
    }

    public static List<UserDTO> fromEntity(List<User> entityList) {
        List<UserDTO> resp = new ArrayList<>();
        if (CollectionUtils.isEmpty(entityList)) {
            return resp;
        }
        return entityList.stream().map(UserDTO::fromEntity).collect(Collectors.toList());
    }
}
