package org.poison.async.thread;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class Box {

    private Integer stock = 0;

    private Integer count = 0;

    public void add() {
        stock++;
        count++;
        log.info("add {}", count);
    }

    public void get() {
        stock--;
        log.info("get {}", count);
    }
}
