package com.jingli.modular.mapper;

import com.jingli.modular.entity.Dept;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jingli.modular.entity.User;

import java.util.List;

/**
 * <p>
 * 部门表 Mapper 接口
 * </p>
 *
 * @author jingli
 * @since 2020-01-31
 */
public interface DeptMapper extends BaseMapper<Dept> {
     List<User> selectAllStudent(String id);
     List<Dept> selectAllDeptByUserId(String userId);
}
