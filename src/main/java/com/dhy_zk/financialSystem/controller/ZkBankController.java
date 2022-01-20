package com.dhy_zk.financialSystem.controller;

import com.dhy_zk.financialSystem.domain.Bank;
import com.dhy_zk.financialSystem.msg.AjaxResponse;
import com.dhy_zk.financialSystem.service.IBankService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author 大忽悠
 * @create 2022/1/20 17:39
 */
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
    @GetMapping("/banks")
    public AjaxResponse getAllBanks()
    {
        return AjaxResponse.success(iBankService.list());
    }

    /**
     * <P>
     *     增加一个银行卡
     * </P>
     * @param bank
     * @return
     */
    @PostMapping("/banks")
    public AjaxResponse addOneBankCard(Bank bank)
    {
        boolean save=false;
        //银行卡不能重复
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
    @GetMapping("/infos")
    public AjaxResponse queryExtraInfo(@RequestParam String num)
    {
        return AjaxResponse.success(iBankService.list(num));
    }
}
