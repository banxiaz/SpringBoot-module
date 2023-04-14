package com.bai.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import com.bai.entity.Role;
import com.bai.entity.query.RoleQueryBean;

public interface IRoleService extends IBaseService<Role, Long> {

    /**
     * find page by query.
     *
     * @param roleQueryBean query
     * @param pageRequest   pageRequest
     * @return page
     */
    Page<Role> findPage(RoleQueryBean roleQueryBean, PageRequest pageRequest);

}
