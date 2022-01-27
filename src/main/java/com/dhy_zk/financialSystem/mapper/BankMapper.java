package com.dhy_zk.financialSystem.mapper;

import com.dhy_zk.financialSystem.domain.BDvo;
import com.dhy_zk.financialSystem.domain.Bank;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 大忽悠
 * @since 2022年01月19日
 */
public interface BankMapper extends BaseMapper<Bank> {
    @Select("SELECT *,b.id bid,b.bankName,b.computerBalance,b.infoBalance,b.num,b.reduceBalance FROM deal  d\n" +
            "INNER JOIN  bankWithDeal bwd\n" +
            "ON  d.id=bwd.d_id\n" +
            "INNER JOIN bank b\n" +
            "ON b.id=bwd.b_id\n"+
            "WHERE b.id=#{id}")
    List<BDvo> list(@Param("id") Integer id);
}
