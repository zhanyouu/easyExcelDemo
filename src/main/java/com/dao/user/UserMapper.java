package com.dao.user;

import com.domain.entity.user.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {
    User selectByPrimaryKey(@Param("id") Integer id);

    List<User> selectUserListByIds(@Param("ids") List<Integer> ids);
}

