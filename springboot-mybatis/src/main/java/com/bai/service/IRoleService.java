package com.bai.service;

import com.bai.entity.Role;
import com.bai.entity.query.RoleQueryBean;

import java.util.List;

public interface IRoleService {
    List<Role> findList(RoleQueryBean roleQueryBean);
}
