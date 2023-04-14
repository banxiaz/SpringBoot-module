package com.bai.dao;

import com.bai.entity.User;
import com.bai.entity.query.UserQueryBean;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

// 它的实现在 src/main/resources/mapper/UserMapper.xml
public interface IUserDao extends BaseMapper<User> {
    List<User> findList(UserQueryBean userQueryBean);

    int addUser(User user);

    List<User> getAllUser();

    User getUserById(long id);

}
