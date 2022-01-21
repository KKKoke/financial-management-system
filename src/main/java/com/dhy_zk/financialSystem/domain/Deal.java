package com.dhy_zk.financialSystem.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>
 * 
 * </p>
 *
 * @author 大忽悠
 * @since 2022年01月19日
 */
@Data
public class Deal implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 注明
     */
    private String remark;

    /**
     * 到账时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date paytime;

    /**
     * 业务相关单位
     */
    private String company;

    /**
     * 汇款单位经办人
     */
    private String handler;

    /**
     * 收款单位
     */
    private String payee;

    /**
     * 用项
     */
    private String item;

    /**
     * 品名
     */
    private String detail;

    /**
     * 收支
     */

    private Integer payType;

    /**
     * 金额
     */
    private BigDecimal money;

    /**
     * 收货人
     */
    private String receivePeo;

    /**
     * 经办人
     */
    private String byPeo;

    /**
     * 单价
     */
    private BigDecimal unitPrice;

    /**
     * 数量
     */
    private Integer number;

    /**
     * 单位
     */
    private String unit;

    /**
     * 应收金额
     */
    private BigDecimal expectMoney;

    /**
     * 实收金额
     */
    private BigDecimal realMoney;

    /**
     * 余款
     */
    private BigDecimal leftMoney;

    /**
     * 交易备注
     */
    private String infoMark;

    /**
     * 收款人
     */
    private String receiver;

}
