package com.qiongqi.video.guaikavideo.config.MybatisPlus.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.qiongqi.video.guaikavideo.config.MybatisPlus.group.AddGroup;
import com.qiongqi.video.guaikavideo.config.MybatisPlus.group.UpdateGroup;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;
import java.util.Date;

public abstract class BasicDTO implements Serializable {

    @Null(
            message = "{id.null}",
            groups = {AddGroup.class}
    )
    @NotNull( message = "{id.require}",groups = {UpdateGroup.class})
    private Long id;

    @JsonProperty(
            access = Access.READ_ONLY
    )
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    private Date createTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    private Date modifyTime;

    public BasicDTO() {}

    public Long getId() {
        return this.id;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    @JsonProperty(
            access = Access.READ_ONLY
    )
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
