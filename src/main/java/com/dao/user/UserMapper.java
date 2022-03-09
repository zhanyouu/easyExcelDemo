package com.dao.user;

import com.domain.entity.user.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper{
    User selectByPrimaryKey(@Param("id") Integer id);
}