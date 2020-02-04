package com.jingli.modular.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
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
public class SignRecord extends Model<SignRecord> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 签到表id
     */
    private Integer signId;

    /**
     * 用户id
     */
    private Integer userId;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
