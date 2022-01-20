package com.dhy_zk.financialSystem.service;

import com.dhy_zk.financialSystem.domain.BDvo;
import com.dhy_zk.financialSystem.domain.Bank;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 大忽悠
 * @since 2022年01月19日
 */
public interface IBankService extends IService<Bank> {

    List<BDvo> list(String num);
}
