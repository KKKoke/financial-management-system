package com.dhy_zk.financialSystem.serviceImpl;

import com.dhy_zk.financialSystem.domain.BankWithDeal;
import com.dhy_zk.financialSystem.mapper.BankWithDealMapper;
import com.dhy_zk.financialSystem.service.IBankWithDealService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 大忽悠
 * @since 2022年01月19日
 */
@Transactional
@Service
public class BankWithDealServiceImpl extends ServiceImpl<BankWithDealMapper, BankWithDeal> implements IBankWithDealService {

}
