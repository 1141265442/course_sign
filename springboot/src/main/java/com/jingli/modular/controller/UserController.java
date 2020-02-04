package com.jingli.modular.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jingli.core.msg.ErrorTip;
import com.jingli.core.msg.SuccessTip;
import com.jingli.core.msg.Tip;
import com.jingli.modular.entity.User;
import com.jingli.modular.entity.vo.UserVO;
import com.jingli.modular.mapper.UserMapper;
import com.jingli.modular.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * <p>
 * 管理员表 前端控制器
 * </p>
 *
 * @author jingli
 * @since 2020-01-31
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
   UserMapper userMapper;

    @RequestMapping("/modify")
    public Object modify(User user){
//        User user1 = userMapper.selectById(user.getId());

        User oldUser = getUserByName(user.getUsername());
        if(oldUser==null||oldUser.getId()==user.getId()){
            userMapper.updateById(user);
            return new SuccessTip(user.selectById());
        }else{
            return new ErrorTip("用户名已存在!");
        }
    }

    @RequestMapping("/updateById")
    public Object updateById(User user){
        userMapper.updateUser(user.getUsername(),user.getName(),
                user.getPhone(),user.getRoleType(),user.getId());
       return new SuccessTip("用户信息修改成功");
    }

    @RequestMapping("/selectUserById")
    public Object selectUserById(String id){
        UserVO userVO=new UserVO(userMapper.selectById(id),
                null, userMapper.selectDeptByUserId(id));
        return new SuccessTip(userVO);
    }

    @RequestMapping("/delUserById")
    public Object delUser(String id){
        userMapper.delUser(id);
        return  new SuccessTip("用户删除成功");
    }

    @RequestMapping("/selectAll")
    public Object selectAll(String name){

        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.ne("role_type",0);
        if(name!=null){
            if(name.trim().equals(""))
                return new SuccessTip(userMapper.selectList(queryWrapper));
            queryWrapper.eq("name",name);
            return new SuccessTip(userMapper.selectList(queryWrapper));
        }
        return new SuccessTip(userMapper.selectList(queryWrapper));
    }

    public User getUserByName(String username){
        //构造查询条件
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.eq("username",username);
        //查询数据库表
        return userMapper.selectOne(queryWrapper);
    }

}
