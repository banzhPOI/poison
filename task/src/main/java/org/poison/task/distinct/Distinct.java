package org.poison.task.distinct;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import org.poison.merge.BaseTask;
import org.poison.merge.Merge;
import org.redisson.api.RList;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@Slf4j
@Component
public class Distinct extends Merge {

    @Override
    protected int getWindowNum() {
        return 100;
    }

    @Override
    protected String getTaskName() {
        return "DEFAULT_TASK";
    }
}
