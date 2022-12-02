package org.poison.starter.web.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.SneakyThrows;

import org.springframework.stereotype.Component;

import java.util.List;

import javax.annotation.Resource;


/**
 * 克隆List，static方法不能带泛型
 */
@Component
public class CloneUtils<T> {

    @Resource
    private ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 克隆列表
     */
    @SneakyThrows
    public List<T> cloneList(List<T> oList) {
        return objectMapper.readValue(objectMapper.writeValueAsString(oList), new TypeReference<List<T>>() {
        });

    }

}
