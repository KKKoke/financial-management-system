package com.dhy_zk.financialSystem.domain;

import io.swagger.models.auth.In;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;

/**
 * @author 大忽悠
 * @create 2022/1/27 14:48
 */
@Data
@Builder
public class CompanyDebtVo {
    private Integer id;
    private BigDecimal debt;
    private String companyName;
}
