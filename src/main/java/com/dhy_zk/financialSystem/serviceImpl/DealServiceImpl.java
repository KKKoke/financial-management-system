package com.dhy_zk.financialSystem.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dhy_zk.financialSystem.domain.BDvo;
import com.dhy_zk.financialSystem.domain.Deal;
import com.dhy_zk.financialSystem.mapper.DealMapper;
import com.dhy_zk.financialSystem.service.IDealService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

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
public class DealServiceImpl extends ServiceImpl<DealMapper, Deal> implements IDealService {
    @Resource
    DealMapper dealMapper;
    @Override
    public List<BDvo> list(Deal deal) {
        return dealMapper.list(deal);
    }

    @Override
    public BigDecimal computeDebtByCompanyName(String companyName) {
        BigDecimal reduce = dealMapper.selectList(new QueryWrapper<Deal>().eq("company",companyName))
                .stream().map(d -> d.getLeftMoney()).reduce(new BigDecimal(0), BigDecimal::add);
        return  reduce;
    }

    @Override
    public List<BDvo> listBD() {
        return dealMapper.listBD();
    }

}
