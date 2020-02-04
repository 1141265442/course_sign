package com.jingli.modular.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jingli.modular.entity.User;
import com.jingli.modular.mapper.UserMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 管理员表 服务实现类
 * </p>
 *
 * @author jingli
 * @since 2020-01-31
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IService<User> {
    @Autowired
    UserMapper userMapper;

    public User getUserByName(String username){
        //构造查询条件
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.eq("username",username);
        //查询数据库表
        return userMapper.selectOne(queryWrapper);
    }
}
