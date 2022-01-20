package com.dhy_zk.financialSystem.serviceImpl;

import com.dhy_zk.financialSystem.domain.BDvo;
import com.dhy_zk.financialSystem.domain.Bank;
import com.dhy_zk.financialSystem.mapper.BankMapper;
import com.dhy_zk.financialSystem.service.IBankService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jdk.nashorn.internal.ir.annotations.Reference;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
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
public class BankServiceImpl extends ServiceImpl<BankMapper, Bank> implements IBankService {

     @Resource
     private BankMapper bankMapper;
    @Override
    public List<BDvo> list(String num) {
        return bankMapper.list(num);
    }
}
