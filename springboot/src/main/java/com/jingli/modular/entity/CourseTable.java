package com.jingli.modular.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.sql.Blob;
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
public class CourseTable extends Model<CourseTable> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 课表名
     */
    private String name;

    /**
     * 课表照片
     */
    private byte[] content;

    //课表照片地址
    @TableField("content_url")
    private String contentUrl;

    /**
     * 班级id
     */
    private Integer deptId;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
