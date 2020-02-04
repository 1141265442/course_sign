package com.jingli.modular.mapper;

import com.jingli.modular.entity.TakeLeave;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jingli
 * @since 2020-01-31
 */
public interface TakeLeaveMapper extends BaseMapper<TakeLeave> {
    List<TakeLeave> selectByStuId(String stuId);
    Integer selectDeptIdByUserId(Integer userId);
    Map selectDetailById(String id);
    List<Map> selectByTeacherId(String teacherId);
}
