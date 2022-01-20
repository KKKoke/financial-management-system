package com.dhy_zk.financialSystem.service;

import com.dhy_zk.financialSystem.domain.BDvo;
import com.dhy_zk.financialSystem.domain.Deal;
import com.baomidou.mybatisplus.extension.service.IService;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 大忽悠
 * @since 2022年01月19日
 */
public interface IDealService extends IService<Deal> {
    List<BDvo> list(Deal deal);

    BigDecimal computeDebtByCompanyName(String companyName);

    List<BDvo> listBD();

}
