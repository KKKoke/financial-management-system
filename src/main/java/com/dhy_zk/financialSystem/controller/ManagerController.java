package com.dhy_zk.financialSystem.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dhy_zk.financialSystem.domain.Manager;
import com.dhy_zk.financialSystem.msg.AjaxResponse;
import com.dhy_zk.financialSystem.msg.CustomExceptionType;
import com.dhy_zk.financialSystem.service.IManagerService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;


/**
 * @author 大忽悠
 * @create 2022/1/20 11:12
 */
@RestController
public class ManagerController
{
    @Resource
    private IManagerService managerService;

    @Resource
    private BCryptPasswordEncoder passwordEncoder;;


    @GetMapping("/manager")
    public AjaxResponse getAllManagers()
    {
        return AjaxResponse.success(managerService.list());
    }

    /**
     * <p>
     *     超级管理员才可以执行删除操作
     * </p>
     * @param name
     * @return
     */
    @PreAuthorize("hasRole('SUPER')")
    @DeleteMapping("/manager")
    public AjaxResponse delManager(@RequestParam String name)
    {
        boolean remove=false;
        Manager manager = managerService.getOne(new QueryWrapper<Manager>().eq("mname", name));
        //超级管理员不可删除
        if(manager!=null&&manager.getType()!=1)
        {
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("mname",name);
            synchronized (this)
            {
                remove = managerService.removeByMap(hashMap);
            }
        }
        return remove?AjaxResponse.success():AjaxResponse.error(CustomExceptionType.SYSTEM_ERROR,"删除失败或检查删除的是否是超级管理员");
    }

    @GetMapping("/managerByExample")
    public AjaxResponse getManagersByExample(@RequestParam String name)
    {
        Manager manager = managerService.getOne(new QueryWrapper<Manager>().eq("mname", name));
        return AjaxResponse.success(manager);
    }

    @PreAuthorize("hasRole('SUPER')")
    @PostMapping("/addOne")
    public AjaxResponse addOneManager(@Validated Manager manager)
    {
        //管理员姓名不能重复
        Manager mname = managerService.getOne(new QueryWrapper<Manager>().eq("mname", manager.getMname()));
        Assert.isNull(mname,"管理员姓名重复");
        //对密码进行加密操作
        String encode = passwordEncoder.encode(manager.getPassword());
        manager.setMpwd(encode);
        boolean b=false;
        synchronized (this)
        {
          b= managerService.save(manager);
        }
        return AjaxResponse.success(b);
    }
}
