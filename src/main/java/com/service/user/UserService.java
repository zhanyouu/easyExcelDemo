package com.service.user;

import com.dao.user.UserMapper;
import com.domain.entity.user.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserService {
    private final UserMapper userMapper;
    public User findById(Integer id) {
        // select * from user where id = #{id}
        return this.userMapper.selectByPrimaryKey(id);
    }
    public void insertUser(User user){
        this.userMapper.insert(user);
    }
}
