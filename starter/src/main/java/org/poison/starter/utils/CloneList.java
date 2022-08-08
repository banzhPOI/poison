package org.poison.starter.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.SneakyThrows;

import java.util.List;

public class CloneList {

    @SneakyThrows
    public static List cloneTaskList(List oList) {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(objectMapper.writeValueAsString(oList), new TypeReference<List>() {
        });
    }
}
