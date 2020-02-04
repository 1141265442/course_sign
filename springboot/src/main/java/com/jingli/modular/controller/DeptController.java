package com.jingli.modular.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jingli.core.msg.ErrorTip;
import com.jingli.core.msg.SuccessTip;
import com.jingli.modular.entity.Dept;
import com.jingli.modular.entity.User;
import com.jingli.modular.entity.UserDept;
import com.jingli.modular.entity.vo.UserVO;
import com.jingli.modular.mapper.DeptMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 部门表 前端控制器
 * </p>
 *
 * @author jingli
 * @since 2020-01-31
 */
@RestController
@RequestMapping("/dept")
public class DeptController {

    @Autowired
    DeptMapper deptMapper;

    @RequestMapping
    public Object selectAllDept(String userId){
        return deptMapper.selectAllDeptByUserId(userId);
    }

    @RequestMapping("/selectDeptById")
    public Object selectDeptById(String id){
        return new SuccessTip(deptMapper.selectById(id));
    }

    @RequestMapping("/add")
    public Object addDept(Dept dept){
        if(dept.getName().equals(getDeptByName(dept.getName()))){
            return new ErrorTip("所添加的班级已存在！");
        }
        return deptMapper.insert(dept);
    }

    public Dept getDeptByName(String name){
        //构造查询条件
        QueryWrapper<Dept> queryWrapper = new QueryWrapper<Dept>();
        queryWrapper.eq("name",name);
        //查询数据库表
        return deptMapper.selectOne(queryWrapper);
    }

    @RequestMapping("/selectTeacher")
    public Object selectUserByDeptId(String id){
        UserDept userDept=new UserDept();
        QueryWrapper<UserDept> queryWrapper = new QueryWrapper<UserDept>();
        Map map=new HashMap<String,Object>();
        map.put("deptId",id);
        map.put("user_type",1);
        queryWrapper.allEq(map);
        UserDept oldTeacher = userDept.selectOne(queryWrapper);
        if(oldTeacher==null) return new ErrorTip("该班级暂无教师！");
        User user=new User();

        UserVO userVO = new UserVO(user.selectById(oldTeacher.getUserId()),
                deptMapper.selectList(null), deptMapper.selectById(id));
        return new SuccessTip(userVO);
    }

    @RequestMapping("/selectAllStudent")
    public Object selectAllStudentByDeptId(String id){
        return new SuccessTip(deptMapper.selectAllStudent(id));
    }
}
