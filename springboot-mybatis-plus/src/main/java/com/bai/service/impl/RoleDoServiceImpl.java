package com.bai.service.impl;

import com.bai.dao.IRoleDao;
import com.bai.entity.Role;
import com.bai.entity.query.RoleQueryBean;
import com.bai.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoleDoServiceImpl extends ServiceImpl<IRoleDao, Role> implements IRoleService {
    @Override
    @Transactional
    public List<Role> findList(RoleQueryBean roleQueryBean) {
        return lambdaQuery().like(StringUtils.isNotEmpty(roleQueryBean.getName()), Role::getName, roleQueryBean.getName())
                .like(StringUtils.isNotEmpty(roleQueryBean.getDescription()), Role::getDescription, roleQueryBean.getDescription())
                .like(StringUtils.isNotEmpty(roleQueryBean.getRoleKey()), Role::getRoleKey, roleQueryBean.getRoleKey())
                .list();
    }
}
