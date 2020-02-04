package com.jingli.modular.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jingli.core.msg.ErrorTip;
import com.jingli.core.msg.RoadYaoException;
import com.jingli.core.msg.SuccessTip;
import com.jingli.modular.entity.User;
import com.jingli.modular.entity.UserDept;
import com.jingli.modular.mapper.UserDeptMapper;
import com.jingli.modular.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.HashMap;
import java.util.Map;

import static com.jingli.core.msg.exception.ExceptionEnum.TIME_OUT;

@RestController
public class LoginController {

    @Autowired
    UserMapper userMapper;

    @Autowired
    UserDeptMapper userDeptMapper;

    @RequestMapping("register")
    public Object register(User user,String deptId){
        if(user.getUsername()==null||user.getUsername().trim().equals("")){
            return new ErrorTip("用户名不能为空！");
        }
        User oldUser=getUserByName(user.getUsername());
        if(oldUser!=null){
            return new ErrorTip("用户已存在！");
        }

        if(userMapper.insert(user)==1){
            if(insertUserDept(user,deptId)==false){
                userMapper.deleteById(user.getId());
                return new ErrorTip("所选班级已存在教师！");
            }
            else{
                return new SuccessTip("注册成功");
            }
        }
        else{
            return new ErrorTip("注册失败");
        }
    }
    public boolean insertUserDept(User user,String deptId){
        UserDept userDept=new UserDept();
        //如果注册用户是教师，到数据库查找注册的班级是否有教师
        //若有教师，返回false
        if(user.getRoleType()==1){
            QueryWrapper<UserDept> queryWrapper = new QueryWrapper<UserDept>();
            Map map=new HashMap<String,Object>();
            map.put("deptId",deptId);
            map.put("user_type",1);
            queryWrapper.allEq(map);
            UserDept oldTeacher = userDept.selectOne(queryWrapper);
            if(oldTeacher!=null) return false;
        }
        //将信息写入数据库
        userDept.setDeptId(Integer.parseInt(deptId));
        userDept.setUserType(user.getRoleType());
        userDept.setUserId(user.getId());
        return userDept.insert();
    }

    @RequestMapping("login")
    public Object login(String username,String password){

        User user=getUserByName(username);

        //执行判断登录逻辑
        if(user==null){
            return new ErrorTip(501,"用户不存在");
        }
        else if(user.getPassword().equals(password)){
            return new SuccessTip(user);
        }else
            return new ErrorTip(502,"密码错误");
    }

    public User getUserByName(String username){
        //构造查询条件
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.eq("username",username);
        //查询数据库表
        return userMapper.selectOne(queryWrapper);
    }


}
