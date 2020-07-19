package com.wugui.datax.admin.controller;

import com.wugui.datatx.core.biz.model.ReturnT;
import com.wugui.datax.admin.datamapper.SourceServerDOMapper;
import com.wugui.datax.admin.dataobject.SourceServerDO;
import com.wugui.datax.admin.service.SourceServerService;
import com.wugui.datax.admin.service.model.CloudAccountModel;
import com.wugui.datax.admin.service.model.SourceServerModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jiahui on 2020-07-13 09:08
 */
@RestController
@RequestMapping("/api/source")
@Api(tags = "源服务器接口")
public class SourceServerController {

    @Autowired
    private SourceServerService sourceServerService;

    @Autowired
    private SourceServerDOMapper sourceServerDOMapper;

    @PostMapping("/add")
    @ApiOperation("添加源服务器")
    public ReturnT addSourceServer(@RequestBody SourceServerModel sourceServerModel){

        SourceServerModel serverModel = sourceServerService.addSourceServer(sourceServerModel);

        return new ReturnT(serverModel);

    }

    @GetMapping("/pageList")
    @ApiOperation("源服务器列表")
    public ReturnT<Map<String, Object>> pageList(@RequestParam(required = false, defaultValue = "1") int current,
                                                 @RequestParam(required = false, defaultValue = "10") int size,
                                                 String username) {

        // page list
        List<SourceServerDO> list = sourceServerDOMapper.pageList((current - 1) * size, size, username);
        int recordsTotal = sourceServerDOMapper.pageListCount((current - 1) * size, size, username);

        // package result
        Map<String, Object> maps = new HashMap<>();
        maps.put("recordsTotal", recordsTotal);        // 总记录数
        maps.put("recordsFiltered", recordsTotal);    // 过滤后的总记录数
        maps.put("data", list);                    // 分页列表
        maps.put("page",(recordsTotal + size - 1) / size);//分页数
        return new ReturnT<>(maps);
    }

    @GetMapping("/list")
    @ApiOperation("源服务器列表")
    public ReturnT<List<SourceServerModel>> list() {

        // page list
        List<SourceServerModel> list = sourceServerService.getSourceList();
        return new ReturnT<>(list);
    }

    @PostMapping("/del")
    @ApiOperation("删除源服务器")
    public ReturnT delSource(@RequestParam(name = "id")Integer id){
        Boolean result = sourceServerService.deleteSource(id);

        return new ReturnT(result);

    }

    @PostMapping("/update")
    @ApiOperation("更新源服务器")
    public ReturnT updateSource(@RequestBody SourceServerModel sourceServerModel){

        Boolean result = sourceServerService.updateSource(sourceServerModel);

        return new ReturnT(result);

    }



}
