package com.jingli.modular.controller;


import com.jingli.core.msg.ErrorTip;
import com.jingli.core.msg.SuccessTip;
import com.jingli.modular.entity.Dept;
import com.jingli.modular.entity.Sign;
import com.jingli.modular.entity.SignRecord;
import com.jingli.modular.mapper.DeptMapper;
import com.jingli.modular.mapper.SignMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jingli
 * @since 2020-01-31
 */
@RestController
@RequestMapping("/sign")
public class SignController {
    @Autowired
    SignMapper signMapper;

    @RequestMapping("/add")
    public Object add(Sign sign){
        sign.setFinish(false);
        sign.setNumber(0);
        sign.setSignDatetime(LocalDateTime.now());
        sign.insert();
        return new SuccessTip("新建签到成功!");
    }

    //教师可查看其管理班级下的全部签到表
    @RequestMapping
    public Object selectSignByTeacherId(String userId){
        return new SuccessTip(signMapper.selectSignByUserId(userId));
    }

    //学生只可查看其所属班级的签到表
    @RequestMapping("/studentList")
    public Object selectSignByStudentId(String userId){
        return new SuccessTip(signMapper.selectSignByStudentId(userId));
    }

    @RequestMapping("/sign")
    public Object sign(SignRecord signRecord){
        SignRecord signRecord1 = signMapper.checkSign(signRecord.getUserId(), signRecord.getSignId());
        if(signRecord1!=null){
            return new ErrorTip("已签到,无需重复签到!");
        }
        if(signRecord.insert()){
            Sign sign = signMapper.selectById(signRecord.getSignId());
            sign.setNumber(sign.getNumber()+1);
            sign.updateById();
            return new SuccessTip("签到成功!");
        }
        else return new ErrorTip("签到失败");
    }

    @RequestMapping("/endSign")
    public Object endSign(String id){
        Sign sign = signMapper.selectById(id);
        sign.setFinish(true);
        sign.updateById();
        return new SuccessTip("签到已经结束");
    }

    @RequestMapping("/detail")
    public Object detail(String id){
        Map sign = signMapper.selectSignDetailBySignId(id);

        //强制时间格式转换  TODO:此处有bug
        sign.put("time",sign.get("time").toString());

        sign.put("roomtotal",signMapper.selectAllStuBySignId(id).size());
        sign.put("missNumber",(int)sign.get("roomtotal")-(int)sign.get("signNumber"));
        sign.put("missDetail",getMissStu(id));

        return new SuccessTip(sign);
    }

    List getMissStu(String id){
        List<Map> allStu = signMapper.selectAllStuBySignId(id);
        List<SignRecord> signStu=signMapper.selectSignStuBySignId(id);
        List missStu=new ArrayList();
        for (Map map : allStu) {
            if(signStu.size()==0) {
                missStu.add(map.get("name"));
                continue;
            }
            else{
                for (SignRecord signRecord : signStu) {
                    if(signRecord.getUserId()==(int)map.get("id")){
                        continue;
                    }else
                        missStu.add(map.get("name"));
                }
            }
        }
        return missStu;
    };
}
