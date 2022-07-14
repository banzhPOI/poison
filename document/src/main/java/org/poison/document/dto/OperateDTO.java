package org.poison.document.dto;

import lombok.Data;
import org.poison.document.core.base.Operator;

@Data
public class OperateDTO extends Operator {

    Long id;

    String userId;
}
