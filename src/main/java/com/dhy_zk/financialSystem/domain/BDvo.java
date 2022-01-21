package com.dhy_zk.financialSystem.domain;


import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author 大忽悠
 * @create 2022/1/20 18:10
 */
@Data
public class BDvo
{
    /**
     * 银行id
     */
    private Integer bid;

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


  //////////////////////////////////////////////


    /**
     * 交易id
     */
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
