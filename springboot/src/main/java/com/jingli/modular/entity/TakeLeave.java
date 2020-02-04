package com.jingli.modular.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author jingli
 * @since 2020-01-31
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class TakeLeave extends Model<TakeLeave> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 班级id
     */
    private Integer deptId;

    /**
     * 请假人id（用户表id）
     */
    private Integer studentId;

    /**
     * 响应人id（用户表id）
     */
    private Integer teacherId;

    /**
     * 开始时间
     */
    private String startTime;

    /**
     * 结束时间
     */
    private String endTime;

    /**
     * 请假原因
     */
    private String reason;

    /**
     * 响应消息
     */
    private String response;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
