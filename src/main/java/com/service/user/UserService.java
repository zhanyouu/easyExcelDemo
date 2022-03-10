package com.service.user;

import com.dao.user.UserMapper;
import com.domain.entity.user.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserService {
    private final UserMapper userMapper;
    public User findById(Integer id) {
        return this.userMapper.selectByPrimaryKey(id);
    }
    public List<User> findUsersByIds(List<Integer> ids){
        return this.userMapper.selectUserListByIds(ids);
    }
}
