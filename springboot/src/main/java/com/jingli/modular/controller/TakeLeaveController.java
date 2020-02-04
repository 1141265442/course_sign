package com.jingli.modular.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jingli.core.msg.SuccessTip;
import com.jingli.modular.entity.TakeLeave;
import com.jingli.modular.entity.UserDept;
import com.jingli.modular.mapper.TakeLeaveMapper;
import com.jingli.modular.mapper.UserDeptMapper;
import com.jingli.modular.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Wrapper;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jingli
 * @since 2020-01-31
 */
@RestController
@RequestMapping("/takeLeave")
public class TakeLeaveController {

    @Autowired
    TakeLeaveMapper takeLeaveMapper;

    @RequestMapping("/add")
    public Object add(TakeLeave takeLeave){

        Integer deptId = takeLeaveMapper.selectDeptIdByUserId(takeLeave.getStudentId());
        takeLeave.setDeptId(deptId);
        takeLeave.insert();

        return new SuccessTip("请假已提交！");
    }

    @RequestMapping("/update")
    public Object update(TakeLeave takeLeave){
        takeLeave.updateById();
        return new SuccessTip("回复成功！");
    }

    @RequestMapping
    public Object list(String studentId){
        return new SuccessTip(takeLeaveMapper.selectByStuId(studentId));
    }
    @RequestMapping("/listByTeacherId")
    public Object listByTeacherId(String teacherId){
        return new SuccessTip(takeLeaveMapper.selectByTeacherId(teacherId));
    }


    @RequestMapping("/detail")
    public Object selectDetailById(String id){
        return new SuccessTip(takeLeaveMapper.selectDetailById(id));
    }

}
