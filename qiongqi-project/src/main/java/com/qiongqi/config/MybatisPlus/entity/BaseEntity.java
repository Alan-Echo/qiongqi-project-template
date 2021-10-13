package com.qiongqi.config.MybatisPlus.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class BaseEntity<T extends Model<T>> extends AbstractBasicEntity<T> {

    @DateTimeFormat(
            pattern = "yyyy-MM-dd HH:mm:ss"
    )
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    @TableField(
            value = "modifyTime",
            fill = FieldFill.INSERT_UPDATE
    )
    private Date modifyTime;


    @DateTimeFormat(
            pattern = "yyyy-MM-dd HH:mm:ss"
    )
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    @TableField(
            value = "createTime",
            fill = FieldFill.INSERT
    )
    private Date createTime;

    public BaseEntity() {

    }


    public Date getModifyTime() {
        return this.modifyTime;
    }
    public void setModifyTime(final Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    @Override
    public Date getCreateTime() {
        return createTime;
    }

    @Override
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
