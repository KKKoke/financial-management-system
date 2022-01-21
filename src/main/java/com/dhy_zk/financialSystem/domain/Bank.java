package com.dhy_zk.financialSystem.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.math.BigDecimal;


import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * <p>
 * 
 * </p>
 *
 * @author 大忽悠
 * @since 2022年01月19日
 */
@Data
@NoArgsConstructor
public class Bank implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 银行名字
     */
    private String bankName;

    /**
     * 电脑余额
     */
    private BigDecimal computerBalance;

    /**
     * 信息余额
     */
    private BigDecimal infoBalance;

    /**
     * 卡号后四位
     */
    private String num;

    /**
     * 余差
     */
    private BigDecimal reduceBalance;


}
