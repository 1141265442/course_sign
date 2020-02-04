package com.jingli.modular.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

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
public class Sign extends Model<Sign> {

    private static final long serialVersionUID = 1L;
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 班级id
     */
    private Integer deptId;

    /**
     * 签到开始时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime signDatetime;

    /**
     * 是否完成
     */
    private Boolean finish;

    /**
     * 课程id
     */
    @TableField("course_name")
    private String courseName;

    /**
     * 教师id
     */
    private Integer userId;

    /**
     * 签到人数
     */
    private Integer number;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
