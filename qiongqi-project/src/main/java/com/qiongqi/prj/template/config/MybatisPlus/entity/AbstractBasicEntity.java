package com.qiongqi.prj.template.config.MybatisPlus.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class AbstractBasicEntity<T extends Model<T>> extends Model<T> {
    @TableId
    @TableField("ID")
    private Long id;

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

    @TableField(
            value = "modifyTime",
            fill = FieldFill.INSERT_UPDATE
    )
    private Date modifyTime;

    public AbstractBasicEntity() {
    }

    public Long getId() {
        return this.id;
    }

    public Date getCreateTime() {
        return this.createTime;
    }


    public void setId(final Long id) {
        this.id = id;
    }

    public void setCreateTime(final Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }



}

