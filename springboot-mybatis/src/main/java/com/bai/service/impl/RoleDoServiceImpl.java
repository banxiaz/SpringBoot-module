package com.bai.service.impl;

import com.bai.dao.IRoleDao;
import com.bai.entity.Role;
import com.bai.entity.query.RoleQueryBean;
import com.bai.service.IRoleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleDoServiceImpl implements IRoleService {
    /**
     * roleDao.
     */
    private final IRoleDao roleDao;

    /**
     * init.
     *
     * @param roleDao2 role dao
     */
    public RoleDoServiceImpl(final IRoleDao roleDao2) {
        this.roleDao = roleDao2;
    }

    @Override
    public List<Role> findList(RoleQueryBean roleQueryBean) {
        return roleDao.findList(roleQueryBean);
    }
}
