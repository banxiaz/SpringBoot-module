package com.bai.service;

import com.bai.entity.Role;
import com.bai.entity.query.RoleQueryBean;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface IRoleService extends IService<Role> {
    List<Role> findList(RoleQueryBean roleQueryBean);
}
