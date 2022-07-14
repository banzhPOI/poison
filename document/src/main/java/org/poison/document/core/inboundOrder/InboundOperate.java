package org.poison.document.core.inboundOrder;

import org.poison.document.core.base.BaseOperate;

import java.math.BigDecimal;

public class InboundOperate extends BaseOperate {

    /**
     * 仓库商品id
     */
    String warehouseInventoryId;

    /**
     * 货位商品id
     */
    String shelfInventoryId;

    /**
     * 操作入库数量
     */
    Long quantity;

    /**
     * 操作入库价格
     */
    BigDecimal price;
}
