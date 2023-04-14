package com.bai.service;

import com.bai.entity.User;
import com.bai.entity.query.UserQueryBean;

import java.util.List;

public interface IUserService {
    List<User> findList(UserQueryBean userQueryBean);

    User findById(Long id);

    int deleteById(Long id);

    int deleteByIds(Long[] ids);

    int update(User user);

    int save(User user);

    int updatePassword(User user);

}
