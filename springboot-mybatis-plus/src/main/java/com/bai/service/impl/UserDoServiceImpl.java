package com.bai.service.impl;

import com.bai.dao.IUserDao;
import com.bai.entity.User;
import com.bai.entity.query.UserQueryBean;
import com.bai.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserDoServiceImpl extends ServiceImpl<IUserDao, User> implements IUserService {
    @Override
    @Transactional
    public List<User> findList(UserQueryBean userQueryBean) {
        return baseMapper.findList(userQueryBean);
    }

    @Override
    public int addUser(User user) {
        return baseMapper.addUser(user);
    }

    @Override
    public List<User> getAllUser() {
        return baseMapper.getAllUser();
    }

    @Override
    public User getUserById(long id) {
        return baseMapper.getUserById(id);
    }

}
