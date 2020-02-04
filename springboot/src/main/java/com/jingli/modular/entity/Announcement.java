package com.jingli.modular.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
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
 * @since 2020-02-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Announcement extends Model<Announcement> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 公告名
     */
    private String name;

    /**
     * 公告内容
     */
    private String content;

    /**
     * 公告图片
     */
    private byte[] img;

    //图片地址
    @TableField("img_url")
    private String imgUrl;

    /**
     * 发布人id
     */
    private Integer userId;

    /**
     * 发布时间
     */
    private LocalDateTime dateTime;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
