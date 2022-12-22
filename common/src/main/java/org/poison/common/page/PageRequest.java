package org.poison.common.page;

import lombok.Data;
import org.springframework.data.domain.Sort;

@Data
public class PageRequest {


    private Integer page;
    private Integer size;
    private String sort;
    private Sort.Direction order;

    public Integer getPage() {
        if (page == null || page < 0) {
            return 0;
        }

        return page;
    }

    public Integer getSize() {
        if (size == null || size < 0 || size > 1000) {
            return 10;
        }

        return size;
    }

    public Integer getOffset() {
        return getPage() * getSize();
    }

    public Integer getLimit() {
        return getSize();
    }
}
