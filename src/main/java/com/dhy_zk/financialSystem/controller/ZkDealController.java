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
        bank.setId(bdvo.getBid());

        //通过银行卡或者现金名字加上对应的id确认唯一的记录
        bank= bankService.getOne(new QueryWrapper<Bank>().eq("bankName",bank.getBankName()).eq("id",bank.getId()));
        Assert.notNull(bank,"银行卡或指定现金卡不存在");

        //如果是银行卡
        if(!bank.getBankName().equals("现金"))
        {
            Assert.notNull(bdvo.getInfoBalance(),"信息余额不能为空");
            //如果输入的信息余额和保存的电脑余额不一致，就更新电脑余额
            if(bank.getComputerBalance().compareTo(bdvo.getInfoBalance())!=0)
            {
                bank.setComputerBalance(bdvo.getInfoBalance());
            }
        }

        updateBankState(bank,bdvo.getPayType(),deal,bdvo);

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
     *     增加一条交易的时候，更新对应的银行卡或者现金卡状态
     * </p>
     * @param bank
     */
    private void updateBankState(Bank bank,int payType,Deal deal,BDvo bdvo)
    {
        BigDecimal sign=null;
        //收入
        if(payType==1)
        {
          sign=new BigDecimal(1);
        }
        //支出
        else if(payType==0)
        {
            sign=new BigDecimal(-1);
        }
        else
        {
            throw  new IllegalArgumentException("参数异常");
        }

        //更新电脑余额
        bank.setComputerBalance(bank.getComputerBalance().add(deal.getMoney().multiply(sign)));
        //判断钱还够不够
        int res = bank.getComputerBalance().compareTo(new BigDecimal(0));
        Assert.isTrue(res>=0,"余额不足");

        if(!bank.getBankName().equals("现金"))
        {
            //对信息金额进行更新
            bank.setInfoBalance(bdvo.getInfoBalance().add(deal.getMoney().multiply(sign)));
            res = bank.getInfoBalance().compareTo(new BigDecimal(0));
            Assert.isTrue(res>=0,"余额不足");
            //计算余额差
            BigDecimal subtract = bank.getComputerBalance().subtract(bank.getInfoBalance());
            bank.setReduceBalance(subtract.abs());
        }
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
