package com.dhy_zk.financialSystem.mapper;

import com.dhy_zk.financialSystem.domain.BDvo;
import com.dhy_zk.financialSystem.domain.Deal;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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
public interface DealMapper extends BaseMapper<Deal> {
    @Select("<script>" +
            "SELECT *,b.id bid,b.bankName,b.computerBalance,b.infoBalance,b.num,b.reduceBalance FROM deal  d\n" +
            "INNER JOIN  bankWithDeal bwd\n" +
            "ON  d.id=bwd.d_id\n" +
            "INNER JOIN bank b\n" +
            "ON b.id=bwd.b_id"+
            "<where>"+
            "1=1"+
            "            <if test='remark != null'>" +
            "           AND remark = #{remark}" +
            "            </if>" +
            "            <if test='paytime != null'>" +
            "            AND paytime = #{paytime}" +
            "            </if>" +
            "            <if test='company != null'>" +
            "            AND company = #{company}" +
            "            </if>"+
            "            <if test='handler != null'>" +
            "            AND handler = #{handler}" +
            "            </if>"+
            "            <if test='payee != null'>" +
            "            AND payee = #{payee}" +
            "            </if>"+
            "            <if test='item != null'>" +
            "            AND item = #{item}" +
            "            </if>"+
            "            <if test='detail != null'>" +
            "            AND detail = #{detail}" +
            "            </if>"+
            "            <if test='payType != null'>" +
            "            AND payType = #{payType}" +
            "            </if>"+
            "            <if test='money != null'>" +
            "            AND money = #{money}" +
            "            </if>"+
            "            <if test='receivePeo != null'>" +
            "            AND receivePeo = #{receivePeo}" +
            "            </if>"+
            "            <if test='unitPrice != null'>" +
            "            AND unitPrice = #{unitPrice}" +
            "            </if>"+
            "            <if test='number != null'>" +
            "            AND number = #{number}" +
            "            </if>"+
            "            <if test='unit != null'>" +
            "            AND unit = #{unit}" +
            "            </if>"+
            "            <if test='expectMoney != null'>" +
            "            AND expectMoney = #{expectMoney}" +
            "            </if>"+
            "            <if test='realMoney != null'>" +
            "            AND realMoney = #{realMoney}" +
            "            </if>"+
            "            <if test='leftMoney != null'>" +
            "            AND leftMoney = #{leftMoney}" +
            "            </if>"+
            "            <if test='infoMark != null'>" +
            "            AND infoMark = #{infoMark}" +
            "            </if>"+
            "            <if test='receiver != null'>" +
            "            AND receiver = #{receiver}" +
            "            </if>"+
            "            <if test='id != null'>" +
            "            AND d.id = #{id}" +
            "            </if>"+
            " </where>"+
            "</script>")
    List<BDvo> list(Deal deal);

    /**
     * <p>
     *     三表关联查询
     * </p>
     * @return
     */
    @Select("SELECT *,b.id bid,b.bankName,b.computerBalance,b.infoBalance,b.num,b.reduceBalance FROM deal  d\n" +
            "INNER JOIN  bankWithDeal bwd\n" +
            "ON  d.id=bwd.d_id\n" +
            "INNER JOIN bank b\n" +
            "ON b.id=bwd.b_id;")
    List<BDvo> listBD();


}
