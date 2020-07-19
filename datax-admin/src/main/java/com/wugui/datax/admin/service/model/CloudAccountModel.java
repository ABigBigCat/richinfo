package com.wugui.datax.admin.service.model;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Created by jiahui on 2020-07-10 09:25
 */
public class CloudAccountModel implements Serializable {

    private Integer id;

    /**
     * 名称
     */
    @ApiModelProperty("姓名")
    private String name;

    /**
     * 云账号类型 0-腾讯云 1-阿里云
     */
    @ApiModelProperty("云账号类型 0-腾讯云 1-阿里云")
    private Integer type;

    /**
     * accesskeyId
     */
    @ApiModelProperty("accesskeyId")
    private String accesskeyId;

    /**
     * accesskeySecret
     */
    @ApiModelProperty("accesskeySecret")
    private String accesskeySecret;

    /**
     * 备注
     */
    @ApiModelProperty("备注")
    private String remarks;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getAccesskeyId() {
        return accesskeyId;
    }

    public void setAccesskeyId(String accesskeyId) {
        this.accesskeyId = accesskeyId;
    }

    public String getAccesskeySecret() {
        return accesskeySecret;
    }

    public void setAccesskeySecret(String accesskeySecret) {
        this.accesskeySecret = accesskeySecret;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
