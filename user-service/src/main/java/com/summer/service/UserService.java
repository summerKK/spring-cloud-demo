package com.summer.service;

import com.summer.mapper.UserMapper;
import com.summer.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserMapper mapper;

    public User queryById(Long id) {
        return mapper.selectByPrimaryKey(id);
    }
}
