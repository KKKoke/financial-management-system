package com.dhy_zk.financialSystem.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author 大忽悠
 * @since 2022年01月19日
 */
@Data
@Builder
@TableName("bankWithDeal")
public class BankWithDeal implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 银行卡id
     */
    private Integer b_id;

    /**
     * 交易id
     */
    private Integer d_id;


}
