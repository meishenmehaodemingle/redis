package com.qf.dao;

import com.qf.base.BaseMapper;
import com.qf.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    List<User> selectData();


    void addUser(User user);
}
