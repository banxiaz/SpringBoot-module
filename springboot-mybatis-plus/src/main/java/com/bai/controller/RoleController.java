package com.bai.controller;

import com.bai.entity.Role;
import com.bai.entity.query.RoleQueryBean;
import com.bai.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private IRoleService roleService;

    @GetMapping("list")
    public List<Role> list(RoleQueryBean roleQueryBean) {
        List<Role> res = roleService.findList(roleQueryBean);
        System.out.println(res);
        return res;
    }
}
