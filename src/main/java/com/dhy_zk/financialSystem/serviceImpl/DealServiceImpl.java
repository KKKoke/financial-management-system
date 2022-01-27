package com.dhy_zk.financialSystem.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dhy_zk.financialSystem.domain.BDvo;
import com.dhy_zk.financialSystem.domain.CompanyDebtVo;
import com.dhy_zk.financialSystem.domain.Deal;
import com.dhy_zk.financialSystem.mapper.DealMapper;
import com.dhy_zk.financialSystem.service.IDealService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sun.scenario.effect.impl.sw.java.JSWBlend_SRC_OUTPeer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.Collector;
import java.util.stream.Collectors;

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

    @Override
    public List<CompanyDebtVo> getAllCompanyDebts() {
        Map<String, List<Deal>> map = list().stream().collect(Collectors.groupingBy(deal -> deal.getCompany()));
        List<CompanyDebtVo> res=new ArrayList<>();
        int i=1;
        for (Map.Entry<String, List<Deal>> entry : map.entrySet()) {
            BigDecimal reduce = entry.getValue().stream().map(x -> x.getLeftMoney()==null?new BigDecimal(0):x.getLeftMoney()).reduce(new BigDecimal(0), BigDecimal::add);
            CompanyDebtVo.builder().id(i++).companyName(entry.getKey()).debt(reduce);
        }
        return res;
    }

}
