package com.bai.service.impl;

import java.util.List;

import com.bai.dao.IUserDao;
import com.bai.entity.User;
import com.bai.entity.query.UserQueryBean;
import com.bai.service.IUserService;
import org.springframework.stereotype.Service;

@Service
public class UserDoServiceImpl implements IUserService {

    /**
     * userDao.
     */
    private final IUserDao userDao;

    /**
     * init.
     *
     * @param userDao2 user dao
     */
    public UserDoServiceImpl(final IUserDao userDao2) {
        this.userDao = userDao2;
    }

    @Override
    public List<User> findList(UserQueryBean userQueryBean) {
        return userDao.findList(userQueryBean);
    }

    @Override
    public User findById(Long id) {
        return userDao.findById(id);
    }

    @Override
    public int deleteById(Long id) {
        return userDao.deleteById(id);
    }

    @Override
    public int deleteByIds(Long[] ids) {
        return userDao.deleteByIds(ids);
    }

    @Override
    public int update(User user) {
        return userDao.update(user);
    }

    @Override
    public int save(User user) {
        return userDao.save(user);
    }

    @Override
    public int updatePassword(User user) {
        return userDao.updatePassword(user);
    }
}
