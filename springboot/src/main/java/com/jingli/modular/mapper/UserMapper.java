package com.jingli.modular.mapper;

import com.jingli.modular.entity.Dept;
import com.jingli.modular.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 管理员表 Mapper 接口
 * </p>
 *
 * @author jingli
 * @since 2020-01-31
 */
public interface UserMapper extends BaseMapper<User> {
    int delUser(String id);
    Dept selectDeptByUserId(String id);
    int updateUser(@Param("username") String username,@Param("name") String name,
                   @Param("phone") String phone,@Param("roleType") Integer roleType,
                   @Param("id") Integer id);

}
