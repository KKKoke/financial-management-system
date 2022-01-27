package com.dhy_zk.financialSystem.domain;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author 大忽悠
 * @create 2022/1/27 14:17
 */
@Builder
@Data
public class AbstarctBankWithMoneyVo {
    private String bankName;
    private BigDecimal money;
}
