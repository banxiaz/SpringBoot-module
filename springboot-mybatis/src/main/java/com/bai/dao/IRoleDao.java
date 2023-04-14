package com.bai.dao;

import com.bai.entity.Role;
import com.bai.entity.query.RoleQueryBean;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IRoleDao {
    List<Role> findList(RoleQueryBean roleQueryBean);

}
