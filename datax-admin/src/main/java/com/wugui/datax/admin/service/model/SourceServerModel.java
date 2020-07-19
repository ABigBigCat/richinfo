package com.wugui.datax.admin.service.model;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Created by jiahui on 2020-07-10 15:47
 */
public class SourceServerModel implements Serializable {

    private Integer id;

    /**
     * 名称
     */
    @ApiModelProperty("名称")
    private String name;

    /**
     * 系统类型 0-linux 1-windows
     */
    @ApiModelProperty("系统类型 0-linux 1-windows")
    private Integer type;

    /**
     * 公网ip
     */
    @ApiModelProperty("公网ip")
    private String ip;

    /**
     * 端口
     */
    @ApiModelProperty("端口")
    private String port;

    /**
     * 用户名
     */
    @ApiModelProperty("用户名")
    private String username;

    /**
     * 用户密码
     */
    @ApiModelProperty("用户密码")
    private String password;

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

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
