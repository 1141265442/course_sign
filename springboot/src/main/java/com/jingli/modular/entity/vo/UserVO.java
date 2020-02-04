package com.jingli.modular.entity.vo;

import com.jingli.modular.entity.Dept;
import com.jingli.modular.entity.User;
import lombok.Data;

import java.util.List;

@Data
public class UserVO {
    User user;
    List<Dept> deptList;
    Dept dept;

    public UserVO(User user, List<Dept> deptList, Dept dept) {
        this.user = user;
        this.deptList = deptList;
        this.dept = dept;
    }
}
