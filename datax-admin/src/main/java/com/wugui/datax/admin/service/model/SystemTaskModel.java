package com.wugui.datax.admin.service.model;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by jiahui on 2020-07-10 16:24
 */
public class SystemTaskModel implements Serializable {

    private Integer id;

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("源服务器id")
    private Integer sourceServerId;

    @ApiModelProperty("源服务器名称")
    private String sourceServerName;

    @ApiModelProperty("源服务器系统类型")
    private Integer sourceServerSystemType;

    @ApiModelProperty("云账号id")
    private Integer cloudAccountId;

    @ApiModelProperty("云账号名称")
    private String cloudAccountName;

    @ApiModelProperty("云账号服务器ip")
    private String cloudServerIp;

    @ApiModelProperty("云实例名称")
    private String cloudExampleName;

    @ApiModelProperty("目标地域")
    private String targetArea;

    @ApiModelProperty("带宽")
    private Integer broadband;

    @ApiModelProperty("任务启动类型 0-自动 1-手动")
    private Integer startType;

    @ApiModelProperty("开始时间")
    private Date startTime;

    @ApiModelProperty("状态 0-未启动 1-失败 2-运行中 3-成功 ")
    private Integer status;

    @ApiModelProperty("迁移耗时")
    private Date spendTime;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getSpendTime() {
        return spendTime;
    }

    public void setSpendTime(Date spendTime) {
        this.spendTime = spendTime;
    }

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

    public Integer getSourceServerId() {
        return sourceServerId;
    }

    public void setSourceServerId(Integer sourceServerId) {
        this.sourceServerId = sourceServerId;
    }

    public Integer getCloudAccountId() {
        return cloudAccountId;
    }

    public void setCloudAccountId(Integer cloudAccountId) {
        this.cloudAccountId = cloudAccountId;
    }

    public String getCloudServerIp() {
        return cloudServerIp;
    }

    public void setCloudServerIp(String cloudServerIp) {
        this.cloudServerIp = cloudServerIp;
    }

    public String getTargetArea() {
        return targetArea;
    }

    public void setTargetArea(String targetArea) {
        this.targetArea = targetArea;
    }

    public Integer getBroadband() {
        return broadband;
    }

    public void setBroadband(Integer broadband) {
        this.broadband = broadband;
    }

    public Integer getStartType() {
        return startType;
    }

    public void setStartType(Integer startType) {
        this.startType = startType;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public String getCloudExampleName() {
        return cloudExampleName;
    }

    public void setCloudExampleName(String cloudExampleName) {
        this.cloudExampleName = cloudExampleName;
    }

    public String getSourceServerName() {
        return sourceServerName;
    }

    public void setSourceServerName(String sourceServerName) {
        this.sourceServerName = sourceServerName;
    }

    public String getCloudAccountName() {
        return cloudAccountName;
    }

    public void setCloudAccountName(String cloudAccountName) {
        this.cloudAccountName = cloudAccountName;
    }

    public Integer getSourceServerSystemType() {
        return sourceServerSystemType;
    }

    public void setSourceServerSystemType(Integer sourceServerSystemType) {
        this.sourceServerSystemType = sourceServerSystemType;
    }
}
