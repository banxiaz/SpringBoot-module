package com.bai.service;

import com.bai.entity.User;
import com.bai.entity.query.UserQueryBean;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface IUserService extends IService<User> {
    List<User> findList(UserQueryBean userQueryBean);

    int addUser(User user);

    List<User> getAllUser();

    User getUserById(long id);
}
