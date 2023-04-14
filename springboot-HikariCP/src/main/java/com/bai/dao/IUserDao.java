package com.bai.dao;

import org.springframework.stereotype.Repository;
import com.bai.entity.User;

/**
 * @author pdai
 */
@Repository
public interface IUserDao extends IBaseDao<User, Long> {

}
