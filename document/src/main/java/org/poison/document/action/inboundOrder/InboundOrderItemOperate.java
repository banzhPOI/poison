package org.poison.document.action.inboundOrder;

import lombok.Data;
import org.poison.document.action.base.BaseOperate;

import java.math.BigDecimal;

@Data
public class InboundOrderItemOperate extends BaseOperate {

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
