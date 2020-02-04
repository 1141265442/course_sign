package com.jingli.modular.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableField;
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
public class UserDept extends Model<UserDept> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户id
     */
    @TableField("userId")
    private Integer userId;

    /**
     * 部门id
     */
    @TableField("deptId")
    private Integer deptId;

    //用户类型
    @TableField("user_type")
    private Integer userType;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
