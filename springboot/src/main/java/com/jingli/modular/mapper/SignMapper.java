package com.jingli.modular.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.jingli.modular.entity.Sign;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jingli.modular.entity.SignRecord;
import org.apache.ibatis.annotations.Param;

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
public interface SignMapper extends BaseMapper<Sign> {
    List<Sign> selectSignByUserId(String userId);
    List<Sign> selectSignByStudentId(String userId);

    SignRecord checkSign(@Param("userId")Integer userId,@Param("signId")Integer signId);

    Map selectSignDetailBySignId(String id);
    List<Map> selectAllStuBySignId(String id);
    List<SignRecord> selectSignStuBySignId(String id);

}
