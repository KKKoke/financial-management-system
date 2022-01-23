package com.dhy_zk.financialSystem.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dhy_zk.financialSystem.domain.BDvo;
import com.dhy_zk.financialSystem.domain.Bank;
import com.dhy_zk.financialSystem.domain.BankWithDeal;
import com.dhy_zk.financialSystem.domain.Deal;
import com.dhy_zk.financialSystem.msg.AjaxResponse;
import com.dhy_zk.financialSystem.service.IBankService;
import com.dhy_zk.financialSystem.service.IBankWithDealService;
import com.dhy_zk.financialSystem.service.IDealService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;

/**
 * @author 大忽悠
 * @create 2022/1/20 17:33
 */
@RestController
@Slf4j
public class ZkDealController
{
    @Resource
    private IDealService iDealService;
   @Resource
   private IBankService bankService;
   @Resource
   private IBankWithDealService bankWithDealService;
    /**
     * <p>
     *     返回全部的记录信息
     * </p>
     * @return
     */
    @GetMapping("/deals")
    public AjaxResponse getAllDeals()
    {
        return AjaxResponse.success(iDealService.listBD());
    }

    /**
     * <p>
     *     增加一个订单
     *     订单表增加一个记录
     *     对应银行卡余额更新
     *     映射表增加一条映射关系
     * </p>
     * @param bdvo
     * @return
     */
    @PostMapping("/deals")
    @Transactional
    public synchronized AjaxResponse addOneDeal(BDvo bdvo)
    {
        Deal deal = new Deal();
        BeanUtils.copyProperties(bdvo,deal);

        //银行卡余额更新
        Bank bank = new Bank();
        BeanUtils.copyProperties(bdvo,bank);
        //通过银行卡加卡号确认
        bank= bankService.getOne(new QueryWrapper<Bank>().eq("bankName",bank.getBankName()).eq("num",bank.getNum()));
        Assert.notNull(bank,"银行卡不存在");
        bank.setComputerBalance(bank.getComputerBalance().subtract(deal.getMoney()));
        int res = bank.getComputerBalance().compareTo(new BigDecimal(0));
        Assert.isTrue(res>=0,"余额不足");
        //信息余额也需要减
        bank.setInfoBalance(bank.getInfoBalance().subtract(deal.getMoney()));
        res = bank.getComputerBalance().compareTo(new BigDecimal(0));
        Assert.isTrue(res>=0,"余额不足");
        //计算余额差
        BigDecimal subtract = bank.getComputerBalance().subtract(bank.getInfoBalance());
        bank.setReduceBalance(subtract.abs());
        bankService.updateById(bank);
        //计算余款--前提是两个值都存在
        if(deal.getExpectMoney()!=null&&deal.getRealMoney()!=null)
        {
            deal.setLeftMoney(deal.getRealMoney().subtract(deal.getExpectMoney()));
        }
        //增加一个交易
        iDealService.save(deal);

        //增加映射
        BankWithDeal bankWithDeal = BankWithDeal.builder().b_id(bank.getId()).d_id(deal.getId()).build();
        bankWithDealService.save(bankWithDeal);
        return AjaxResponse.success();
    }

    /**
     * <p>
     *     删除一个订单
     * </p>
     * @param id
     * @return
     */
    @PreAuthorize("hasRole('SUPER')")
    @DeleteMapping("/deals")
    @Transactional
    public synchronized AjaxResponse delOneDeal(@RequestParam Integer id)
    {
        boolean removeById;
         synchronized (this)
         {
             //删除订单
             removeById  = iDealService.removeById(id);
            //删除映射关系
             HashMap<String, Object> hashMap = new HashMap<>();
             hashMap.put("d_id",id);
             bankWithDealService.removeByMap(hashMap);
         }
        return AjaxResponse.success(removeById);
    }

}
