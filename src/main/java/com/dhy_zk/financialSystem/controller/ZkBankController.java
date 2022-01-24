package com.dhy_zk.financialSystem.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dhy_zk.financialSystem.domain.Bank;
import com.dhy_zk.financialSystem.msg.AjaxResponse;
import com.dhy_zk.financialSystem.service.IBankService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author 大忽悠
 * @create 2022/1/20 17:39
 */
@Api(tags = "银行卡")
@RestController
public class ZkBankController
{
    @Resource
    private IBankService iBankService;

    /**
     * <p>
     *     返回所有银行卡
     * </p>
     * @return
     */
    @ApiOperation("返回所有银行卡")
    @GetMapping("/banks")
    public AjaxResponse getAllBanks()
    {
        return AjaxResponse.success(iBankService.list());
    }

    @ApiOperation("返回当前银行名称下的所有银行")
    @GetMapping("/banks")
    public AjaxResponse getBanksUnderNowBankName(@RequestParam String bankName)
    {
        return AjaxResponse.success(iBankService.list(new QueryWrapper<Bank>().eq("bankName",bankName)));
    }


    /**
     * <p>
     *     所有银行卡的汇总金额
     * </p>
     * @return
     */
    @ApiOperation("所有银行卡的汇总金额")
    @GetMapping("/totalMoney")
    public AjaxResponse getTotalNum()
    {
        BigDecimal bigDecimal = iBankService.list().stream().map(x -> x.getComputerBalance()).reduce(new BigDecimal(0), BigDecimal::add);
        return AjaxResponse.success(bigDecimal);
    }

    /**
     * <P>
     *     列举所有大类银行，汇总每个大类银行的总金额
     * </P>
     * @return
     */
    @ApiOperation("列举所有大类银行，汇总每个大类银行的总金额")
    @GetMapping("/listBankOfTotalMoney")
    public AjaxResponse listAllBigBanks()
    {
        List<BigDecimal> bankOfMoney=new ArrayList<>();
        Map<String, List<Bank>> map = iBankService.list().stream().collect(Collectors.groupingBy(Bank::getBankName));
        map.keySet().stream().forEach(bank->{
            BigDecimal bigDemical = map.get(bank).stream().map(x -> x.getComputerBalance()).reduce(new BigDecimal(0), BigDecimal::add);
            bankOfMoney.add(bigDemical);
        });
        return AjaxResponse.success(bankOfMoney);
    }

    /**
     * <P>
     *     增加一个银行卡
     * </P>
     * @param bank
     * @return
     */
    @ApiOperation("增加一个银行卡")
    @PostMapping("/banks")
    public AjaxResponse addOneBankCard(Bank bank)
    {
        boolean save=false;
        if(!bank.getBankName().equals("现金"))
        {
            //银行卡不能重复
            String num = bank.getNum();
            Bank errorBank = iBankService.getOne(new QueryWrapper<Bank>().eq("bankName",bank.getBankName()));
            Assert.isNull(errorBank,"银行卡不能重复");
            bank.setReduceBalance(bank.getComputerBalance().subtract(bank.getInfoBalance()));
        }
        synchronized (this)
        {
            save= iBankService.save(bank);
        }
        return AjaxResponse.success(save);
    }

    /**
     * 查询当前银行卡上的所有消费记录
     * @return
     */
    @ApiOperation("查询当前银行卡上的所有消费记录")
    @GetMapping("/infos")
    public AjaxResponse queryExtraInfo(@RequestParam String num)
    {
        return AjaxResponse.success(iBankService.list(num));
    }
}
