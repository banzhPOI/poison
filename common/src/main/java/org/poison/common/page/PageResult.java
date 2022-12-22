package org.poison.common.page;

import lombok.Data;

import java.util.List;

@Data
public class PageResult<T> {

    private List<T> content;

    private Integer total;

    public PageResult(List<T> content, Integer total) {
        this.content = content;
        this.total = total;
    }
}
