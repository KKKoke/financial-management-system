package com.dhy_zk.financialSystem.controller;

import com.dhy_zk.financialSystem.domain.Deal;
import com.dhy_zk.financialSystem.msg.AjaxResponse;
import com.dhy_zk.financialSystem.service.IDealService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;


/**
 * @author 大忽悠
 * @create 2022/1/20 14:43
 */
@RestController
public class DhyDealController
{
    @Resource
    private IDealService iDealService;

    /**
     * <p>
     *     按条件查询
     * </p>
     * @param deal
     * @return
     */
    @GetMapping("/getByExample")
    public AjaxResponse getDealsByExample(Deal deal)
    {
          return AjaxResponse.success(iDealService.list(deal));
    }

    /**
     * <p>
     *     根据公司名称计算债务情况
     * </p>
     * @param companyName
     * @return
     */
    @GetMapping("/getDebtByCompanyName")
   public AjaxResponse getCompanyDebt(@RequestParam String companyName)
   {
      BigDecimal debt=iDealService.computeDebtByCompanyName(companyName);
     return AjaxResponse.success(debt);
   }

    /**
     * <p>
     *     根据公司名称获取详细的债务记录
     * </p>
     * @param companyName
     * @return
     */
   @GetMapping("/getCompanyRecords")
   public AjaxResponse getCompanyRecords(@RequestParam String companyName)
   {
       Deal deal=new Deal();
       deal.setCompany(companyName);
       return AjaxResponse.success( iDealService.list(deal));
   }
}
